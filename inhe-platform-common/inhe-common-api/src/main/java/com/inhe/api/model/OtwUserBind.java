package com.inhe.api.model;

import java.util.Date;

public class OtwUserBind {
	
	private String userId;

    private String contractNo;
    
    private String contractType;
    
    private String contractName;
    
    private String prodId;

    private String vendorsId;

    private String defaults;

    private String devNum;

    private String custTel;

    private String custName;

    private String custAddr;

    private String status;

    private Date cdate;

    private Date udate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getVendorsId() {
		return vendorsId;
	}

	public void setVendorsId(String vendorsId) {
		this.vendorsId = vendorsId;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
}