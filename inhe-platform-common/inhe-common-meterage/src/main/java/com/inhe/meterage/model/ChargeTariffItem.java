package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeTariffItem  {
    private String refNo;

    private Date execDate;

    private String execType;
    
    private String givingType;
    
    private BigDecimal givingAmount;

    private String actived;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;

    private String execValue;
    
    private String tariffId;

    private Integer tariffVersion;

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public Integer getTariffVersion() {
        return tariffVersion;
    }

    public void setTariffVersion(Integer tariffVersion) {
        this.tariffVersion = tariffVersion;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getExecDate() {
        return execDate;
    }

    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }
    
    public String getGivingType() {
		return givingType;
	}

	public void setGivingType(String givingType) {
		this.givingType = givingType;
	}

	public BigDecimal getGivingAmount() {
		return givingAmount;
	}

	public void setGivingAmount(BigDecimal givingAmount) {
		this.givingAmount = givingAmount;
	}

	public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
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

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public String getUoperator() {
        return uoperator;
    }

    public void setUoperator(String uoperator) {
        this.uoperator = uoperator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExecValue() {
        return execValue;
    }

    public void setExecValue(String execValue) {
        this.execValue = execValue;
    }
}