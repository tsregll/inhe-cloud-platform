package com.inhe.mdm.model;

public class VtMdmAaPowerOffSummary {
	
	private String deptId;
	
	private String orgId;
	
	private Integer offUserCount = 0;
	
	private Integer offThreeUserCount = 0;
	
	private Double totalOffDuration = 0.0;
	
	private Integer totalOffTimes = 0;
	
	private Double totalOffDurationImp = 0.0;
	
	private Integer totalOffTimesImp = 0;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public Double getTotalOffDurationImp() {
		return totalOffDurationImp;
	}

	public void setTotalOffDurationImp(Double totalOffDurationImp) {
		this.totalOffDurationImp = totalOffDurationImp;
	}

	public Integer getTotalOffTimesImp() {
		return totalOffTimesImp;
	}

	public void setTotalOffTimesImp(Integer totalOffTimesImp) {
		this.totalOffTimesImp = totalOffTimesImp;
	}
	
}