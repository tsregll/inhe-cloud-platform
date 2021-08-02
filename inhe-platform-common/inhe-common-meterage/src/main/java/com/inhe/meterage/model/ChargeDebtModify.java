package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeDebtModify {
    private String alterDebtId;

    private String debtId;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private String debtTemplateId;

    private Date execDate;

    private String payType;

    private Integer dm;

    private BigDecimal payDm;

    private BigDecimal conAmt;

    private BigDecimal amt;

    private String includeVat;

    private BigDecimal vat;

    private BigDecimal paid;

    private Date paidDate;

    private Date expiryDate;

    private Date alterDate;

    private String stopReason;

    private String actived;

    private String status;
    
    private String operationType;

    private Date cdate;

    private String uoperator;

    private String remark;

    public String getAlterDebtId() {
        return alterDebtId;
    }

    public void setAlterDebtId(String alterDebtId) {
        this.alterDebtId = alterDebtId;
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

    public String getDebtTemplateId() {
        return debtTemplateId;
    }

    public void setDebtTemplateId(String debtTemplateId) {
        this.debtTemplateId = debtTemplateId;
    }

    public Date getExecDate() {
        return execDate;
    }

    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getDm() {
        return dm;
    }

    public void setDm(Integer dm) {
        this.dm = dm;
    }

    public BigDecimal getPayDm() {
        return payDm;
    }

    public void setPayDm(BigDecimal payDm) {
        this.payDm = payDm;
    }

    public BigDecimal getConAmt() {
        return conAmt;
    }

    public void setConAmt(BigDecimal conAmt) {
        this.conAmt = conAmt;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getIncludeVat() {
        return includeVat;
    }

    public void setIncludeVat(String includeVat) {
        this.includeVat = includeVat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(Date alterDate) {
        this.alterDate = alterDate;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
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
}