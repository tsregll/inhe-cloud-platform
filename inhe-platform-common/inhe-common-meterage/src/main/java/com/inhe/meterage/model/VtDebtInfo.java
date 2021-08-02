package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.List;

public class VtDebtInfo {

	private String payType;
	
	private BigDecimal totalPayDebt;
	
	private BigDecimal totalPayDebtTax;
	
	private BigDecimal stampsMoney;
	
	private String contractNo;
	
	private String orgId;
	
	private String deptId;
	
	private String userId;
	
	private String billId;
	
	private String chargeCode;
	
	private String openCloseId;
	
	private ChargeCustContract contract;
	
	private List<ChargeDebtContract> debtContractList;
	
	private ChargeTariffItem chargeTariffItem;
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getTotalPayDebt() {
		return totalPayDebt;
	}

	public void setTotalPayDebt(BigDecimal totalPayDebt) {
		this.totalPayDebt = totalPayDebt;
	}
	
	public BigDecimal getTotalPayDebtTax() {
		return totalPayDebtTax;
	}

	public void setTotalPayDebtTax(BigDecimal totalPayDebtTax) {
		this.totalPayDebtTax = totalPayDebtTax;
	}

	public BigDecimal getStampsMoney() {
		return stampsMoney;
	}

	public void setStampsMoney(BigDecimal stampsMoney) {
		this.stampsMoney = stampsMoney;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}
	
	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getOpenCloseId() {
		return openCloseId;
	}

	public void setOpenCloseId(String openCloseId) {
		this.openCloseId = openCloseId;
	}

	public ChargeCustContract getContract() {
		return contract;
	}

	public void setContract(ChargeCustContract contract) {
		this.contract = contract;
	}

	public List<ChargeDebtContract> getDebtContractList() {
		return debtContractList;
	}

	public void setDebtContractList(List<ChargeDebtContract> debtContractList) {
		this.debtContractList = debtContractList;
	}

	public ChargeTariffItem getChargeTariffItem() {
		return chargeTariffItem;
	}

	public void setChargeTariffItem(ChargeTariffItem chargeTariffItem) {
		this.chargeTariffItem = chargeTariffItem;
	}

}
