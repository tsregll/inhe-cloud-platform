package com.inhe.mdm.model;

import java.util.Date;

public class MdmPowerOffTaskData {
    private String dataLevel;

    private String code;

    private String deviceId;

    private Date offExecTime;

    private Date onExecTime;

    private String orgId;

    private String offCmd;

    private String offResult;

    private Date offTime;

    private String onCmd;

    private String onResult;

    private Date onTime;

    private String remarks;

    private String params;

    public String getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(String dataLevel) {
        this.dataLevel = dataLevel == null ? null : dataLevel.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Date getOffExecTime() {
        return offExecTime;
    }

    public void setOffExecTime(Date offExecTime) {
        this.offExecTime = offExecTime;
    }

    public Date getOnExecTime() {
        return onExecTime;
    }

    public void setOnExecTime(Date onExecTime) {
        this.onExecTime = onExecTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOffCmd() {
        return offCmd;
    }

    public void setOffCmd(String offCmd) {
        this.offCmd = offCmd == null ? null : offCmd.trim();
    }

    public String getOffResult() {
        return offResult;
    }

    public void setOffResult(String offResult) {
        this.offResult = offResult == null ? null : offResult.trim();
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public String getOnCmd() {
        return onCmd;
    }

    public void setOnCmd(String onCmd) {
        this.onCmd = onCmd == null ? null : onCmd.trim();
    }

    public String getOnResult() {
        return onResult;
    }

    public void setOnResult(String onResult) {
        this.onResult = onResult == null ? null : onResult.trim();
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}