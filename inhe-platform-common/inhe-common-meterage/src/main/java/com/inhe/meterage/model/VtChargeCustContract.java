package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class VtChargeCustContract {
	
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

    private String lastPurchaseDate;
    
    private BigDecimal lastPayAccount;

    private BigDecimal refundedAmount;

    private String contractStatus;
    
    private String lockStatus;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;

    private String custNo;
    
    private String custName;
    
    private String custCombo;
    
    private String custTel;
    
    private Date openTime;
    
    private String custAddr;

    private BigDecimal lastYgZsd;
    
    private BigDecimal thisYgZsd;
    
    private String dictName;
    
    private String billId;
    
    private BigDecimal consumptionMoney;
    
    private BigDecimal accountBalance;
    
    private BigDecimal consumptionVat;
    
    private BigDecimal calcAmount;
    
    private String tariffName;
    
    private String refNo;
    
    private String execType;
    
    private String execValue;
    
    private String billMonth;

    private String controlType;
    
    private String controlReason;
    
    private String controlStatus;
    
    private String subscribeDetail;
    
    private String messageType;
    
    private String messageContent;
    
    private String sendTime;
    
    private String freezeState;
    
    private String ygZsd;
    
    private BigDecimal ygFl1Sd;

    private BigDecimal ygFl2Sd;

    private BigDecimal ygFl3Sd;

    private BigDecimal ygFl4Sd;
    
    private String dataState;
    
    private String meterDataId;
    
    private String dataDate;
    
    private BigDecimal overDraftMoney;
    
    private BigDecimal refundedMoney;
    
    private Integer periodNo;

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

	public String getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(String lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
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

	public String getCustCombo() {
		return custCombo;
	}

	public void setCustCombo(String custCombo) {
		this.custCombo = custCombo;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public BigDecimal getLastYgZsd() {
		return lastYgZsd;
	}

	public void setLastYgZsd(BigDecimal lastYgZsd) {
		this.lastYgZsd = lastYgZsd;
	}

	public BigDecimal getThisYgZsd() {
		return thisYgZsd;
	}

	public void setThisYgZsd(BigDecimal thisYgZsd) {
		this.thisYgZsd = thisYgZsd;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getConsumptionVat() {
		return consumptionVat;
	}

	public void setConsumptionVat(BigDecimal consumptionVat) {
		this.consumptionVat = consumptionVat;
	}

	public BigDecimal getCalcAmount() {
		return calcAmount;
	}

	public void setCalcAmount(BigDecimal calcAmount) {
		this.calcAmount = calcAmount;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}

	public String getExecValue() {
		return execValue;
	}

	public void setExecValue(String execValue) {
		this.execValue = execValue;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getControlReason() {
		return controlReason;
	}

	public void setControlReason(String controlReason) {
		this.controlReason = controlReason;
	}

	public String getControlStatus() {
		return controlStatus;
	}

	public void setControlStatus(String controlStatus) {
		this.controlStatus = controlStatus;
	}

	public String getSubscribeDetail() {
		return subscribeDetail;
	}

	public void setSubscribeDetail(String subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getFreezeState() {
		return freezeState;
	}

	public void setFreezeState(String freezeState) {
		this.freezeState = freezeState;
	}

	public String getYgZsd() {
		return ygZsd;
	}

	public void setYgZsd(String ygZsd) {
		this.ygZsd = ygZsd;
	}

	public BigDecimal getYgFl1Sd() {
		return ygFl1Sd;
	}

	public void setYgFl1Sd(BigDecimal ygFl1Sd) {
		this.ygFl1Sd = ygFl1Sd;
	}

	public BigDecimal getYgFl2Sd() {
		return ygFl2Sd;
	}

	public void setYgFl2Sd(BigDecimal ygFl2Sd) {
		this.ygFl2Sd = ygFl2Sd;
	}

	public BigDecimal getYgFl3Sd() {
		return ygFl3Sd;
	}

	public void setYgFl3Sd(BigDecimal ygFl3Sd) {
		this.ygFl3Sd = ygFl3Sd;
	}

	public BigDecimal getYgFl4Sd() {
		return ygFl4Sd;
	}

	public void setYgFl4Sd(BigDecimal ygFl4Sd) {
		this.ygFl4Sd = ygFl4Sd;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public String getMeterDataId() {
		return meterDataId;
	}

	public void setMeterDataId(String meterDataId) {
		this.meterDataId = meterDataId;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public BigDecimal getOverDraftMoney() {
		return overDraftMoney;
	}

	public void setOverDraftMoney(BigDecimal overDraftMoney) {
		this.overDraftMoney = overDraftMoney;
	}

	public BigDecimal getRefundedMoney() {
		return refundedMoney;
	}

	public void setRefundedMoney(BigDecimal refundedMoney) {
		this.refundedMoney = refundedMoney;
	}

	public Integer getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}
    
}