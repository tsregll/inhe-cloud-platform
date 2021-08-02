package com.inhe.mdm.subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.admin.model.SysDevice;
import com.inhe.build.rocketmq.IMessageProcessorCmd;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmDeviceEventDao;
import com.inhe.mdm.dao.MdmDeviceReadDao;
import com.inhe.mdm.dao.MdmDeviceSetDao;
import com.inhe.mdm.dao.MdmSysEventTypeDao;
import com.inhe.mdm.model.MdmDeviceEvent;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.mdm.model.MdmDeviceSet;
import com.inhe.mdm.model.MdmSysEventType;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.mdm.service.IMdmEventAutoProcessService;
import com.inhe.mdm.service.MdmSaveFreezingService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.ISysDeviceService;
import com.inhe.utils.DateUtil;

/**
 * 数据流转消息订阅
 * @author
 * @date 2020/12/08
 */
@Service
public class SubscribeCmd0201 implements IMessageProcessorCmd {
    
    private static final Logger logger = LoggerFactory.getLogger(SubscribeCmd0201.class);
    
    @Autowired
    MdmDeviceReadDao mdmDeviceReadDao;
    
    @Autowired
    MdmDeviceSetDao mdmDeviceSetDao;
    
    @Autowired
    MdmDeviceEventDao mdmDeviceEventDao;
    
    @Autowired
    IMdmEventAutoProcessService MdmEventAutoProcess;
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    
    @Autowired
    MdmSaveFreezingService mdmSaveFreezingService;
    
    @Autowired
    SendCmdProxy sendCmdProxy;
    
    @Autowired
    ISysDeviceService sysDeviceService;
    
    @Autowired
    MdmSysEventTypeDao mdmSysEventTypeDao;
    
	@Override
	public String process(JSONObject jobj) {
	    logger.info("Receive 0201. "+jobj.toJSONString());
		try {
            String type = jobj.getString("type");
            JSONObject content = jobj.getJSONObject("content");
            String fieldType = content.getString("fieldType");
            if ("0".equals(type)) {
                if ("R".equals(fieldType)) {
                    SaveToMdmDeviceRead(jobj);
                    mdmSaveFreezingService.processCmd(content);
                } else {
                    SaveToMdmDeviceSet(jobj);
                }
            } else if ("1".equals(type)) {
                SaveToMdmDeviceEvent(jobj);
            } 
        } catch (Exception e) {
            logger.error("", e);
        }
		return "0";
	}
	
	/**
	 * @description 保存到 MDM_DEVICE_READ
	 * @param jobj
	 * @throws Exception
	 */
	private void SaveToMdmDeviceRead(JSONObject jobj) throws Exception {
	    //去重
        List<String> record = new ArrayList<String>();
        
	    JSONObject content = jobj.getJSONObject("content");
	    String devId = content.getString("devId");
	    SysDevice paramSysDevice = new SysDevice();
	    paramSysDevice.setDevId(devId);
	    SysDevice sysDevice = sysDeviceService.detail(paramSysDevice);
	    if(sysDevice == null) {
	        logger.error("Can not find the device: " + devId);
	        return;
	    }
	    
	    Date dataTime = new Date(content.getLong("dataTime"));
	    String fieldId = content.getString("fieldId");
	    MdmDeviceRead mdmDeviceRead = new MdmDeviceRead();
	    mdmDeviceRead.setCmdCode(content.getString("cmdCode"));
	    mdmDeviceRead.setDeviceId(devId);
	    mdmDeviceRead.setDeviceNum(sysDevice.getDevNo());
//	    mdmDeviceRead.setParentNum(sysDevice.getParentName());
	    mdmDeviceRead.setDeptId(sysDevice.getDeptId());
	    mdmDeviceRead.setFieldId(fieldId);
	    mdmDeviceRead.setRealValue(content.getString("value"));
	    mdmDeviceRead.setOrgId(content.getString("orgId"));
	    mdmDeviceRead.setReadTime(dataTime);
	    mdmDeviceRead.setStartTime(dataTime);
	    mdmDeviceRead.setFinishTime(dataTime);
	    mdmDeviceRead.setDataType(MdmUtil.checkDataType(fieldId));
	    mdmDeviceRead.setAgentCode(MdmUtil.checkOperator(content.getString("cmd_type")));
        mdmDeviceRead.setOperator("mdm");
	    mdmDeviceRead.setCdate(new Date());
	    if(isInsert(record, mdmDeviceRead)) {
	        mdmDeviceRead.setFuuid(MdmCodeUtil.getReadCode());
	        mdmDeviceReadDao.insertSelective(mdmDeviceRead);
	    }
	    if(!MdmUtil.isDayField(fieldId) && !MdmUtil.isMonthField(fieldId)) {
	        sendCmdProxy.send0112(
	                "0", //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
	                devId,
	                mdmDeviceRead.getDataType(),
	                DateUtil.dateToString(dataTime, DateUtil.DATE_TIME_WITH_LINE),
	                content.getString("value"),
	                mdmDeviceRead.getCmdCode(),
	                "0", //0：VEE校验    1：VEE预估
	                "0",  //数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
	                fieldId
	                );
	    }
	    
    }
    
	
	/**
     * @description 保存到 MDM_DEVICE_SET
     * @param jobj
     * @throws Exception
     */
    private void SaveToMdmDeviceSet(JSONObject jobj) throws Exception {
        JSONObject content = jobj.getJSONObject("content");
        String devId = content.getString("devId");
        SysDevice paramSysDevice = new SysDevice();
        paramSysDevice.setDevId(devId);
        SysDevice sysDevice = sysDeviceService.detail(paramSysDevice);
        if(sysDevice == null) {
            logger.error("Can not find the device: " + devId);
            return;
        }
        
        Date dateTime = new Date(content.getLong("dataTime"));
        MdmDeviceSet mdmDeviceSet = new MdmDeviceSet();
        mdmDeviceSet.setCmdCode(content.getString("cmdCode"));
        mdmDeviceSet.setDeviceId(content.getString("devId"));
        mdmDeviceSet.setDeviceNum(sysDevice.getDevNo());
//      mdmDeviceSet.setParentNum(sysDevice.getParentName());
        mdmDeviceSet.setDeptId(sysDevice.getDeptId());
        mdmDeviceSet.setFieldId(content.getString("fieldId"));
        mdmDeviceSet.setFieldValue(content.getString("value"));
        mdmDeviceSet.setOrgId(content.getString("orgId"));
        mdmDeviceSet.setSetTime(dateTime);
        mdmDeviceSet.setStartTime(dateTime);
        mdmDeviceSet.setFinishTime(dateTime);
        mdmDeviceSet.setAgentCode(MdmUtil.checkOperator(content.getString("cmd_type")));
        mdmDeviceSet.setOperator("mdm");
        String lastDeviceSet = mdmDeviceSetDao.selectLastDeviceSet(mdmDeviceSet);
        if(StringUtils.isNotBlank(lastDeviceSet)) {
            mdmDeviceSet.setOldFieldValue(lastDeviceSet);
        }else {
            String lastDeviceRead = mdmDeviceSetDao.selectLastDeviceRead(mdmDeviceSet);
            if(StringUtils.isNotBlank(lastDeviceRead)) {
                mdmDeviceSet.setOldFieldValue(lastDeviceRead);
            }else {
                mdmDeviceSet.setOldFieldValue("-"); 
            }
        }
        mdmDeviceSet.setCdate(new Date());
        mdmDeviceSet.setFuuid(MdmCodeUtil.getReadCode());
        mdmDeviceSetDao.insertSelective(mdmDeviceSet);
        
    }
    
