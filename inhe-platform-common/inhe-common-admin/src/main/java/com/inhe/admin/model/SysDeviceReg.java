package com.inhe.admin.model;

import java.util.Date;

public class SysDeviceReg {
	
	private String devId;

	private String deployId;
	
	private String addr;
	
    private Date cdate;
    
    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId == null ? null : devId.trim();
    }

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId == null ? null : deployId.trim();
    }
    
    public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}