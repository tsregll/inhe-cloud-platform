package com.inhe.mdm.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhe.mdm.dao.MdmDeviceEventDao;
import com.inhe.mdm.model.MdmDeviceEvent;
import com.inhe.mdm.service.IMdmEventAutoProcessService;

@Service
public class MdmEventAutoProcessServiceImpl implements IMdmEventAutoProcessService {
	
	private final static Logger logger = LoggerFactory.getLogger(MdmEventAutoProcessServiceImpl.class);
	
	@Autowired
	MdmDeviceEventDao deviceEventdao;
	
	@Override
	public void autoProcess(String addr, String refCode, String id, Date eventTime) throws Exception{
		if(refCode == null || "".equals(refCode)){
			return;
		}
		String[] refList = refCode.split(",");
		MdmDeviceEvent deviceEvent = new MdmDeviceEvent();
		deviceEvent.setDeviceAddr(addr);
		deviceEvent.setOpTime(eventTime);
		deviceEvent.setOpContent("Auto Process: " + id);
		deviceEvent.setOpType("0");
		deviceEvent.setOpOperator("mdm");
		deviceEvent.setDisposeFlag("Y");
		for (String ref : refList) {
			try {
				deviceEvent.setEventType(Integer.parseInt(ref));
				deviceEventdao.updateByPrimaryKeySelectives(deviceEvent);
				logger.info("autoProcess success!");
			} 
			catch (Exception e) {
				logger.error("error." + e.toString());
			}
		}
	}

}
