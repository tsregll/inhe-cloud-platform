/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.model;

import java.util.Date;

public class MdmVeeReadQry {
	private String dataType;
	private String fieldId;
	private String deviceId;
	private String deviceNum;
	private String readTime;
	private Date time;
	private String pattern;
	private Date startTime;
	private Date endTime;
	private String stimeStr;
	private String etimeStr;
	private String cmdType;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
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
	public String getCmdType() {
		return cmdType;
	}
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	public String getStimeStr() {
		return stimeStr;
	}
	public void setStimeStr(String stimeStr) {
		this.stimeStr = stimeStr;
	}
	public String getEtimeStr() {
		return etimeStr;
	}
	public void setEtimeStr(String etimeStr) {
		this.etimeStr = etimeStr;
	}
}
