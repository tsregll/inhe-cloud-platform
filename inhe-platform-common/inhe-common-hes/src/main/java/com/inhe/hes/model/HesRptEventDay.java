package com.inhe.hes.model;

import java.util.Date;

public class HesRptEventDay {
	
	private Date startTime;

    private String orgId;

    private String deptId;

    private String eventType;
	    
    private Integer eventCount;

    private Integer devCount;
    
    //add
    private Date endTime;
    
    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Integer getDevCount() {
        return devCount;
    }

    public void setDevCount(Integer devCount) {
        this.devCount = devCount;
    }

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
}