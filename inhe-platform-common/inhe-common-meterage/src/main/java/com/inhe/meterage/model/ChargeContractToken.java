package com.inhe.meterage.model;

import java.util.Date;

public class ChargeContractToken {
	
	private String refCode;

	private Integer refIndex;
	
    private Date bzDate;

    private String bzType;

    private String contractNo;

    private String devNum;

    private String description;

    private String token;

    public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Integer getRefIndex() {
		return refIndex;
	}

	public void setRefIndex(Integer refIndex) {
		this.refIndex = refIndex;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}