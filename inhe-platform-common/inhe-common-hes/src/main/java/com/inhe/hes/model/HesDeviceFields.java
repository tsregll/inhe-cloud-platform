package com.inhe.hes.model;

import java.util.Date;

public class HesDeviceFields{
	
    private String devId;

    private String fieldId;
    
    private String fieldValue;

    private String ratio;

    private String ratioValue;

    private String cmdCode;

    private Date cmdTime;

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
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

	public String getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	public Date getCmdTime() {
		return cmdTime;
	}

	public void setCmdTime(Date cmdTime) {
		this.cmdTime = cmdTime;
	}

   
}