package com.inhe.meterage.model;

import java.util.Date;

public class ChargeContractStsMt {
    private String id;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private String devId;

    private String devNum;

    private Date bzDate;

    private String bzType;

    private String bzSubType;

    private String oldValue;

    private String newValue;

    private Integer subClass;

    private Integer classValue;

    private Date cdate;

    private String coperator;

    private String remarks;
    
    //add
    private String token;

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public Date getBzDate() {
        return bzDate;
    }

    public void setBzDate(Date bzDate) {
        this.bzDate = bzDate;
    }

    public String getBzType() {
        return bzType;
    }

    public void setBzType(String bzType) {
        this.bzType = bzType;
    }

    public String getBzSubType() {
        return bzSubType;
    }

    public void setBzSubType(String bzSubType) {
        this.bzSubType = bzSubType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Integer getSubClass() {
        return subClass;
    }

    public void setSubClass(Integer subClass) {
        this.subClass = subClass;
    }

    public Integer getClassValue() {
        return classValue;
    }

    public void setClassValue(Integer classValue) {
        this.classValue = classValue;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}