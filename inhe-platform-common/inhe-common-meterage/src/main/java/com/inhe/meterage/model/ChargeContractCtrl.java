package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeContractCtrl {
    private String contractNo;

    private BigDecimal overdraft;

    private String outages;
    
    private String outagesPlan;
    
    private Date outagesPlanTime;

    private Date outagesTime;

    private String outagesCode;

    private String outagesOp;

    private Date recoverTime;

    private String recoverCode;

    private String recoverOp;

    private String swStatus;

    private Date swTime;
    
    // add
    private String combo;
    
    private String orgId;
    
    private String deptId;
    
    private String contractName;
    
    private String contractType;
    
    private String devNum;
    
    private String devName;
    
    private String total;
    
    private String custNo;
    
    private String custName;
    
    private String debtType;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(BigDecimal overdraft) {
		this.overdraft = overdraft;
	}

	public String getOutages() {
		return outages;
	}

	public void setOutages(String outages) {
		this.outages = outages;
	}

	public String getOutagesPlan() {
		return outagesPlan;
	}

	public void setOutagesPlan(String outagesPlan) {
		this.outagesPlan = outagesPlan;
	}

	public Date getOutagesPlanTime() {
		return outagesPlanTime;
	}

	public void setOutagesPlanTime(Date outagesPlanTime) {
		this.outagesPlanTime = outagesPlanTime;
	}

	public Date getOutagesTime() {
		return outagesTime;
	}

	public void setOutagesTime(Date outagesTime) {
		this.outagesTime = outagesTime;
	}

	public String getOutagesCode() {
		return outagesCode;
	}

	public void setOutagesCode(String outagesCode) {
		this.outagesCode = outagesCode;
	}

	public String getOutagesOp() {
		return outagesOp;
	}

	public void setOutagesOp(String outagesOp) {
		this.outagesOp = outagesOp;
	}

	public Date getRecoverTime() {
		return recoverTime;
	}

	public void setRecoverTime(Date recoverTime) {
		this.recoverTime = recoverTime;
	}

	public String getRecoverCode() {
		return recoverCode;
	}

	public void setRecoverCode(String recoverCode) {
		this.recoverCode = recoverCode;
	}

	public String getRecoverOp() {
		return recoverOp;
	}

	public void setRecoverOp(String recoverOp) {
		this.recoverOp = recoverOp;
	}

	public String getSwStatus() {
		return swStatus;
	}

	public void setSwStatus(String swStatus) {
		this.swStatus = swStatus;
	}

	public Date getSwTime() {
		return swTime;
	}

	public void setSwTime(Date swTime) {
		this.swTime = swTime;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
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

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public String getDebtType() {
		return debtType;
	}

	public void setDebtType(String debtType) {
		this.debtType = debtType;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
    
}