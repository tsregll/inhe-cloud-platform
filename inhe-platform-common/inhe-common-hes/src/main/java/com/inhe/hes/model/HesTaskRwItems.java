package com.inhe.hes.model;

public class HesTaskRwItems {

	private String taskId;

	private String refType;
	
	private String refId;

	private String refParams;

	private String status;
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getRefParams() {
		return refParams;
	}

	public void setRefParams(String refParams) {
		this.refParams = refParams;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}