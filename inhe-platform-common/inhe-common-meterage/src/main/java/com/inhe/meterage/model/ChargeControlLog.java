package com.inhe.meterage.model;

import java.util.Date;

public class ChargeControlLog {
	
    private String dataId;

    private String custId;

    private String orgId;

    private String deptId;

    private String contractNo;
    
    private String devId;
    
    private String devNum;

    private String controlType;
    
    private String controlReason;
    
    private String controlStatus;

    private Date cdate;

    private String coperator;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getControlReason() {
		return controlReason;
	}

	public void setControlReason(String controlReason) {
		this.controlReason = controlReason;
	}

	public String getControlStatus() {
		return controlStatus;
	}

	public void setControlStatus(String controlStatus) {
		this.controlStatus = controlStatus;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getCoperator() {
		return coperator;
	}

	public void setCoperator(String coperator) {
		this.coperator = coperator;
	}
    
}