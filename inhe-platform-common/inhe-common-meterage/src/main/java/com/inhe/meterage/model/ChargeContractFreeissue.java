package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeContractFreeissue {
	
	private String id;

	private String orgId;

	private String deptId;

	private String custId;

	private String contractNo;

	private String devId;

	private String devNum;

	private BigDecimal qty;

	private String reasonCode;

	private String remark;

	private String chargeToken;
	
	private String tokenStatus;

	private String status;

	private Date cdate;

	private String coperator;

	private Date udate;

	private String uoperator;

	private String checkNotes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChargeToken() {
		return chargeToken;
	}

	public void setChargeToken(String chargeToken) {
		this.chargeToken = chargeToken;
	}

	public String getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
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

	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getUoperator() {
		return uoperator;
	}

	public void setUoperator(String uoperator) {
		this.uoperator = uoperator;
	}

	public String getCheckNotes() {
		return checkNotes;
	}

	public void setCheckNotes(String checkNotes) {
		this.checkNotes = checkNotes;
	}
}