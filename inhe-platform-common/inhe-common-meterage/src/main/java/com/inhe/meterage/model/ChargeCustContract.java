package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCustContract {
    private String contractNo;

    private String custId;

    private String contractType;

    private String contractName;
    
    private String contractAddress;

    private String orgId;

    private String deptId;

    private String tariffId;

    private String payType;

    private Date startTime;

    private Date endTime;

    private String devId;

    private String devName;

    private String devNum;
    
    private String stmType;
    
    private Integer stmCycle;
    
    private Date stmBaseTime;
    
    private BigDecimal givingAmount;
    
    private BigDecimal givingMoney;
    
    private BigDecimal billGiving;
    
    private BigDecimal billQty;
    
    private BigDecimal billTotal;

    private BigDecimal billPaid;

    private Date lastPurchaseTime;

    private Integer purchaseTimes;

    private BigDecimal lastPayAccount;

    private BigDecimal refundedAmount;

    private String contractStatus;
    
    private String lockStatus;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;

    private String lastPurchaseDate;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String startRegistDate;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String endRegistDate;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String custNoAndName;
    
    // 冻结数据查询 2020-07-30
    private BigDecimal monthQtyStart;
    
    // 冻结数据查询 2020-07-30
    private BigDecimal monthQtyEnd;
    
    // 冻结数据查询 2020-07-30
    private BigDecimal monthQtyUsage;
    
    private Integer monthNo;
    
    private String custNo;
    
    private String custName;
    
    private String outages; // 当超过透支金额时是否中断使用: 0-否, 1-是
    
    private String overdraft; // 透支金额

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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
	
	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getStmType() {
		return stmType;
	}

	public void setStmType(String stmType) {
		this.stmType = stmType;
	}

	public Integer getStmCycle() {
		return stmCycle;
	}

	public void setStmCycle(Integer stmCycle) {
		this.stmCycle = stmCycle;
	}

	public Date getStmBaseTime() {
		return stmBaseTime;
	}

	public void setStmBaseTime(Date stmBaseTime) {
		this.stmBaseTime = stmBaseTime;
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

	public BigDecimal getBillGiving() {
		return billGiving;
	}

	public void setBillGiving(BigDecimal billGiving) {
		this.billGiving = billGiving;
	}

	public BigDecimal getBillQty() {
		return billQty;
	}

	public void setBillQty(BigDecimal billQty) {
		this.billQty = billQty;
	}

	public BigDecimal getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(BigDecimal billTotal) {
		this.billTotal = billTotal;
	}

	public BigDecimal getBillPaid() {
		return billPaid;
	}

	public void setBillPaid(BigDecimal billPaid) {
		this.billPaid = billPaid;
	}

	public Date getLastPurchaseTime() {
		return lastPurchaseTime;
	}

	public void setLastPurchaseTime(Date lastPurchaseTime) {
		this.lastPurchaseTime = lastPurchaseTime;
	}

	public Integer getPurchaseTimes() {
		return purchaseTimes;
	}

	public void setPurchaseTimes(Integer purchaseTimes) {
		this.purchaseTimes = purchaseTimes;
	}

	public BigDecimal getLastPayAccount() {
		return lastPayAccount;
	}

	public void setLastPayAccount(BigDecimal lastPayAccount) {
		this.lastPayAccount = lastPayAccount;
	}

	public BigDecimal getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(BigDecimal refundedAmount) {
		this.refundedAmount = refundedAmount;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(String lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

	public String getStartRegistDate() {
		return startRegistDate;
	}

	public void setStartRegistDate(String startRegistDate) {
		this.startRegistDate = startRegistDate;
	}

	public String getEndRegistDate() {
		return endRegistDate;
	}

	public void setEndRegistDate(String endRegistDate) {
		this.endRegistDate = endRegistDate;
	}

	public String getCustNoAndName() {
		return custNoAndName;
	}

	public void setCustNoAndName(String custNoAndName) {
		this.custNoAndName = custNoAndName;
	}

	public BigDecimal getMonthQtyStart() {
		return monthQtyStart;
	}

	public void setMonthQtyStart(BigDecimal monthQtyStart) {
		this.monthQtyStart = monthQtyStart;
	}

	public BigDecimal getMonthQtyEnd() {
		return monthQtyEnd;
	}

	public void setMonthQtyEnd(BigDecimal monthQtyEnd) {
		this.monthQtyEnd = monthQtyEnd;
	}

	public BigDecimal getMonthQtyUsage() {
		return monthQtyUsage;
	}

	public void setMonthQtyUsage(BigDecimal monthQtyUsage) {
		this.monthQtyUsage = monthQtyUsage;
	}

	public Integer getMonthNo() {
		return monthNo;
	}

	public void setMonthNo(Integer monthNo) {
		this.monthNo = monthNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getOutages() {
		return outages;
	}

	public void setOutages(String outages) {
		this.outages = outages;
	}

	public String getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(String overdraft) {
		this.overdraft = overdraft;
	}
    
}