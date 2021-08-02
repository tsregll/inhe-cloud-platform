package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAaStealDetail {
    private String deviceId;

    private String nowTime;

    private String stealId;
    
    private String refCode;

    private String orgId;

    private String deptId;

    private String lineId;

    private String tmId;

    private String deviceType;

    private String supplier;

    private String cstId;

    private String cstName;

    private String meterSort;

    private String deviceNum;

    private String status;

    private String opType;

    private Date opTime;

    private String opOperator;

    private Double suspicion;

    private String ptct;

    private Date cdate;

    private String opContent;
    
    private int countStealId;//窃电事件数量
    
    private Double sumSuspicion;//嫌疑值总和
    
    private String startTime;//开始日期
    
    private String endTime;//结束日期
    
    private String meterDescription;//计量点名称
    
    private String themeId;//主题编号
    
    private String timeType;//时间类型（日，月）
    
    private String type;//分类（按计量点或按事件类型）
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime == null ? null : nowTime.trim();
    }

    public String getStealId() {
        return stealId;
    }

    public void setStealId(String stealId) {
        this.stealId = stealId == null ? null : stealId.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
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

    public String getCstId() {
        return cstId;
    }

    public void setCstId(String cstId) {
        this.cstId = cstId == null ? null : cstId.trim();
    }

    public String getCstName() {
        return cstName;
    }

    public void setCstName(String cstName) {
        this.cstName = cstName == null ? null : cstName.trim();
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort == null ? null : meterSort.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getOpOperator() {
        return opOperator;
    }

    public void setOpOperator(String opOperator) {
        this.opOperator = opOperator == null ? null : opOperator.trim();
    }

    public Double getSuspicion() {
        return suspicion;
    }

    public void setSuspicion(Double suspicion) {
        this.suspicion = suspicion;
    }

    public String getPtct() {
        return ptct;
    }

    public void setPtct(String ptct) {
        this.ptct = ptct == null ? null : ptct.trim();
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

	public int getCountStealId() {
		return countStealId;
	}

	public void setCountStealId(int countStealId) {
		this.countStealId = countStealId;
	}

	public Double getSumSuspicion() {
		return sumSuspicion;
	}

	public void setSumSuspicion(Double sumSuspicion) {
		this.sumSuspicion = sumSuspicion;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public String getMeterDescription() {
		return meterDescription;
	}

	public void setMeterDescription(String meterDescription) {
		this.meterDescription = meterDescription == null ? null : meterDescription.trim();
	}

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId == null ? null : themeId.trim();
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType == null ? null : timeType.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
	
	
}