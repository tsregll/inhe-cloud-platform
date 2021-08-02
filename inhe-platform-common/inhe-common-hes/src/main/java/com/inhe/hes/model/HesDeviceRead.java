package com.inhe.hes.model;

import java.util.Date;

public class HesDeviceRead {
    private Date dataTime;

    private String fieldId;
    
    private String orgId;

    private String deptId;

    private String devId;
	
	private Integer dataType;

    private String cmdCode;

    private String devNum;
    
    private String parentNum;

    private Date startTime;

    private Date endTime;

    private String fieldValue;

    private String ratio;

    private String ratioValue;

    private String dataSource;

    private String dataOperator;

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}
	
    public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getRatioValue() {
		return ratioValue;
	}

	public void setRatioValue(String ratioValue) {
		this.ratioValue = ratioValue;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataOperator() {
		return dataOperator;
	}

	public void setDataOperator(String dataOperator) {
		this.dataOperator = dataOperator;
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
    

  
}