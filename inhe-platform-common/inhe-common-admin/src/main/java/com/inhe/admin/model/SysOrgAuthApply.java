package com.inhe.admin.model;

import java.util.Date;

public class SysOrgAuthApply {
    private String id;

    private String orgId;

    private String deployId;

    private Date applyTime;

    private Short authMonths;

    private String authStatus;

    private Date authTime;

    private String authOperator;

    private String authContent;

    private String remark;

    private Date cdate;

    private Date udate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId == null ? null : deployId.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Short getAuthMonths() {
        return authMonths;
    }

    public void setAuthMonths(Short authMonths) {
        this.authMonths = authMonths;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus == null ? null : authStatus.trim();
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getAuthOperator() {
        return authOperator;
    }

    public void setAuthOperator(String authOperator) {
        this.authOperator = authOperator == null ? null : authOperator.trim();
    }

    public String getAuthContent() {
        return authContent;
    }

    public void setAuthContent(String authContent) {
        this.authContent = authContent == null ? null : authContent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}