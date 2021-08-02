package com.inhe.mdm.model;

import java.math.BigDecimal;
import java.util.Date;

public class MdmPowerOffTask {
    private String code;

    private String description;

    private String orgId;

    private String deptId;

    private String deptName;

    private String schemeType;

    private String execWay;

    private String failureType;

    private Date startDate;

    private Date actualExecTime;

    private Date endDate;

    private String execDay;

    private String startTime;

    private String endTime;

    private Date startDatetime;

    private Date endDatetime;

    private String actualOffDate;

    private Short offCount;

    private Short offSCount;

    private Short offFCount;

    private BigDecimal offSPer;

    private Short tryTimes;

    private Short tryTime;

    private String auditRemark;

    private String auditOperator;

    private Date auditTime;

    private Date invalidTime;

    private String status;

    private String execingFlag;

    private String ip;

    private String mac;

    private String execing;

    private String reason;

    private String coperator;

    private Date cdate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    public String getExecWay() {
        return execWay;
    }

    public void setExecWay(String execWay) {
        this.execWay = execWay == null ? null : execWay.trim();
    }

    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType == null ? null : failureType.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getActualExecTime() {
        return actualExecTime;
    }

    public void setActualExecTime(Date actualExecTime) {
        this.actualExecTime = actualExecTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getExecDay() {
        return execDay;
    }

    public void setExecDay(String execDay) {
        this.execDay = execDay == null ? null : execDay.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getActualOffDate() {
        return actualOffDate;
    }

    public void setActualOffDate(String actualOffDate) {
        this.actualOffDate = actualOffDate == null ? null : actualOffDate.trim();
    }

    public Short getOffCount() {
        return offCount;
    }

    public void setOffCount(Short offCount) {
        this.offCount = offCount;
    }

    public Short getOffSCount() {
        return offSCount;
    }

    public void setOffSCount(Short offSCount) {
        this.offSCount = offSCount;
    }

    public Short getOffFCount() {
        return offFCount;
    }

    public void setOffFCount(Short offFCount) {
        this.offFCount = offFCount;
    }

    public BigDecimal getOffSPer() {
        return offSPer;
    }

    public void setOffSPer(BigDecimal offSPer) {
        this.offSPer = offSPer;
    }

    public Short getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Short tryTimes) {
        this.tryTimes = tryTimes;
    }

    public Short getTryTime() {
        return tryTime;
    }

    public void setTryTime(Short tryTime) {
        this.tryTime = tryTime;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public String getAuditOperator() {
        return auditOperator;
    }

    public void setAuditOperator(String auditOperator) {
        this.auditOperator = auditOperator == null ? null : auditOperator.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExecingFlag() {
        return execingFlag;
    }

    public void setExecingFlag(String execingFlag) {
        this.execingFlag = execingFlag == null ? null : execingFlag.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getExecing() {
        return execing;
    }

    public void setExecing(String execing) {
        this.execing = execing == null ? null : execing.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
}