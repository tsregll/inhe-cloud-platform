package com.inhe.hes.model;

import java.util.Date;

public class HesNwDevice {
    private String nwId;

    private String devId;

    private String devAddr;
    
    private String devPtl;

    private Integer devPoint;

    private Short devLevel;

    private Date refreshTime;

    private String devPath;
    
    //add 模块版本
    private String devVersion;
    
    private Boolean isRead;

    public String getNwId() {
        return nwId;
    }

    public void setNwId(String nwId) {
        this.nwId = nwId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }
    
    public String getDevPtl() {
		return devPtl;
	}

	public void setDevPtl(String devPtl) {
		this.devPtl = devPtl;
	}

	public Integer getDevPoint() {
        return devPoint;
    }

    public void setDevPoint(Integer devPoint) {
        this.devPoint = devPoint;
    }

    public Short getDevLevel() {
        return devLevel;
    }

    public void setDevLevel(Short devLevel) {
        this.devLevel = devLevel;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getDevPath() {
        return devPath;
    }

    public void setDevPath(String devPath) {
        this.devPath = devPath;
    }

	public String getDevVersion() {
		return devVersion;
	}

	public void setDevVersion(String devVersion) {
		this.devVersion = devVersion;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
}