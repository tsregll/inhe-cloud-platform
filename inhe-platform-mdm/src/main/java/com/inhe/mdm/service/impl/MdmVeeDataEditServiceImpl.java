package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.build.rocketmq.ProducerNode;
import com.inhe.mdm.dao.MdmVeeEstimateDao;
import com.inhe.mdm.dao.MdmVeeTaskDataDao;
import com.inhe.mdm.dao.MdmVeeValidationDao;
import com.inhe.mdm.model.MdmVeeEstimate;
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.MdmVeeValidation;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeTaskData;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.mdm.service.IMdmVeeDataEditService;
import com.inhe.mdm.utils.MdmUtil;

/**
 * @Description : vee校验规则数据编辑服务
 * @author : guhf
 * @Date : 2020/12/16
 */
@Service
public class MdmVeeDataEditServiceImpl implements IMdmVeeDataEditService , ApplicationRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(MdmVeeDataEditServiceImpl.class);
    
    private static String[] DAYFFA = {"0303[0-8]002"};  //日冻结正向有功(T1~T8)
    private static String[] DAYFRA = {"0304[0-8]002"};  //日冻结反向有功(T1~T8)
    private static String[] DAYFFR = {"0305[0-8]002"};  //日冻结正向无功(T1~T8)
    private static String[] DAYFRR = {"0306[0-8]002"};  //日冻结反向无功(T1~T8)

    private static String[] DAYUFA = {"0303[0-8]012"};  //日使用正向有功(T1~T8)
    private static String[] DAYURA = {"0304[0-8]012"};  //日使用反向有功(T1~T8)
    private static String[] DAYUFR = {"0305[0-8]012"};  //日使用正向无功(T1~T8)
    private static String[] DAYURR = {"0306[0-8]012"};  //日使用反向无功(T1~T8)

    private static String[] MONFFA = {"0303[0-8]102"};  //月冻结正向有功(T1~T8)
    private static String[] MONFRA = {"0304[0-8]102"};  //月冻结反向有功(T1~T8)
    private static String[] MONFFR = {"0305[0-8]102"};  //月冻结正向无功(T1~T8)
    private static String[] MONFRR = {"0306[0-8]102"};  //月冻结反向无功(T1~T8)

    private static String[] MONUFA = {"0303[0-8]112"};  //月使用正向有功(T1~T8)
    private static String[] MONURA = {"0304[0-8]112"};  //月使用反向有功(T1~T8)
    private static String[] MONUFR = {"0305[0-8]112"};  //月使用正向无功(T1~T8)
    private static String[] MONURR = {"0306[0-8]112"};  //月使用反向无功(T1~T8)
    
    private static String[] DAYFREE = {"0300[0-8]002"};  //上一日冻结总电量(T1~T8)
    private static String[] MONFREE = {"0300[0-8]102"};  //上一月冻结总电量(T1~T8)
    
    private static String[] DAYVEE = {"VEE[0-8]0012"};  //预估日用电量
    private static String[] MONVEE = {"VEE[0-8]0112"};  //预估月用电量
    
    private final Map<String, MdmVeeValidation> mdmVeeValidationMap = new ConcurrentHashMap<String, MdmVeeValidation>();
    
    private final Map<String, MdmVeeEstimate> mdmVeeEstimateMap = new ConcurrentHashMap<String, MdmVeeEstimate>();
    
    @Autowired
    MdmVeeTaskDataDao mdmVeeTaskDataDao;
    
    @Autowired
    MdmVeeValidationDao mdmVeeValidationDao;
    
    @Autowired
    MdmVeeEstimateDao mdmVeeEstimateDao;
    
    @Autowired
    ProducerNode producerNode;
    
    @Autowired
    SendCmdProxy sendCmdProxy;
    
    @Value("${spring.application.name}")
    private String source;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {       
        initValRules();
        initEstRules();
    }
    
    @Override
    public PageBean<VtMdmVeeTaskData> queryList(VtMdmVeeTaskData param, int pageNo, int pageSize) {
    	if(param.getVeeType() != null){
	    	if("0".equals(param.getVeeType())){
	    		param.setVeeType("0");
	    		param.setVeeResult("1");
	    	}else if("1".equals(param.getVeeType())){
	    		param.setVeeType("0");
	    		param.setVeeResult("2");
	    	}else if("2".equals(param.getVeeType())){
	    		param.setVeeType("1");
	    		param.setVeeResult("2");
	    	}else if("3".equals(param.getVeeType())){
	    		param.setVeeType("1");
	    		param.setVeeResult("0");
	    	}else{
	    		param.setVeeType("2");
	    		param.setVeeResult("0");
	    	}
    	}
        int totalCount = mdmVeeTaskDataDao.selectCount(param);
        List<VtMdmVeeTaskData> rows = mdmVeeTaskDataDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmVeeTaskData> pageBean = new PageBean<VtMdmVeeTaskData>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    @Override
    public boolean update(MdmVeeTaskData mdmVeeTaskData) {
        JSONObject param = JSONObject.parseObject(mdmVeeTaskData.getId());
        mdmVeeTaskData.setDataType(param.getInteger("dataType"));
        mdmVeeTaskData.setId(param.getString("id"));
        mdmVeeTaskData.setStatus("1");
        mdmVeeTaskData.setOpResult("S");
        mdmVeeTaskData.setVeeType("2");
        mdmVeeTaskData.setVeeResult("0");
        int result = mdmVeeTaskDataDao.updateByPrimaryKeySelective(mdmVeeTaskData);
        if (result <= 0) {
            throw new InheExceptionBase(-100053, "更新记录失败");
        }
        
        String veeDataType = param.getString("veeDataType");
        String readTime = param.getString("readTime");
        readTime = readTime.replace("T", " ").split("\\.")[0];
        String deviceCode = param.getString("deviceCode");
        String fieldId = param.getString("fieldId");
        String value = mdmVeeTaskData.getEditValue();
        if (veeDataType.equals("09")||veeDataType.equals("10")) {
            //kwh日用电量，月用电量，以VEE开头的10条指令，只保存aa_kwh_detail
            sendToKwh(readTime,deviceCode,fieldId,value);
        }
        else if (veeDataType.equals("05")) {
            //freezing帐单数据，只保存amr_day_freezing 和 amr_month_freezing
            sendToFreezing(readTime,deviceCode,fieldId,value);
        }
        return true;
    }
    
    @Override
    public MdmVeeTaskData detail(MdmVeeTaskData mdmVeeTaskData) {
        MdmVeeTaskData result = mdmVeeTaskDataDao.selectByPrimaryKey(mdmVeeTaskData);
        if (result == null) {
            throw new InheExceptionBase(-101081, "找不到指定记录");
        }
        return result;
    }

    @Override
    public PageBean<VtMdmRulesDetail> searchFailDetail(MdmVeeTaskData param, int pageNo, int pageSize) {
        List<VtMdmRulesDetail> result = new ArrayList<VtMdmRulesDetail>();
        MdmVeeTaskData mdmVeeTaskData = detail(param);
        JSONArray detailArray = JSONArray.parseArray(mdmVeeTaskData.getVeeResultDetails());
        if(detailArray != null) {
            for (int i = 0; i < detailArray.size(); i++) {
                JSONObject item = (JSONObject) detailArray.get(i);
                VtMdmRulesDetail vtMdmRulesDetail = new VtMdmRulesDetail();
                String id = item.getString("id");
                vtMdmRulesDetail.setId(id);
                vtMdmRulesDetail.setRemarks(item.getString("remarks"));
                vtMdmRulesDetail.setResult(item.getString("result"));
                if(mdmVeeValidationMap.containsKey(id)) {
                    vtMdmRulesDetail.setName(mdmVeeValidationMap.get(id).getName());
                } else if(mdmVeeEstimateMap.containsKey(id)) {
                    vtMdmRulesDetail.setName(mdmVeeEstimateMap.get(id).getName());
                }
                result.add(vtMdmRulesDetail);
            }
        }
        int totalCount = result.size();
        int start = (pageNo - 1) * pageSize;
        int end = pageNo * pageSize < totalCount? (pageNo * pageSize):totalCount;
        List<VtMdmRulesDetail> rows = result.subList(start, end);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmRulesDetail> pageBean = new PageBean<VtMdmRulesDetail>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    private void initValRules() {
        MdmVeeValidation param = new MdmVeeValidation();
        param.setActived("Y");
        List<MdmVeeValidation> mdmVeeValidationList = mdmVeeValidationDao.selectAll(param);
        for(MdmVeeValidation item:mdmVeeValidationList) {
            mdmVeeValidationMap.put(item.getId(), item);
        }
    }
    
    private void initEstRules() {
        MdmVeeEstimate param = new MdmVeeEstimate();
        param.setActived("Y");
        List<MdmVeeEstimate> mdmVeeEstimateList = mdmVeeEstimateDao.selectAll(param);
        for(MdmVeeEstimate item:mdmVeeEstimateList) {
            mdmVeeEstimateMap.put(item.getId(), item);
        }
    }
    
    public void sendToFreezing(String readTime,String deviceId,String fieldId,String value) {
        try {
            String dataType=null;
            if(MdmUtil.contains(DAYFFA, fieldId)||
                    MdmUtil.contains(DAYFRA, fieldId)||
                    MdmUtil.contains(DAYFFR, fieldId)||
                    MdmUtil.contains(DAYFRR, fieldId)||
                    MdmUtil.contains(DAYFREE, fieldId)) {
                dataType = "4";//日冻结电量
            }
            else if(MdmUtil.contains(DAYUFA, fieldId)||
                    MdmUtil.contains(DAYURA, fieldId)||
                    MdmUtil.contains(DAYUFR, fieldId)||
                    MdmUtil.contains(DAYURR, fieldId)) {
                dataType = "5";//日使用电量
            }
            else if(MdmUtil.contains(MONFFA, fieldId)||
                    MdmUtil.contains(MONFRA, fieldId)||
                    MdmUtil.contains(MONFFR, fieldId)||
                    MdmUtil.contains(MONFRR, fieldId)||
                    MdmUtil.contains(MONFREE, fieldId)) {
                dataType = "6";//月冻结电量
            }
            else if(MdmUtil.contains(MONUFA, fieldId)||
                    MdmUtil.contains(MONURA, fieldId)||
                    MdmUtil.contains(MONUFR, fieldId)||
                    MdmUtil.contains(MONURR, fieldId)) {
                dataType = "7";//月使用电量
            }
            else {
                logger.info("send to freezing field error:"+fieldId);
                return;
            }
            String function = "1"; //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
            String sort = "2";
            String type = "0";
            sendCmdProxy.send0112Save(function, deviceId, fieldId, dataType, readTime, value, sort, type);
        } catch (Exception e) {
            logger.error("send to freezing error.", e);
        }
    }

    public void sendToKwh(String readTime,String deviceId,String fieldId,String value) {
        try {
            String dataType=null;
            if(MdmUtil.contains(DAYVEE, fieldId)) {
                dataType = "09";//日使用电量
            }
            else if(MdmUtil.contains(MONVEE, fieldId)) {
                dataType = "10";//月使用电量
            }
            else {
                logger.info("send to kwh field error:"+fieldId);
                return;
            }
            String function = "2"; //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
            String sort = "2";
            String type = "2";
            sendCmdProxy.send0112Save(function, deviceId, fieldId, dataType, readTime, value, sort, type);
        } catch (Exception e) {
            logger.error("send to kwh error.", e);
        }
    }
    
}
