package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesNw {
    private String nwId;

    private String nwType;

    private String devId;

    private String devAddr;

    private Short devCount;

    private String nwParams;

    private Date opStartTime;

    private Date opEndTime;

    private BigDecimal progress;

    private Integer remainTime;

    private String opResult;

    private String coperator;

    private Date cdate;

    public String getNwId() {
        return nwId;
    }

    public void setNwId(String nwId) {
        this.nwId = nwId;
    }

    public String getNwType() {
        return nwType;
    }

    public void setNwType(String nwType) {
        this.nwType = nwType;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }

    public Short getDevCount() {
        return devCount;
    }

    public void setDevCount(Short devCount) {
        this.devCount = devCount;
    }

    public String getNwParams() {
        return nwParams;
    }

    public void setNwParams(String nwParams) {
        this.nwParams = nwParams;
    }

    public Date getOpStartTime() {
        return opStartTime;
    }

    public void setOpStartTime(Date opStartTime) {
        this.opStartTime = opStartTime;
    }

    public Date getOpEndTime() {
        return opEndTime;
    }

    public void setOpEndTime(Date opEndTime) {
        this.opEndTime = opEndTime;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Integer getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}