package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesRptFluxHour {
	
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
    
    private String prodType;//添加
    
    private String up;//添加
    
    private String down;//添加 
    
    private String time;//添加
    
    private BigDecimal onlineRate;//添加

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

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(BigDecimal onlineRate) {
		this.onlineRate = onlineRate;
	}
    
}