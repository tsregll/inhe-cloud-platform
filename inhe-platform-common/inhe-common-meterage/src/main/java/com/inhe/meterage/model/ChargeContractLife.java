package com.inhe.meterage.model;

import java.util.Date;

public class ChargeContractLife {
	
    private String id;

    private String contractNo;

    private String devNum;

    private Date bzDate;

    private String bzType;
    
    private String oldValue;

    private String newValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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
    
}