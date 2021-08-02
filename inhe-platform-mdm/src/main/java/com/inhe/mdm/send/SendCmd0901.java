package com.inhe.mdm.send;

import java.sql.Timestamp;
import java.util.Date;

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
 * @description 发送消息通知
 */
@Service
public class SendCmd0901 {
	private static final Logger logger = LoggerFactory.getLogger(SendCmd0901.class);

	@Value("${spring.application.name}")
    private String serverName;
	
	@Autowired
	ProducerNode producerNode;
	
	@Autowired
    InstanceIdConfig instanceIdConfig;
	
	public void send(String orgId, String ref, String operator, String type, String params, String refCode, 
            String mails, String phones, String users) {
        try {
            JSONObject rootJson=new JSONObject();
            JSONObject content = new JSONObject();
            content.put("org_id", orgId);
            content.put("ref", ref);  
            content.put("operator", operator);
            content.put("type", type);
            content.put("params", params);
            content.put("ref_code", refCode);
            content.put("mails", mails);
            content.put("phones", phones);
            content.put("users", users);
            rootJson.put("cmd", "$0901");
            rootJson.put("source", serverName + "_" + instanceIdConfig.getInstanceId());
            rootJson.put("content", content);
            rootJson.put("timestamp", new Timestamp(new Date().getTime()));
            rootJson.put("sign", DigestUtils.md5Hex(rootJson.toString()));
            Message msg_obj = new Message(
                "ws_node",                           // topic
                "inhe-platform-message",             // tag inhe-platform-message
                CodeUtil.getMqCode(),                // key用于标识业务的唯一性
                rootJson.toString().getBytes()       // body
            );
            SendResult send = producerNode.send(msg_obj);
            if(SendStatus.SEND_OK.equals(send.getSendStatus())){
                logger.info("send cmd 0901 successfully. " + rootJson.toString());
            }
        } catch (Exception e) {
            logger.error("send cmd 0901 failed. ", e);
        }
    }
}
