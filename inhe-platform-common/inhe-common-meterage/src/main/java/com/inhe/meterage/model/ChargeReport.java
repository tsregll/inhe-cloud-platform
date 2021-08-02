package com.inhe.meterage.model;

import java.math.BigDecimal;

public class ChargeReport {

	private String reportDate;
	
	private BigDecimal totalMoney;
	
	private BigDecimal accountRechargeMoney;
	
	private BigDecimal payBillMoney;
	
	private BigDecimal prePayMoney;
	
	private BigDecimal refundedMoney;

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getAccountRechargeMoney() {
		return accountRechargeMoney;
	}

	public void setAccountRechargeMoney(BigDecimal accountRechargeMoney) {
		this.accountRechargeMoney = accountRechargeMoney;
	}

	public BigDecimal getPayBillMoney() {
		return payBillMoney;
	}

	public void setPayBillMoney(BigDecimal payBillMoney) {
		this.payBillMoney = payBillMoney;
	}

	public BigDecimal getPrePayMoney() {
		return prePayMoney;
	}

	public void setPrePayMoney(BigDecimal prePayMoney) {
		this.prePayMoney = prePayMoney;
	}

	public BigDecimal getRefundedMoney() {
		return refundedMoney;
	}

	public void setRefundedMoney(BigDecimal refundedMoney) {
		this.refundedMoney = refundedMoney;
	}

}
