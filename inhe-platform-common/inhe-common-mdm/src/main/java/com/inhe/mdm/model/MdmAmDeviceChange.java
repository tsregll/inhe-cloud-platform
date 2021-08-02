package com.inhe.mdm.model;

import java.util.Date;

public class MdmAmDeviceChange {
    private String id;

    private String mode;
    
    private String orgId;

    private String deptId;

    private String type;

    private String oldDevicenum;

    private Double oldDevicenumKwhEnd;

    private String newDevicenum;

    private Double newDevicenumKwhStart;

    private String opReason;

    private Date opTime;

    private String opOperator;

    private String remarks;

    private String ip;

    private String mac;

    private String coperator;

    private Date cdate;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOldDevicenum() {
        return oldDevicenum;
    }

    public void setOldDevicenum(String oldDevicenum) {
        this.oldDevicenum = oldDevicenum == null ? null : oldDevicenum.trim();
    }

    public Double getOldDevicenumKwhEnd() {
        return oldDevicenumKwhEnd;
    }

    public void setOldDevicenumKwhEnd(Double oldDevicenumKwhEnd) {
        this.oldDevicenumKwhEnd = oldDevicenumKwhEnd;
    }

    public String getNewDevicenum() {
        return newDevicenum;
    }

    public void setNewDevicenum(String newDevicenum) {
        this.newDevicenum = newDevicenum == null ? null : newDevicenum.trim();
    }

    public Double getNewDevicenumKwhStart() {
        return newDevicenumKwhStart;
    }

    public void setNewDevicenumKwhStart(Double newDevicenumKwhStart) {
        this.newDevicenumKwhStart = newDevicenumKwhStart;
    }

    public String getOpReason() {
        return opReason;
    }

    public void setOpReason(String opReason) {
        this.opReason = opReason == null ? null : opReason.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpOperator() {
        return opOperator;
    }

    public void setOpOperator(String opOperator) {
        this.opOperator = opOperator == null ? null : opOperator.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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