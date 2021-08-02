package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeRptCreditAmt {
	
	private String orgId;

    private String contractNo;

    private Date readTime;
	
    private String devNum;

    private String devId;

    private BigDecimal credit;

    private Integer estimateDays;

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

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public Integer getEstimateDays() {
		return estimateDays;
	}

	public void setEstimateDays(Integer estimateDays) {
		this.estimateDays = estimateDays;
	}

}