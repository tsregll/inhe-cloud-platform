package com.inhe.hes.model;

import java.util.Date;

public class HesDeviceEvent {
    private Date dataTime;

    private String devId;

    private String eventType;
    
    private String orgId;

    private String deptId;

    private String cmdCode;

    private String devAddr;

    private Date startTime;

    private Date endTime;

    private String detail;

    private String dataSource;

    private String dataOperator;
    
    private Date cdate;

    public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
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

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode == null ? null : cmdCode.trim();
    }

    public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getDataOperator() {
        return dataOperator;
    }

    public void setDataOperator(String dataOperator) {
        this.dataOperator = dataOperator == null ? null : dataOperator.trim();
    }

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
}