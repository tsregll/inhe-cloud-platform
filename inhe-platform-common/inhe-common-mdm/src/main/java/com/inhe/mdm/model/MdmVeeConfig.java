package com.inhe.mdm.model;

import java.util.Date;

public class MdmVeeConfig {
    private String code;

    private String orgId;

    private String description;

    private Short rulesCount;

    private Short fieldsCount;

    private String actived;

    private String remarks;

    private String coperator;

    private Date cdate;

    private Date udate;
    
    private String dataTypeDetails;

    private String deptId;

    private String meterSort;

    private String meterModel;

    private String parentId;

    private String deviceId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getRulesCount() {
        return rulesCount;
    }

    public void setRulesCount(Short rulesCount) {
        this.rulesCount = rulesCount;
    }

    public Short getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(Short fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived == null ? null : actived.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
    
    public String getDataTypeDetails() {
        return dataTypeDetails;
    }

    public void setDataTypeDetails(String dataTypeDetails) {
        this.dataTypeDetails = dataTypeDetails == null ? null : dataTypeDetails.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort == null ? null : meterSort.trim();
    }

    public String getMeterModel() {
        return meterModel;
    }

    public void setMeterModel(String meterModel) {
        this.meterModel = meterModel == null ? null : meterModel.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
}