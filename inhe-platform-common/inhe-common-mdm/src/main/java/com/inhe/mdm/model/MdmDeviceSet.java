package com.inhe.mdm.model;

import java.util.Date;

public class MdmDeviceSet {
    private Date setTime;

    private String fuuid;
    
    private String deviceId;

    private String orgId;

    private String deptId;

    private String fieldId;

    private String deviceNum;

    private String parentNum;

    private String cmdCode;

    private String fieldValue;

    private String oldFieldValue;

    private Date startTime;

    private Date finishTime;

    private String dataSource;

    private String agentCode;

    private String operator;

    private Date cdate;
    
    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getFuuid() {
        return fuuid;
    }

    public void setFuuid(String fuuid) {
        this.fuuid = fuuid == null ? null : fuuid.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
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

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum(String parentNum) {
        this.parentNum = parentNum == null ? null : parentNum.trim();
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode == null ? null : cmdCode.trim();
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    public String getOldFieldValue() {
        return oldFieldValue;
    }

    public void setOldFieldValue(String oldFieldValue) {
        this.oldFieldValue = oldFieldValue == null ? null : oldFieldValue.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}