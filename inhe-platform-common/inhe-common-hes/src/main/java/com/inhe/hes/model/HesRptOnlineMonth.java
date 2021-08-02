package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesRptOnlineMonth {
	
    private Date startTime;

    private String orgId;

    private String deptId;
    
    private Integer devCount;

    private BigDecimal ratio;

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

	public Integer getDevCount() {
        return devCount;
    }

    public void setDevCount(Integer devCount) {
        this.devCount = devCount;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}