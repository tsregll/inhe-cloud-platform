package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeSysDevice {

    private String devId;

    private String orgId;

    private String deptId;

    private String type;

    private String devNum;

    private String devName;

    private String address;

    private BigDecimal rmpRate;

    private BigDecimal lo;

    private BigDecimal la;

    private String status;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

    private String remarks;

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getRmpRate() {
		return rmpRate;
	}

	public void setRmpRate(BigDecimal rmpRate) {
		this.rmpRate = rmpRate;
	}

	public BigDecimal getLo() {
		return lo;
	}

	public void setLo(BigDecimal lo) {
		this.lo = lo;
	}

	public BigDecimal getLa() {
		return la;
	}

	public void setLa(BigDecimal la) {
		this.la = la;
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

	public String getUoperator() {
		return uoperator;
	}

	public void setUoperator(String uoperator) {
		this.uoperator = uoperator;
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