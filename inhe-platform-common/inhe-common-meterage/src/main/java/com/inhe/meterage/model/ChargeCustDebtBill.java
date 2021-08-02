package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCustDebtBill {
	
	private String billId;

	private String debtId;
	    
    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private Date billStartTime;

    private Date billEndTime;

    private Integer billPeriod;

    private String billStatus;

    private Integer payCount;

    private BigDecimal debtIncludeStampsMoney;

    private BigDecimal debtWithoutStampsMoney;

    private BigDecimal debtRemainMoney;

    private BigDecimal debtVat;

    private BigDecimal stampsMoney;

    private String coperator;

    private Date cdate;

    private Date udate;
    
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
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

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public BigDecimal getDebtIncludeStampsMoney() {
        return debtIncludeStampsMoney;
    }

    public void setDebtIncludeStampsMoney(BigDecimal debtIncludeStampsMoney) {
        this.debtIncludeStampsMoney = debtIncludeStampsMoney;
    }

    public BigDecimal getDebtWithoutStampsMoney() {
        return debtWithoutStampsMoney;
    }

    public void setDebtWithoutStampsMoney(BigDecimal debtWithoutStampsMoney) {
        this.debtWithoutStampsMoney = debtWithoutStampsMoney;
    }

    public BigDecimal getDebtRemainMoney() {
        return debtRemainMoney;
    }

    public void setDebtRemainMoney(BigDecimal debtRemainMoney) {
        this.debtRemainMoney = debtRemainMoney;
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