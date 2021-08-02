package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAmSubstation {

	private String id;

	private String description;

	private String address;

	private String stationCode;

	private String orgId;

	private String deptId;

	private String volLevel;

	private Double lo;

	private Double la;

	private Short zoom;

	private String status;

	private String coperator;

	private Date cdate;

	private Date udate;

	private String remarks;
	
	private String subCodeName;//stationCode和description合并

	public String getSubCodeName() {
		return subCodeName;
	}

	public void setSubCodeName(String subCodeName) {
		this.subCodeName = subCodeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
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

	public String getVolLevel() {
		return volLevel;
	}

	public void setVolLevel(String volLevel) {
		this.volLevel = volLevel;
	}

	public Double getLo() {
		return lo;
	}

	public void setLo(Double lo) {
		this.lo = lo;
	}

	public Double getLa() {
		return la;
	}

	public void setLa(Double la) {
		this.la = la;
	}

	public Short getZoom() {
		return zoom;
	}

	public void setZoom(Short zoom) {
		this.zoom = zoom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
