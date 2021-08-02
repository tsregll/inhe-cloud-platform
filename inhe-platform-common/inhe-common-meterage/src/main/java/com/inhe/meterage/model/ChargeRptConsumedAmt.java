package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeRptConsumedAmt {
	
	private String orgId;

    private String contractNo;

    private Date readTime;

    private String devNum;
	
    private String devId;

    private String amtType;

    private BigDecimal amt;

    private BigDecimal estimateAmt;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getAmtType() {
		return amtType;
	}

	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getEstimateAmt() {
		return estimateAmt;
	}

	public void setEstimateAmt(BigDecimal estimateAmt) {
		this.estimateAmt = estimateAmt;
	}

}