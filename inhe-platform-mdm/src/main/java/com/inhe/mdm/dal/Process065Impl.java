package com.inhe.mdm.dal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmAaStealDetailDao;
import com.inhe.mdm.dao.MdmAaStealRuleDao;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmDeviceReadDao;
import com.inhe.mdm.dao.MdmVeeTaskDataDao;
import com.inhe.mdm.model.MdmAaStealDetail;
import com.inhe.mdm.model.MdmAaStealRule;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.VtMdmAmDeviceChange;
import com.inhe.mdm.model.VtMdmDeviceEvent;
import com.inhe.mdm.model.VtMdmDeviceRead;
import com.inhe.mdm.model.VtMdmVeeTaskData;
import com.inhe.mdm.utils.MdmDateUtil;
import com.inhe.utils.DateUtil;

/**
 * @description 防窃电分析
 * @author ghf
 * @date 2021/01/05
 */
@Service
public class Process065Impl {
    
    private static final Logger logger = LoggerFactory.getLogger(Process065Impl.class);
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    
    @Autowired
    MdmAaStealRuleDao mdmAaStealRuleDao;
    
    @Autowired
    MdmAaStealDetailDao mdmAaStealDetailDao;
    
    @Autowired
    MdmVeeTaskDataDao mdmVeeTaskDataDao;
    
    @Autowired
    MdmDeviceReadDao mdmDeviceReadDao;
    
    @Autowired
    SqlSessionTemplate template;
    
