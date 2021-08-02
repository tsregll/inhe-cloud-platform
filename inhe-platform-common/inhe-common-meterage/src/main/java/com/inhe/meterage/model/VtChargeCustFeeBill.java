package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class VtChargeCustFeeBill {
	
	private String billId;
	
	private String feeId;
	
	private String orgId;
	
	private String deptId;
	
	private String custId;
	
	private String contractNo;
	
	private String devId;
	
	private String tariffId;
	
	private Integer tariffVersion;
	
	private Date billStartTime;
	
	private Date billEndTime;
	
	private String billPeriod;
	
	private String billStatus;
	
	private Integer payCount;
	
	private BigDecimal feeMoney;
	
	private BigDecimal feeVat;
	
	private String coperator;
	
	private Date cdate;
	
	private Date udate;
	
	private String custName;
	
	private String custNo;
	
	private String feeName;
	
	private String payType;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
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

	public String getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}