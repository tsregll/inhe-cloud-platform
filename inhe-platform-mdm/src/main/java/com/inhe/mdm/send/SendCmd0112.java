package com.inhe.mdm.send;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.config.InstanceIdConfig;
import com.inhe.build.rocketmq.ProducerNode;
import com.inhe.utils.CodeUtil;


/**
 * @description 数据校验(普通)
 * @author guhf
 * @date 2021/01/06
 */
@Service
public class SendCmd0112 {
	
	private static final Logger logger = LoggerFactory.getLogger(SendCmd0112.class);
	
	@Autowired
    private InstanceIdConfig instanceIdConfig;
    
    @Value("${spring.application.name}")
    private String serviceName;
	
	@Autowired
	ProducerNode producerNode;
	
	//VEE 账单数据、瞬时数据、曲线数据
	public void send(String function, String deviceId, String dataType, String time, 
	        String data, String sourceCode, String sort, String type, String cmd) {
	    try {
            JSONObject content= new JSONObject();
            content.put("device_id", deviceId);
            content.put("cmd", cmd);
            content.put("data_type", dataType);
            content.put("time", time);
            content.put("data", data);
            content.put("source", sourceCode);
            
            JSONObject rootJson = new JSONObject();
            rootJson.put("cmd", "$0112");
            rootJson.put("function", function); //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
            rootJson.put("sort", sort);//0：VEE校验    1：VEE预估
            rootJson.put("type", type);//数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
            rootJson.put("source", serviceName + "_" + instanceIdConfig.getInstanceId());
            rootJson.put("content",content);
            rootJson.put("timestamp", System.currentTimeMillis());
            rootJson.put("sign", DigestUtils.md5Hex(rootJson.toString()));
            
            Message msg_obj = new Message(
                "ws_node",                           // topic
                serviceName,                         // tag
                CodeUtil.getMqCode(),                // key用于标识业务的唯一性
                rootJson.toString().getBytes()       // body
            );
            SendResult result = producerNode.send(msg_obj);
            if(result.getSendStatus().equals(SendStatus.SEND_OK)){
                if("0".equals(sort)) {
                    logger.info("send vee validation success. "+rootJson.toString());
                }else if("1".equals(sort)) {
                    logger.info("send vee estimation success. "+rootJson.toString());
                }else if("2".equals(sort)) {
                    logger.info("send vee edit success. "+rootJson.toString());
                }
            }
        } catch (Exception e) {
			logger.error("send 0112 fail.", e);
		}
		
	}
	
	//VEE结果数据入库freezing、kwhDetail
    public void sendSave(String function, String deviceId,String cmd, String dataType, String time, 
            String value, String sort, String type) {
        try {
            JSONObject content= new JSONObject();
            content.put("device_id", deviceId);
            content.put("data_type", dataType);
            content.put("time", time);
            content.put("cmd", cmd);
            content.put("result", value);
            
            JSONObject rootJson = new JSONObject();
            rootJson.put("cmd", "$0112");
            rootJson.put("function", function); //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
            rootJson.put("sort", sort);//0：VEE校验    1：VEE预估
            rootJson.put("type", type);//数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
            rootJson.put("source", serviceName + "_" + instanceIdConfig.getInstanceId());
            rootJson.put("content",content);
            rootJson.put("timestamp", System.currentTimeMillis());
            rootJson.put("sign", DigestUtils.md5Hex(rootJson.toString()));
            
            Message msg_obj = new Message(
                "ws_node",                           // topic
                serviceName,                         // tag
                CodeUtil.getMqCode(),                // key用于标识业务的唯一性
                rootJson.toString().getBytes()       // body
            );
            SendResult result = producerNode.send(msg_obj);
            if(result.getSendStatus().equals(SendStatus.SEND_OK)){
                if("0".equals(sort)) {
                    logger.info("send vee validation success. "+rootJson.toString());
                }else if("1".equals(sort)) {
                    logger.info("send vee estimation success. "+rootJson.toString());
                }else if("2".equals(sort)) {
                    logger.info("send vee edit success. "+rootJson.toString());
                }
            }
        } catch (Exception e) {
            logger.error("send 0112 save fail.", e);
        }
        
    }
}
