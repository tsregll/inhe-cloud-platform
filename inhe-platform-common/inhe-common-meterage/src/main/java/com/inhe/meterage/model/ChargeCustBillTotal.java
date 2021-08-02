package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCustBillTotal {
    private String billId;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private String devId;

    private String tariffId;

    private Integer tariffVersion;

    private Date billStartTime;

    private Date billEndTime;

    private Integer billPeriod;

    private String billStatus;
    
    private Date billDeadline;
    
    private Integer payCount;

    private BigDecimal givingAmount;

    private BigDecimal givingMoney;

    private BigDecimal calcAmount;

    private BigDecimal refundedMoney;

    private BigDecimal vendAmount;

    private BigDecimal totalIncludeStampsMoney;

    private BigDecimal totalWithoutStampsMoney;

    private BigDecimal totalVat;

    private BigDecimal consumptionMoney;

    private BigDecimal consumptionVat;

    private BigDecimal feeMoney;

    private BigDecimal feeVat;

    private BigDecimal debtMoney;

    private BigDecimal debtVat;

    private BigDecimal stampsMoney;

    private String chargeToken;

    private String tokenStatus;

    private String chargeCode;
    
    private Date chargeTime;
    
    private String payType;

    private String coperator;

    private Date cdate;

    private Date udate;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
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

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public Integer getTariffVersion() {
		return tariffVersion;
	}

	public void setTariffVersion(Integer tariffVersion) {
		this.tariffVersion = tariffVersion;
	}

	public Date getBillStartTime() {
		return billStartTime;
	}

	public void setBillStartTime(Date billStartTime) {
		this.billStartTime = billStartTime;
	}

	public Date getBillEndTime() {
		return billEndTime;
	}

	public void setBillEndTime(Date billEndTime) {
		this.billEndTime = billEndTime;
	}

	public Integer getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(Integer billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Date getBillDeadline() {
		return billDeadline;
	}

	public void setBillDeadline(Date billDeadline) {
		this.billDeadline = billDeadline;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public BigDecimal getGivingAmount() {
		return givingAmount;
	}

	public void setGivingAmount(BigDecimal givingAmount) {
		this.givingAmount = givingAmount;
	}

	public BigDecimal getGivingMoney() {
		return givingMoney;
	}

	public void setGivingMoney(BigDecimal givingMoney) {
		this.givingMoney = givingMoney;
	}

	public BigDecimal getCalcAmount() {
		return calcAmount;
	}

	public void setCalcAmount(BigDecimal calcAmount) {
		this.calcAmount = calcAmount;
	}

	public BigDecimal getRefundedMoney() {
		return refundedMoney;
	}

	public void setRefundedMoney(BigDecimal refundedMoney) {
		this.refundedMoney = refundedMoney;
	}

	public BigDecimal getVendAmount() {
		return vendAmount;
	}

	public void setVendAmount(BigDecimal vendAmount) {
		this.vendAmount = vendAmount;
	}

	public BigDecimal getTotalIncludeStampsMoney() {
		return totalIncludeStampsMoney;
	}

	public void setTotalIncludeStampsMoney(BigDecimal totalIncludeStampsMoney) {
		this.totalIncludeStampsMoney = totalIncludeStampsMoney;
	}

	public BigDecimal getTotalWithoutStampsMoney() {
		return totalWithoutStampsMoney;
	}

	public void setTotalWithoutStampsMoney(BigDecimal totalWithoutStampsMoney) {
		this.totalWithoutStampsMoney = totalWithoutStampsMoney;
	}

	public BigDecimal getTotalVat() {
		return totalVat;
	}

	public void setTotalVat(BigDecimal totalVat) {
		this.totalVat = totalVat;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public BigDecimal getConsumptionVat() {
		return consumptionVat;
	}

	public void setConsumptionVat(BigDecimal consumptionVat) {
		this.consumptionVat = consumptionVat;
	}

	public BigDecimal getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(BigDecimal feeMoney) {
		this.feeMoney = feeMoney;
	}

	public BigDecimal getFeeVat() {
		return feeVat;
	}

	public void setFeeVat(BigDecimal feeVat) {
		this.feeVat = feeVat;
	}

	public BigDecimal getDebtMoney() {
		return debtMoney;
	}

	public void setDebtMoney(BigDecimal debtMoney) {
		this.debtMoney = debtMoney;
	}

	public BigDecimal getDebtVat() {
		return debtVat;
	}

	public void setDebtVat(BigDecimal debtVat) {
		this.debtVat = debtVat;
	}

	public BigDecimal getStampsMoney() {
		return stampsMoney;
	}

	public void setStampsMoney(BigDecimal stampsMoney) {
		this.stampsMoney = stampsMoney;
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

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Date getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator;
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