    public void execute(JSONObject jobj) throws Exception {
        Date startDate = DateUtil.rollByMonth(jobj.getDate("data_time"), -1);
        String month = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
        logger.info("Process065-start-"+month);
        
        List<MdmAaStealRule> ruleList = mdmAaStealRuleDao.selectAll();
        if(ruleList == null) {
            return;
        }
        
        template.select("com.inhe.mdm.dao.MdmAmDeviceDao.selectAll", new ResultHandler<Object>() {
            @Override
            public void handleResult(ResultContext<? extends Object> arg0) {
                MdmAmDevice device = (MdmAmDevice) arg0.getResultObject();
                for(MdmAaStealRule rule:ruleList) {
                    Double suspicion = 0.0;
                    try {
                        if("1".equals(rule.getSource())) {  //数据来源-VEE
                            suspicion = sourceFromVee(rule,device,month);
                        }else if("2".equals(rule.getSource())) {  //数据来源-异常事件
                            suspicion = sourceFromEvent(rule,device,month);
                        }else if("3".equals(rule.getSource())) {  //数据来源-档案
                            suspicion = sourceFromDocument(rule,device,month);
                        }else if("4".equals(rule.getSource())) {  //数据来源-异常事件+档案
                            suspicion = sourceFromEventAndDocument(rule,device,month);
                        }else if("5".equals(rule.getSource())) {  //数据来源-抄表数据
                            suspicion = sourceFromReadData(rule,device,month);
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    if(suspicion > 0) {
                        MdmAaStealDetail mdmAaStealDetail = new MdmAaStealDetail();
                        mdmAaStealDetail.setDeviceId(device.getId());
                        mdmAaStealDetail.setNowTime(month);
                        mdmAaStealDetail.setStealId(rule.getId());
                        mdmAaStealDetail.setOrgId(device.getOrgId());
                        mdmAaStealDetail.setDeptId(device.getDeptId());
                        mdmAaStealDetail.setLineId(device.getLineId());
                        mdmAaStealDetail.setTmId(device.getTmId());
                        mdmAaStealDetail.setDeviceType(device.getType());
                        mdmAaStealDetail.setCstId(device.getOrgId());
                        mdmAaStealDetail.setCstName(device.getDescription());
                        mdmAaStealDetail.setMeterSort(device.getMeterSort()==null?"-":device.getMeterSort());
                        mdmAaStealDetail.setDeviceNum(device.getDeviceNum());
                        mdmAaStealDetail.setStatus("0");  //0：未处理   1：已处理
                        mdmAaStealDetail.setCdate(new Date());
                        mdmAaStealDetail.setSuspicion(suspicion);
                        MdmAaStealDetail detail=mdmAaStealDetailDao.selectByPrimaryKey(mdmAaStealDetail);
                        if(detail == null ) {
                            mdmAaStealDetailDao.insertSelective(mdmAaStealDetail);
                        }else if(detail != null && "0".equals(detail.getStatus())) {
                            mdmAaStealDetailDao.updateByPrimaryKeySelective(mdmAaStealDetail);
                        }
                    }
                }
            }
            
        });
        
    }

    
    /**
     * source为vee
     * @param rule
     * @param device
     * @param month
     * @throws Exception 
     */
    private Double sourceFromVee(MdmAaStealRule rule, MdmAmDevice device, String month) throws Exception {
        Double suspicion = 0.0;
        //R001,03000102
        String[] arr = rule.getParams().split(",");
        VtMdmVeeTaskData vtMdmVeeTaskData = new VtMdmVeeTaskData();
        vtMdmVeeTaskData.setDeviceCode(device.getId());
        vtMdmVeeTaskData.setFieldId(arr[1]);
        vtMdmVeeTaskData.setVeeType("0"); //VEE类型：0：校验类型 1：估算类型 2：编辑类型
        vtMdmVeeTaskData.setOpResult("S"); //数据操作结果：S成功 F失败
        String lastDayOfMonth=MdmDateUtil.getLastDayOfMonth(month);
        vtMdmVeeTaskData.setStartTime(DateUtil.stringToDate(month+"-01 00:00:00", DateUtil.DATE_TIME_WITH_LINE));
        vtMdmVeeTaskData.setEndTime(DateUtil.stringToDate(lastDayOfMonth+" 23:59:59", DateUtil.DATE_TIME_WITH_LINE));
        vtMdmVeeTaskData.setVeeResultDetails(arr[0]);
        List<MdmVeeTaskData> veeTaskDataList = mdmVeeTaskDataDao.selectDetailByRule(vtMdmVeeTaskData);
        
        if(veeTaskDataList != null) {
            for(MdmVeeTaskData veeTaskData:veeTaskDataList) {
                JSONArray detailJSONArray = JSONArray.parseArray(veeTaskData.getVeeResultDetails());
                if(detailJSONArray!=null) {
                    boolean b = true;
                    for(int i=0;i<detailJSONArray.size()&&b;i++) {
                        JSONObject detailObject = detailJSONArray.getJSONObject(i);
                        if(detailObject.getString("id").equals(arr[0]) && 1 == detailObject.getIntValue("result")) {
                            suspicion=rule.getFactor();
                            b = false;
                        }
                    }
                }
            }
        }
        return suspicion;
    }
    
    /**
     * source为异常事件
     * @param rule
     * @param device
     * @param month
     * @throws Exception 
     */
    private Double sourceFromEvent(MdmAaStealRule rule, MdmAmDevice device, String month) throws Exception {
        Double suspicion = 0.0;
        //  8|164|166|168|1181|1182|1183
        String[] typeArr = rule.getParams().split("\\|");
        List<Integer> typeList = new ArrayList<Integer>();
        for(int i=0;i<typeArr.length;i++) {
            typeList.add(Integer.valueOf(typeArr[i]));
        }
        VtMdmDeviceEvent vtMdmDeviceEvent = new VtMdmDeviceEvent(); 
        vtMdmDeviceEvent.setDeviceId(device.getId());
        vtMdmDeviceEvent.setList(typeList);
        String lastDayOfMonth=MdmDateUtil.getLastDayOfMonth(month);
        vtMdmDeviceEvent.setStartTime(DateUtil.stringToDate(month+"-01 00:00:00", DateUtil.DATE_TIME_WITH_LINE));
        vtMdmDeviceEvent.setEndTime(DateUtil.stringToDate(lastDayOfMonth+" 23:59:59", DateUtil.DATE_TIME_WITH_LINE));
        Integer count = mdmAaStealDetailDao.selectCountByEventSource(vtMdmDeviceEvent);
        if(count > 0) {
            suspicion=rule.getFactor();
        }
        return suspicion;
    }
    
    /**
     * source为档案
     * @param rule
     * @param device
     * @param month
     * @throws Exception 
     */
    private Double sourceFromDocument(MdmAaStealRule rule, MdmAmDevice device, String month) throws Exception {
        Double suspicion = 0.0;
        String lastDayOfMonth=MdmDateUtil.getLastDayOfMonth(month);
        if("0601".equals(rule.getId())) { //电表状态不为运行
            Date lastRegTime= DateUtil.stringToDate(lastDayOfMonth+" 23:59:59", DateUtil.DATE_TIME_WITH_LINE);
            if(device.getRegDate().before(lastRegTime) && !"0".equals(device.getStatus())) {
                suspicion=rule.getFactor();
            }
        }else if("0602".equals(rule.getId())) { //电表有更换记录
            VtMdmAmDeviceChange vtMdmAmDeviceChange = new VtMdmAmDeviceChange();
            vtMdmAmDeviceChange.setOldDevicenum(device.getDeviceNum());
            vtMdmAmDeviceChange.setNewDevicenum(device.getDeviceNum());
            vtMdmAmDeviceChange.setMode("0"); //业务模式   0-设备更换   1-设备拆除
            vtMdmAmDeviceChange.setStartTime(DateUtil.stringToDate(month+"-01 00:00:00", DateUtil.DATE_TIME_WITH_LINE));
            vtMdmAmDeviceChange.setEndTime(DateUtil.stringToDate(lastDayOfMonth+" 23:59:59", DateUtil.DATE_TIME_WITH_LINE));
            Integer count = mdmAaStealDetailDao.selectCountByDeviceChange(vtMdmAmDeviceChange);
            if(count > 0) {
                suspicion=rule.getFactor();
            }
        }
        return suspicion;
    }
    
    /**
     * source为异常事件+档案
     * @param rule
     * @param device
     * @param month
     * @throws Exception 
     */
    private Double sourceFromEventAndDocument(MdmAaStealRule rule, MdmAmDevice device, String month) throws Exception {
        Double suspicion = 0.0;
        if("0405".equals(rule.getId())) {
            String measMode=device.getMeasMode();
            if(measMode != null && "0".equals(measMode)) { //计量方式为高供高计
                suspicion = sourceFromEvent(rule,device,month);
            }
        }
        return suspicion;
    }
    
    /**
     * source为抄读数据
     * @param rule
     * @param device
     * @param month
     * @throws Exception 
     */
    private Double sourceFromReadData(MdmAaStealRule rule, MdmAmDevice device, String month) throws Exception {
        Double suspicion = 0.0;
        //  >1,03472002|03472102|03472202|03472302
        String[] paramArr = rule.getParams().split(",");
        String[] fieldArr = paramArr[1].split("\\|");
        List<String> fieldList = Arrays.asList(fieldArr);
        VtMdmDeviceRead vtMdmDeviceRead = new VtMdmDeviceRead(); 
        vtMdmDeviceRead.setDeviceId(device.getId());
        vtMdmDeviceRead.setList(fieldList);
        vtMdmDeviceRead.setCompare(paramArr[0]);
        String lastDayOfMonth=MdmDateUtil.getLastDayOfMonth(month);
        vtMdmDeviceRead.setStartTime(DateUtil.stringToDate(month+"-01 00:00:00", DateUtil.DATE_TIME_WITH_LINE));
        vtMdmDeviceRead.setFinishTime(DateUtil.stringToDate(lastDayOfMonth+" 23:59:59", DateUtil.DATE_TIME_WITH_LINE));
        Integer count = mdmDeviceReadDao.selectCountByPowerFactor(vtMdmDeviceRead);
        if(count > 0) {
            suspicion=rule.getFactor();
        }
        return suspicion;
    }
    
}
