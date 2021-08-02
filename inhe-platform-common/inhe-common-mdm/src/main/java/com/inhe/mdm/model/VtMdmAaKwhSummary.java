package com.inhe.mdm.model;

public class VtMdmAaKwhSummary {
	
	private String orgId;
	
	private String deptId;
	
	private Double totalKwhStart = 0.0;
	
	private Double totalKwhEnd = 0.0;
	
	private Double totalKwh = 0.0;
	
	private Double priLinePriTmKwh = 0.0;
	
	private Double pubLinePriTmKwh = 0.0;
	
	private Double pubLinePubTmKwh = 0.0;
	
	private Double YearBasis = 0.0;
	
	private Double linkRelativeRatio = 0.0;
	
	public String getOrgId() {
		return orgId;
	}

	public void setBranchCode(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptCode(String deptId) {
		this.deptId = deptId;
	}

	public Double getTotalKwhStart() {
		return totalKwhStart;
	}

	public void setTotalKwhStart(Double totalKwhStart) {
		this.totalKwhStart = totalKwhStart;
	}

	public Double getTotalKwhEnd() {
		return totalKwhEnd;
	}

	public void setTotalKwhEnd(Double totalKwhEnd) {
		this.totalKwhEnd = totalKwhEnd;
	}

	public Double getTotalKwh() {
		return totalKwh;
	}

	public void setTotalKwh(Double totalKwh) {
		this.totalKwh = totalKwh;
	}

	public Double getPriLinePriTmKwh() {
		return priLinePriTmKwh;
	}

	public void setPriLinePriTmKwh(Double priLinePriTmKwh) {
		this.priLinePriTmKwh = priLinePriTmKwh;
	}

	public Double getPubLinePriTmKwh() {
		return pubLinePriTmKwh;
	}

	public void setPubLinePriTmKwh(Double pubLinePriTmKwh) {
		this.pubLinePriTmKwh = pubLinePriTmKwh;
	}

	public Double getPubLinePubTmKwh() {
		return pubLinePubTmKwh;
	}

	public void setPubLinePubTmKwh(Double pubLinePubTmKwh) {
		this.pubLinePubTmKwh = pubLinePubTmKwh;
	}

	public Double getYearBasis() {
		return YearBasis;
	}

	public void setYearBasis(Double yearBasis) {
		YearBasis = yearBasis;
	}

	public Double getLinkRelativeRatio() {
		return linkRelativeRatio;
	}

	public void setLinkRelativeRatio(Double linkRelativeRatio) {
		this.linkRelativeRatio = linkRelativeRatio;
	}
	
}