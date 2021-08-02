package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeDebtTemplate {
    private String debtTemplateId;

    private String debtNo;

    private String debtName;

    private String orgId;

    private String deptId;

    private String payType;

    private String includeVat;

    private BigDecimal vat;

    private String actived;

    private String remark;

    private String debtStatus;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    public String getDebtTemplateId() {
        return debtTemplateId;
    }

    public void setDebtTemplateId(String debtTemplateId) {
        this.debtTemplateId = debtTemplateId;
    }

    public String getDebtNo() {
        return debtNo;
    }

    public void setDebtNo(String debtNo) {
        this.debtNo = debtNo;
    }

    public String getDebtName() {
        return debtName;
    }

    public void setDebtName(String debtName) {
        this.debtName = debtName;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDebtStatus() {
        return debtStatus;
    }

    public void setDebtStatus(String debtStatus) {
        this.debtStatus = debtStatus;
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

	@Override
	public String toString() {
		return "ChargeDebtTemplate [debtTemplateId=" + debtTemplateId + ", debtNo=" + debtNo + ", debtName=" + debtName
				+ ", orgId=" + orgId + ", deptId=" + deptId + ", payType=" + payType + ", includeVat=" + includeVat
				+ ", vat=" + vat + ", actived=" + actived + ", remark=" + remark + ", debtStatus=" + debtStatus
				+ ", cdate=" + cdate + ", coperator=" + coperator + ", udate=" + udate + ", uoperator=" + uoperator
				+ "]";
	}
    
    
}