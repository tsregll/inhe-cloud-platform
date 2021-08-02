package com.inhe.admin.model;

public class SysButton {

	private String btnId;

	private String btnName;

	private String action;
	
	private String remark;

	public String getBtnId() {
		return btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}
	
	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysButton [btnId=" + btnId + ", btnName=" + btnName + ", action=" + action + ", remark=" + remark + "]";
	}
	
}