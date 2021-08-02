package com.inhe.mdm.model;

public class MdmAaLineLossModelDve {
    private String id;

    private String deviceId;

    private String supplyDire;
    
    private String deviceNum;

    private String orgId;

    private String deptId;

    private String meteringDire;

    private String meterSort;

    private String meterName;

    private String volLevel;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getSupplyDire() {
        return supplyDire;
    }

    public void setSupplyDire(String supplyDire) {
        this.supplyDire = supplyDire == null ? null : supplyDire.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
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

    public String getMeteringDire() {
        return meteringDire;
    }

    public void setMeteringDire(String meteringDire) {
        this.meteringDire = meteringDire == null ? null : meteringDire.trim();
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort == null ? null : meterSort.trim();
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName == null ? null : meterName.trim();
    }

    public String getVolLevel() {
        return volLevel;
    }

    public void setVolLevel(String volLevel) {
        this.volLevel = volLevel == null ? null : volLevel.trim();
    }
}