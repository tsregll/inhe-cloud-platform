package com.inhe.meterage.model;

import java.math.BigDecimal;

public class VtPlPayParam {
	
	private String contractNo;

    private String payType;

    private String chargeType;
    
    private String billIds;

    private BigDecimal chargeMoney;
    
    private String chargeResult;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBillIds() {
		return billIds;
	}

	public void setBillIds(String billIds) {
		this.billIds = billIds;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public BigDecimal getChargeMoney() {
		return chargeMoney;
	}

	public void setChargeMoney(BigDecimal chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getChargeResult() {
		return chargeResult;
	}

	public void setChargeResult(String chargeResult) {
		this.chargeResult = chargeResult;
	}

   
}