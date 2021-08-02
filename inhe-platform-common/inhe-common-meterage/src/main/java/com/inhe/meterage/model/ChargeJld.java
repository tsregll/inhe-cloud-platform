package com.inhe.meterage.model;

public class ChargeJld {

	private String jldNo;
	
	private String CustId;

	private String jldName;

	private String jldAddress;

	public String getJldNo() {
		return jldNo;
	}

	public void setJldNo(String jldNo) {
		this.jldNo = jldNo;
	}

	public String getCustId() {
		return CustId;
	}

	public void setCustId(String custId) {
		CustId = custId;
	}

	public String getJldName() {
		return jldName;
	}

	public void setJldName(String jldName) {
		this.jldName = jldName;
	}

	public String getJldAddress() {
		return jldAddress;
	}

	public void setJldAddress(String jldAddress) {
		this.jldAddress = jldAddress;
	}

	@Override
	public String toString() {
		return "ChargeJld [jldNo=" + jldNo + ", CustId=" + CustId + ", jldName=" + jldName + ", jldAddress="
				+ jldAddress + "]";
	}




	

}