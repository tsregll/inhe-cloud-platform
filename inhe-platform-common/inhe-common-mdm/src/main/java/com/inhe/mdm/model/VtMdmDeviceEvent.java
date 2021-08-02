package com.inhe.mdm.model;

import java.util.Date;
import java.util.List;

public class VtMdmDeviceEvent {
    private String id;

    private Date eventTime;

    private String deviceId;

    private String orgId;

    private String deptId;

    private String deviceAddr;

    private String deviceNum;

    private Integer eventType;

    private String appRcvStatus;

    private String dataSource;

    private String upOperator;

    private Date upTime;

    private String disposeFlag;

    private String opType;

    private Date opTime;

    private String opPic;

    private String opOperator;

    private Date cdate;
    
    private String opContent;

    private String remarks;
    
    private Date startTime;
    
    private Date endTime;
    
    private List<Integer> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
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

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr == null ? null : deviceAddr.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getAppRcvStatus() {
        return appRcvStatus;
    }

    public void setAppRcvStatus(String appRcvStatus) {
        this.appRcvStatus = appRcvStatus == null ? null : appRcvStatus.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getUpOperator() {
        return upOperator;
    }

    public void setUpOperator(String upOperator) {
        this.upOperator = upOperator == null ? null : upOperator.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getDisposeFlag() {
        return disposeFlag;
    }

    public void setDisposeFlag(String disposeFlag) {
        this.disposeFlag = disposeFlag == null ? null : disposeFlag.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpPic() {
        return opPic;
    }

    public void setOpPic(String opPic) {
        this.opPic = opPic == null ? null : opPic.trim();
    }

    public String getOpOperator() {
        return opOperator;
    }

    public void setOpOperator(String opOperator) {
        this.opOperator = opOperator == null ? null : opOperator.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    
    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent == null ? null : opContent.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}