package com.inhe.hes.model;

import java.util.Date;

public class HesDeviceDiscovered {
	
	private Integer dataLevel;

	private String devAddr;
	    
    private Date discoverTime;

    private String ip;

    private Integer port;

    private String parentAddr1;

    private String parentAddr2;

    private String regId;

    private Date regTime;
    
    //add 2020-08-27前台显示
    private String orgId;
    
    //add 2020-08-27前台显示
    private String deptId;
    
    //add 2020-08-27前台显示
    private String devType;
    
    //add 2020-08-27前台显示
    private String status;

    public Integer getDataLevel() {
		return dataLevel;
	}

	public void setDataLevel(Integer dataLevel) {
		this.dataLevel = dataLevel;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public Date getDiscoverTime() {
        return discoverTime;
    }

    public void setDiscoverTime(Date discoverTime) {
        this.discoverTime = discoverTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getParentAddr1() {
        return parentAddr1;
    }

    public void setParentAddr1(String parentAddr1) {
        this.parentAddr1 = parentAddr1;
    }

    public String getParentAddr2() {
        return parentAddr2;
    }

    public void setParentAddr2(String parentAddr2) {
        this.parentAddr2 = parentAddr2;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}