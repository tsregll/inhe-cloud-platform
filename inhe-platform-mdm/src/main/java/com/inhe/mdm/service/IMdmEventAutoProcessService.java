package com.inhe.mdm.service;

import java.util.Date;

public interface IMdmEventAutoProcessService {
    
    public void autoProcess(String addr, String refCode, String id, Date eventTime) throws Exception;
	
}
