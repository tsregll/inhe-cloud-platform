package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAaPowerOffDetail {
    private String deviceId;

    private Date startTime;

    private String offType;

    private String orgId;

    private String deptId;

    private String deviceNum;

    private String refCode;

    private String lineId;

    private String tmId;

    private String deviceType;

    private String supplier;

    private Date endTime;

    private Double duration;

    private String important;

    private Date cdate;
    
    private String dateStr;//按月查询
    
    private String meterSort;//计量点类型
   
	private String startDate;// 开始日期

	private String endDate;// 结束日期
	
	private String timesFlag;//停电次数标志
	
	private String tmName;//台区名称
	
	private String lineName;//线路名称
	
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getOffType() {
        return offType;
    }

    public void setOffType(String offType) {
        this.offType = offType == null ? null : offType.trim();
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

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getTmId() {
        return tmId;
    }

    public void setTmId(String tmId) {
        this.tmId = tmId == null ? null : tmId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important == null ? null : important.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr == null ? null : dateStr.trim();
	}
	

	public String getMeterSort() {
		return meterSort;
	}

	public void setMeterSort(String meterSort) {
		this.meterSort = meterSort;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTimesFlag() {
		return timesFlag;
	}

	public void setTimesFlag(String timesFlag) {
		this.timesFlag = timesFlag;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
}