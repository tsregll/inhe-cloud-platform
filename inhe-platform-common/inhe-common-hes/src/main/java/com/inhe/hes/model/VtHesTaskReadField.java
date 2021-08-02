package com.inhe.hes.model;

import java.util.List;

public class VtHesTaskReadField  {
	
	private String taskId;
	
	private List<HesTaskReadField> fields;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<HesTaskReadField> getFields() {
		return fields;
	}

	public void setFields(List<HesTaskReadField> fields) {
		this.fields = fields;
	}

    
}