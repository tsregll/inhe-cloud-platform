package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAaPowerOff {
    private String id;

    private String orgId;

    private String deptId;

    private String way;

    private String refId;

    private String dataType;

    private String nowTime;

    private String offType;

    private String refContent;

    private Integer userCount;

    private Integer offUserCount;

    private Integer offThreeUserCount;

    private Integer importantCount;

    private Double importantOffDuration;

    private Integer importantOffTimes;

    private Double totalOffDuration;

    private Integer totalOffTimes;

    private Double aveOffDuration;

    private Double aveOffTimes;

    private Date cdate;
    
    private String lineName;//所属线路名称
    
    private String tmName;//所属线路名称

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime == null ? null : nowTime.trim();
    }

    public String getOffType() {
        return offType;
    }

    public void setOffType(String offType) {
        this.offType = offType == null ? null : offType.trim();
    }

    public String getRefContent() {
        return refContent;
    }

    public void setRefContent(String refContent) {
        this.refContent = refContent == null ? null : refContent.trim();
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getOffUserCount() {
        return offUserCount;
    }

    public void setOffUserCount(Integer offUserCount) {
        this.offUserCount = offUserCount;
    }

    public Integer getOffThreeUserCount() {
        return offThreeUserCount;
    }

    public void setOffThreeUserCount(Integer offThreeUserCount) {
        this.offThreeUserCount = offThreeUserCount;
    }

    public Integer getImportantCount() {
        return importantCount;
    }

    public void setImportantCount(Integer importantCount) {
        this.importantCount = importantCount;
    }

    public Double getImportantOffDuration() {
        return importantOffDuration;
    }

    public void setImportantOffDuration(Double importantOffDuration) {
        this.importantOffDuration = importantOffDuration;
    }

    public Integer getImportantOffTimes() {
        return importantOffTimes;
    }

    public void setImportantOffTimes(Integer importantOffTimes) {
        this.importantOffTimes = importantOffTimes;
    }

    public Double getTotalOffDuration() {
        return totalOffDuration;
    }

    public void setTotalOffDuration(Double totalOffDuration) {
        this.totalOffDuration = totalOffDuration;
    }

    public Integer getTotalOffTimes() {
        return totalOffTimes;
    }

    public void setTotalOffTimes(Integer totalOffTimes) {
        this.totalOffTimes = totalOffTimes;
    }

    public Double getAveOffDuration() {
        return aveOffDuration;
    }

    public void setAveOffDuration(Double aveOffDuration) {
        this.aveOffDuration = aveOffDuration;
    }

    public Double getAveOffTimes() {
        return aveOffTimes;
    }

    public void setAveOffTimes(Double aveOffTimes) {
        this.aveOffTimes = aveOffTimes;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
    
}