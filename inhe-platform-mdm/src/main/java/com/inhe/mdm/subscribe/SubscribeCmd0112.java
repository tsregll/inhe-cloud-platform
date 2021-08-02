package com.inhe.mdm.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.rocketmq.IMessageProcessorCmd;
import com.inhe.mdm.dal.ProcessVeeResultFreezing;
import com.inhe.mdm.dal.ProcessVeeResultKwh;
import com.inhe.mdm.vee.ProcessVeeCmd0112;

/**
 * @description 数据校验消息订阅
 * @author
 * @date 2020/12/08
 */
@Service
public class SubscribeCmd0112 implements IMessageProcessorCmd {
    
	private static final Logger logger = LoggerFactory.getLogger(SubscribeCmd0112.class);
	
	@Autowired
	ProcessVeeResultFreezing processVeeResultFreezing;
	
	@Autowired
    ProcessVeeResultKwh processVeeResultKwh;
	
	@Autowired
	ProcessVeeCmd0112 processVeeCmd0112;
	
	@Override
	public String process(JSONObject msgBody) {
	    logger.info("$0112 Msg:"+msgBody.toJSONString());
	    try {
	        String function = msgBody.getString("function");
            if("1".equals(function)) {
                processVeeResultFreezing.execute(msgBody);
            }else if("2".equals(function)) {
                processVeeResultKwh.execute(msgBody);
            }else if("0".equals(function)) {
                processVeeCmd0112.process(msgBody);
            }
        } catch (Exception e) {
            logger.error("process vee failed.",e);
        }
	    
		return "0";
	}


}
