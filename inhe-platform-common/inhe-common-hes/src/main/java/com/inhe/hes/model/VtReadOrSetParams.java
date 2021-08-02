package com.inhe.hes.model;
/**
* @author 0785
* @version 创建时间：2020年08月30日 上午9:15:41
* 类说明：实时抄读--》发送指令参数
*/
public class VtReadOrSetParams  {
	
	private String dev;
	
	private String fieldId;
	
	private String params;

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}