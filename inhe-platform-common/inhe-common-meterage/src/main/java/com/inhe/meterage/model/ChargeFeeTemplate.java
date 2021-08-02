package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeFeeTemplate {
    private String feeId;

    private String feeNo;

    private String orgId;

    private String deptId;

    private String feeName;

    private String includeVat;

    private BigDecimal vatRate;

    private String payType;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getFeeNo() {
        return feeNo;
    }

    public void setFeeNo(String feeNo) {
        this.feeNo = feeNo;
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

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public String getIncludeVat() {
        return includeVat;
    }

    public void setIncludeVat(String includeVat) {
        this.includeVat = includeVat;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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
}