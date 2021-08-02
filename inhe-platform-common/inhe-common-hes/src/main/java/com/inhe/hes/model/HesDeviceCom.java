package com.inhe.hes.model;

import java.util.HashMap;
import java.util.Map;

public class HesDeviceCom {
	
	private String devId;

    private String devName;

    private String orgId;

    private String deptId;
    
    private Integer mPoint;

    private String mPointCompare;

    private Integer nwStatus;

    private String ratio;
    
    private String prodId;

    private String prodType;

    private String commType;

    private String commAddr;

    private String commPassword;
    
    private String ip;

    private Integer port;

    private String parentId;
    
    private String parentName;

    private String taskId;
    
    private String online;
    
    private String conAddr;

    private Boolean canExecmd = true;
    
    private Map<String, String> pMap = new HashMap<String, String>();
    
    private Integer maxPoint = 0;
    
    private String timeZone;
    
    private Integer offsetTime;
    
	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
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

	public Integer getmPoint() {
		return mPoint;
	}

	public void setmPoint(Integer mPoint) {
		this.mPoint = mPoint;
	}

	public String getmPointCompare() {
		return mPointCompare;
	}

	public void setmPointCompare(String mPointCompare) {
		this.mPointCompare = mPointCompare;
	}

	public Integer getNwStatus() {
		return nwStatus;
	}

	public void setNwStatus(Integer nwStatus) {
		this.nwStatus = nwStatus;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}

	public String getCommAddr() {
		return commAddr;
	}

	public void setCommAddr(String commAddr) {
		this.commAddr = commAddr;
	}

	public String getCommPassword() {
		return commPassword;
	}

	public void setCommPassword(String commPassword) {
		this.commPassword = commPassword;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getConAddr() {
		return conAddr;
	}

	public void setConAddr(String conAddr) {
		this.conAddr = conAddr;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public Boolean getCanExecmd() {
		return canExecmd;
	}

	public void setCanExecmd(Boolean canExecmd) {
		this.canExecmd = canExecmd;
	}

	public Map<String, String> getpMap() {
		return pMap;
	}

	public void putPoints(String key, String value) {
		this.pMap.put(key, value);
	}

	public Integer getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getOffsetTime() {
		return offsetTime;
	}

	public void setOffsetTime(Integer offsetTime) {
		this.offsetTime = offsetTime;
	}
	
}