    /**
     * @description 保存到 MDM_DEVICE_EVENT
     * @param jobj
     * @throws Exception
     */
    private void SaveToMdmDeviceEvent(JSONObject jobj) throws Exception {
        JSONObject content = jobj.getJSONObject("content");
        String devId = content.getString("devId");
        SysDevice paramSysDevice = new SysDevice();
        paramSysDevice.setDevId(devId);
        SysDevice sysDevice = sysDeviceService.detail(paramSysDevice);
        if(sysDevice == null) {
            logger.error("Can not find the device: " + devId);
            return;
        }
        
        Date dateTime = new Date(content.getLong("dataTime"));
//        Date dateTime = DateUtil.stringToDate(content.getLong("dataTime").toString(), DateUtil.DATE_TIME_WITH_LINE);
        MdmDeviceEvent mdmDeviceEvent = new MdmDeviceEvent();
        mdmDeviceEvent.setEventTime(dateTime);
        mdmDeviceEvent.setDeviceId(content.getString("devId"));
        mdmDeviceEvent.setDeviceAddr(content.getString("devAddr"));
        mdmDeviceEvent.setDeviceNum(sysDevice.getDevNo());
        mdmDeviceEvent.setDeptId(sysDevice.getDeptId());
        mdmDeviceEvent.setEventType(Integer.valueOf(content.getString("eventType")));
        mdmDeviceEvent.setOrgId(content.getString("orgId"));
        mdmDeviceEvent.setUpTime(dateTime);
        mdmDeviceEvent.setAppRcvStatus("0");
        mdmDeviceEvent.setUpOperator("mdm");
        mdmDeviceEvent.setCdate(new Date());
        mdmDeviceEvent.setId(MdmCodeUtil.getEventCode());
        mdmDeviceEventDao.insertSelective(mdmDeviceEvent);
        MdmSysEventType mdmSysEventType = mdmSysEventTypeDao.selectByPrimaryKey(mdmDeviceEvent.getEventType());
        MdmEventAutoProcess.autoProcess(mdmDeviceEvent.getDeviceAddr(), mdmSysEventType.getRefCode(), mdmDeviceEvent.getId(), dateTime);
    }
    
    private boolean isInsert(List<String> list, MdmDeviceRead mdmDeviceRead){
        try {
            String time = DateUtil.dateToString(mdmDeviceRead.getReadTime(), DateUtil.DATE_TIME_WITH_LINE);
            String value = time + mdmDeviceRead.getDeptId() + mdmDeviceRead.getFieldId();
            if(list.contains(value)){
                return false;
            }
            else{
                list.add(value);
                return true;
            }
        } 
        catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }
    
}
