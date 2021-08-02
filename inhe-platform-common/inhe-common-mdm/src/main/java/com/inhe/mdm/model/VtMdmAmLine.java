package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAmLine {
	private String id;

	private String description;

	private String sort;

	private String lineCode;

	private String orgId;

	private String stationId;

	private String type;

	private String transRegionalFlag;

	private String status;

	private String coperator;

	private Date udate;

	private Date cdate;

	private String remarks;

	private String lineCodeName;// stationCode和code合并
	
	private String deptId;//所属变电站的所属部门

	private String stationDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode == null ? null : lineCode.trim();
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId == null ? null : stationId.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getTransRegionalFlag() {
		return transRegionalFlag;
	}

	public void setTransRegionalFlag(String transRegionalFlag) {
		this.transRegionalFlag = transRegionalFlag == null ? null : transRegionalFlag.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator == null ? null : coperator.trim();
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getLineCodeName() {
		return lineCodeName;
	}

	public void setLineCodeName(String lineCodeName) {
		this.lineCodeName = lineCodeName == null ? null : lineCodeName.trim();
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId == null ? null : deptId.trim();
	}

	public String getStationDescription() {
		return stationDescription;
	}
	

	public void setStationDescription(String stationDescription) {
		this.stationDescription = stationDescription == null ? null : stationDescription.trim();
	}
}