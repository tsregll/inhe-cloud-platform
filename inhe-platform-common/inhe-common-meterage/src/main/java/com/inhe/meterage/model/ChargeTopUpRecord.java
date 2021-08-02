package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeTopUpRecord {
    private String topUpId;

    private String orgId;

    private String deptId;

    private String custId;

    private String code;

    private Date payTime;

    private BigDecimal totalIncludeStampsMoney;

    private BigDecimal totalWithoutStampsMoney;

    private BigDecimal stampsMoney;

    private String payType;

    private String chargeCode;

    private String status;

    private String coperator;

    private Date cdate;

    private String additionText1;

    public String getTopUpId() {
        return topUpId;
    }

    public void setTopUpId(String topUpId) {
        this.topUpId = topUpId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getTotalIncludeStampsMoney() {
        return totalIncludeStampsMoney;
    }

    public void setTotalIncludeStampsMoney(BigDecimal totalIncludeStampsMoney) {
        this.totalIncludeStampsMoney = totalIncludeStampsMoney;
    }

    public BigDecimal getTotalWithoutStampsMoney() {
        return totalWithoutStampsMoney;
    }

    public void setTotalWithoutStampsMoney(BigDecimal totalWithoutStampsMoney) {
        this.totalWithoutStampsMoney = totalWithoutStampsMoney;
    }

    public BigDecimal getStampsMoney() {
        return stampsMoney;
    }

    public void setStampsMoney(BigDecimal stampsMoney) {
        this.stampsMoney = stampsMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAdditionText1() {
        return additionText1;
    }

    public void setAdditionText1(String additionText1) {
        this.additionText1 = additionText1;
    }
}