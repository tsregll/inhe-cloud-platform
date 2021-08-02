package com.inhe.hes.model;

public class VtHesTaskRwItems {

	private String taskId;

	private String refType;
	
	private String refId;

	private String refParams;

	private String status;
	
	private String description;
	
	private String flag; // 前端的抄表参数根据这个决定是否显示
	
	private String plugin;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}
	
}