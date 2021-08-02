package com.inhe.admin.model;

import java.util.Date;

public class SysParam {
	
	private String paramType;

	private String paramCode;
	
    private String description;

    private Integer orderNo;

    private String v1;

    private String v2;

    private String v3;

    private String v4;

    private String remark;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1 == null ? null : v1.trim();
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2 == null ? null : v2.trim();
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3 == null ? null : v3.trim();
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4 == null ? null : v4.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator == null ? null : coperator.trim();
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
        this.uoperator = uoperator == null ? null : uoperator.trim();
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}