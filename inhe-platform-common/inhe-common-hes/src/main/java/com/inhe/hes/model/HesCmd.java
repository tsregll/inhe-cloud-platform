package com.inhe.hes.model;

import java.util.Date;

public class HesCmd {
	
    private Integer dataType;

    private Date cmdTime;

    private String cmdCode;
    
    private String orgId;

    private String deptId;

    private Integer cmdType;

    private String refId;

    private String parentId;

    private String devId;

    private String devNum;

    private String deviceIp;

    private String fieldId;

    private String commType;

    private String ratio;

    private Integer opStatus;

    private String opReslut;

    private Date opStartTime;

    private Date opReadTime;

    private Date opEndTime;

    private String operator;
    
    private String params;

    private String opDetail;

    private String opText;

   
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Date getCmdTime() {
        return cmdTime;
    }

    public void setCmdTime(Date cmdTime) {
        this.cmdTime = cmdTime;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
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

	public Integer getCmdType() {
        return cmdType;
    }

    public void setCmdType(Integer cmdType) {
        this.cmdType = cmdType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Integer getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(Integer opStatus) {
        this.opStatus = opStatus;
    }

    public String getOpReslut() {
        return opReslut;
    }

    public void setOpReslut(String opReslut) {
        this.opReslut = opReslut;
    }

    public Date getOpStartTime() {
        return opStartTime;
    }

    public void setOpStartTime(Date opStartTime) {
        this.opStartTime = opStartTime;
    }

    public Date getOpReadTime() {
        return opReadTime;
    }

    public void setOpReadTime(Date opReadTime) {
        this.opReadTime = opReadTime;
    }

    public Date getOpEndTime() {
        return opEndTime;
    }

    public void setOpEndTime(Date opEndTime) {
        this.opEndTime = opEndTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getOpDetail() {
        return opDetail;
    }

    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    public String getOpText() {
        return opText;
    }

    public void setOpText(String opText) {
        this.opText = opText;
    }
    
}