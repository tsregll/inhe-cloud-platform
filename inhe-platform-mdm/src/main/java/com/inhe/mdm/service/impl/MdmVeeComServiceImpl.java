/**
 * @author pfr 2020-12-15
 */
package com.inhe.mdm.service.impl;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.build.rocketmq.ProducerNode;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.utils.CodeUtil;
import com.inhe.utils.DateUtil;

@Component
public class MdmVeeComServiceImpl {
    
    private static final Logger logger = LoggerFactory.getLogger(MdmVeeComServiceImpl.class);

    @Value("${spring.application.name}")
    private String serverName;
    
    @Autowired
    ProducerNode producerNode;
	
	@Autowired
	MdmVeeConfigDao mdmVeeConfigDao;
	
	@Autowired
	InheRedisTemplate redisTemplate;
	
	@Autowired
    SendCmdProxy sendCmdProxy;
	
	public String parseType(String dataType,String cmd) {
	    String type=dataType;
		if( checkDemandCmd(cmd) ||"4".equals(dataType)||"6".equals(dataType)) {//账单或需量   
			type = "05";
		}else
		if("2".equals(dataType)) { //曲线
			type = "06";
		}else
		if("1".equals(dataType)) { //瞬时数据
			type = "07";
		}
		return type;
	}
	
	public Double getVeeData(String realValue) {//获取数据  需量数据需要去除时间
		Double currentValue = null;
		if(realValue == null) {
		    return currentValue;
		}
		try{
		    String[] realValueArr = realValue.split(";");
		    if(realValueArr.length>1) {//最大需量;时间
				currentValue = Double.parseDouble(realValueArr[0]);
			}else {
				currentValue = Double.parseDouble(realValue);
			}   
		}
		catch(Exception e) {
			e.printStackTrace();
	   }
    	return currentValue;	 
	}
	
	public Boolean checkDemandCmd(String cmd) {
	   Boolean iResult = false;
       if (cmd!=null &&cmd.startsWith("04")&&("106".equals(cmd.substring(cmd.length()-3,cmd.length()))||"109".equals(cmd.substring(cmd.length()-1,cmd.length())))){
    	   iResult = true; 
       } 
	   return iResult;
	}
	
	//查询抄表上一次值
	public Double getReadLast(String deviceNum,String dataType,String cmd,Date time) throws Exception {
		MdmDeviceRead qry = new MdmDeviceRead();
		qry.setDataType(dataType);
		qry.setDeviceNum(deviceNum);
		qry.setReadTime(time);
		qry.setFieldId(cmd);
		MdmDeviceRead amrRead = mdmVeeConfigDao.selectByLastTime(qry);
		Double lastValue = -1d;
		if(amrRead!=null) {
			String realValue = amrRead.getRealValue();
			if(realValue.split(";").length>1) {//最大需量;时间
				lastValue = Double.parseDouble(realValue.split(";")[0]);
			}else {
				lastValue =Double.parseDouble(realValue);
			}
		}
		return lastValue;
	}

	
	//查询上一次需量值
	public Double getReqReadLast(String deviceCode,String dataType,String cmd,Date sTime,Date eTime) throws Exception {
		MdmDeviceRead qry = new MdmDeviceRead();
		qry.setDataType(dataType);
		qry.setDeviceId(deviceCode);
		qry.setFieldId(cmd);
		qry.setStartTime(sTime);
		qry.setFinishTime(eTime);
//		qry.setReadTime(time);
		//qry.setFuuid(time);//借用
		qry.setFieldId(cmd);
		MdmDeviceRead amrRead = mdmVeeConfigDao.selectByReqLastTime(qry);
		Double lastValue = -1d;
		if(amrRead!=null) {
			String realValue = amrRead.getRealValue();
			if(realValue.split(";").length>1) {//最大需量;时间
				lastValue = Double.parseDouble(realValue.split(";")[0]);
			}else {
				lastValue =Double.parseDouble(realValue);
			}
		}
		return lastValue;
	}
	
	//发送消息
    public void sendMsg(String type,MdmAmDevice device, Date time, String amt,String cmd,String refCode) throws Exception {
      JSONObject param = new JSONObject();
      param.put("deviceNum", device.getDeviceNum());//计量点
      param.put("time", DateUtil.dateToString(time, "yyyy-MM-dd HH:mm:ss"));
      param.put("amt", amt);//当前校验值
      param.put("fieldName", cmd);//当前校验值
      String data = param.toJSONString();
      sendCmdProxy.send0901Simple(device.getOrgId(), type, data, refCode);
    }
	
	public void send0112Save(String function,JSONObject rootJson) throws Exception {
	    try {
	        rootJson.put("function", function);
            rootJson.put("sign", DigestUtils.md5Hex(rootJson.toString()));
            Message msg_obj = new Message(
                "ws_node",                   
                serverName,                      
                CodeUtil.getMqCode(),                
                rootJson.toString().getBytes()       
            );
            SendResult result = producerNode.send(msg_obj);
            if(result.getSendStatus().equals(SendStatus.SEND_OK)){
                
            }
        } catch (Exception e) {
            logger.error("send 0112 fail.", e);
        } 	
	}   

}
