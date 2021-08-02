package com.inhe.sts.model;

import java.util.Date;

public class StsKeychangeRst {
	
	private String id;

    private String orgId;

    private Integer rstType;

    private Date bzDate;

    private String devId;

    private String devNum;

    private String oldKey;

    private String newKey;

    private String remarks;

    private String status;

    private String coperator;

    private Date cdate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getRstType() {
		return rstType;
	}

	public void setRstType(Integer rstType) {
		this.rstType = rstType;
	}

	public Date getBzDate() {
		return bzDate;
	}

	public void setBzDate(Date bzDate) {
		this.bzDate = bzDate;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getOldKey() {
		return oldKey;
	}

	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}

	public String getNewKey() {
		return newKey;
	}

	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
    
    
}