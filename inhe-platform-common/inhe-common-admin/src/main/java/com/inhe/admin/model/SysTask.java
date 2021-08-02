package com.inhe.admin.model;

import java.util.Date;

public class SysTask {
    private String code;

    private String wsId;

    private Short sn;

    private String description;
    
    private String type;

    private Short cycle;

    private String cycleType;

    private Date baseTime;

    private Date nextTime;

    private String execing;

    private String actived;

    private String coperator;

    private Date cdate;

    private String params;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId == null ? null : wsId.trim();
    }

    public Short getSn() {
        return sn;
    }

    public void setSn(Short sn) {
        this.sn = sn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Short getCycle() {
        return cycle;
    }

    public void setCycle(Short cycle) {
        this.cycle = cycle;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.trim();
    }

    public Date getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Date baseTime) {
        this.baseTime = baseTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public String getExecing() {
        return execing;
    }

    public void setExecing(String execing) {
        this.execing = execing == null ? null : execing.trim();
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived == null ? null : actived.trim();
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}