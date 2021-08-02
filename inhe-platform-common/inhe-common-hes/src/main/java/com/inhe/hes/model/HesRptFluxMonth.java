package com.inhe.hes.model;

import java.util.Date;

public class HesRptFluxMonth {
	
	private Date startTime;

    private String orgId;

    private String deptId;

    private String devId;
	
    private Date endTime;

    private Integer downByte;

    private Integer downMsg;

    private Integer upByte;

    private Integer upMsg;

    private Integer durationMin;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDownByte() {
		return downByte;
	}

	public void setDownByte(Integer downByte) {
		this.downByte = downByte;
	}

	public Integer getDownMsg() {
		return downMsg;
	}

	public void setDownMsg(Integer downMsg) {
		this.downMsg = downMsg;
	}

	public Integer getUpByte() {
		return upByte;
	}

	public void setUpByte(Integer upByte) {
		this.upByte = upByte;
	}

	public Integer getUpMsg() {
		return upMsg;
	}

	public void setUpMsg(Integer upMsg) {
		this.upMsg = upMsg;
	}

	public Integer getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(Integer durationMin) {
		this.durationMin = durationMin;
	}
    
}