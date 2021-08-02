package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VtContract {

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
    
    private Integer purchaseTimes;

    private Date lastPurchaseDate;

    private BigDecimal lastPayAccount;

    private String contractStatus;
    
    private String lockStatus;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;
    
    private Date opTime;
    
    private BigDecimal z0;
    
    private BigDecimal z1;
    
    private BigDecimal z2;
    
    private BigDecimal z3;
    
    private BigDecimal z4;
    
    private List<ChargeDebtContract> debts;
    
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

	public Integer getPurchaseTimes() {
		return purchaseTimes;
	}

	public void setPurchaseTimes(Integer purchaseTimes) {
		this.purchaseTimes = purchaseTimes;
	}

	public Date getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

	public BigDecimal getLastPayAccount() {
		return lastPayAccount;
	}

	public void setLastPayAccount(BigDecimal lastPayAccount) {
		this.lastPayAccount = lastPayAccount;
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

	public List<ChargeDebtContract> getDebts() {
		return debts;
	}

	public void setDebts(List<ChargeDebtContract> debts) {
		this.debts = debts;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public BigDecimal getZ0() {
		return z0;
	}

	public void setZ0(BigDecimal z0) {
		this.z0 = z0;
	}

	public BigDecimal getZ1() {
		return z1;
	}

	public void setZ1(BigDecimal z1) {
		this.z1 = z1;
	}

	public BigDecimal getZ2() {
		return z2;
	}

	public void setZ2(BigDecimal z2) {
		this.z2 = z2;
	}

	public BigDecimal getZ3() {
		return z3;
	}

	public void setZ3(BigDecimal z3) {
		this.z3 = z3;
	}

	public BigDecimal getZ4() {
		return z4;
	}

	public void setZ4(BigDecimal z4) {
		this.z4 = z4;
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