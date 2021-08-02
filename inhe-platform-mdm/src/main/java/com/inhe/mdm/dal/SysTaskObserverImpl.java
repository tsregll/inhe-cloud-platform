package com.inhe.mdm.dal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.inhe.node.observer.IObserverTask;

@Component
public class SysTaskObserverImpl implements IObserverTask {
    
    private static final Logger logger = LoggerFactory.getLogger(SysTaskObserverImpl.class);
	
    @Autowired
    Process001Impl process001Service;
    
    @Autowired
    Process003Impl process003Service;
    
	@Autowired
	Process004Impl process004Service; 
	
	@Autowired
    Process005Impl process005Service;
	
	@Autowired
    Process006Impl process006Service; 
    
    @Autowired
    Process007Impl process007Service;
	
	@Autowired
    Process008Impl process008Service; 
    
    @Autowired
    Process009Impl process009Service;
	
	@Autowired
    Process065Impl process065Service;
	
	@Override
	public JSONObject exec(JSONObject content) {
	    logger.info("task content: "+content.toJSONString());
	    try {
	    	if("001".equals(content.getString("code"))){
                process001Service.execute(content);
            }else if("003".equals(content.getString("code"))){
                process003Service.execute(content);
            }else if("004".equals(content.getString("code"))){
                process004Service.execute(content);
            }else if("005".equals(content.getString("code"))){
                process005Service.execute(content);
            }else if("006".equals(content.getString("code"))){
                process006Service.execute(content);
            }else if("007".equals(content.getString("code"))){
                process007Service.execute(content);
            }else if("008".equals(content.getString("code"))){
                process008Service.execute(content);
            }else if("009".equals(content.getString("code"))){
                process009Service.execute(content);
            }else if("065".equals(content.getString("code"))){
                process065Service.execute(content);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
		
		JSONObject result = new JSONObject();
		result.put("state", "S");
		return result;
	}

	@Override
	public String getId() {
		return "SYS_TASK";
	}

	@Override
	public void register() {
	
	}
}
