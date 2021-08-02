package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmVeeTaskData {
    private Integer dataType;

    private String id;
    
    private String orgId;

    private String taskCode;

    private Integer cycleTimes;

    private Date readTime;

    private String deviceCode;

    private String fieldId;

    private String deviceNum;

    private String veeDataType;

    private String source;

    private String veeType;

    private String opResult;

    private String realValue;

    private String status;

    private String estimateValue;

    private String editValue;

    private String veeResult;

    private Date cdate;

    private String veeResultDetails;
    
    private Date startTime;
    
    private Date endTime;
    
    private String meterSort;
    
    private String deviceDescription;
    
    private String fieldDescription;
    
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

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

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
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

    public String getVeeDataType() {
        return veeDataType;
    }

    public void setVeeDataType(String veeDataType) {
        this.veeDataType = veeDataType == null ? null : veeDataType.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getVeeType() {
        return veeType;
    }

    public void setVeeType(String veeType) {
        this.veeType = veeType == null ? null : veeType.trim();
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult == null ? null : opResult.trim();
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue == null ? null : realValue.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEstimateValue() {
        return estimateValue;
    }

    public void setEstimateValue(String estimateValue) {
        this.estimateValue = estimateValue == null ? null : estimateValue.trim();
    }

    public String getEditValue() {
        return editValue;
    }

    public void setEditValue(String editValue) {
        this.editValue = editValue == null ? null : editValue.trim();
    }

    public String getVeeResult() {
        return veeResult;
    }

    public void setVeeResult(String veeResult) {
        this.veeResult = veeResult == null ? null : veeResult.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getVeeResultDetails() {
        return veeResultDetails;
    }

    public void setVeeResultDetails(String veeResultDetails) {
        this.veeResultDetails = veeResultDetails == null ? null : veeResultDetails.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }
}