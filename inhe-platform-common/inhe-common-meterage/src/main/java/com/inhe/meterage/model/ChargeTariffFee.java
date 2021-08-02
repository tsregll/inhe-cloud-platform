package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeTariffFee {
    private String tariffFeeId;

    private String feeId;

    private String tariffId;

    private Integer tariffVersion;

    private BigDecimal feeValue;

    private String includeVat;

    private BigDecimal vatRate;

    private String payType;

    private BigDecimal factor;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;

    private String feeName;// 费用名称
    
    private String payCombo;// 支付方式combo
    
    private BigDecimal paid;// 已支付
    
    private Date lastPaidDate;// 最后支付日期
    
    public String getTariffFeeId() {
        return tariffFeeId;
    }

    public void setTariffFeeId(String tariffFeeId) {
        this.tariffFeeId = tariffFeeId;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

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

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public String getIncludeVat() {
        return includeVat;
    }

    public void setIncludeVat(String includeVat) {
        this.includeVat = includeVat;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
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

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getPayCombo() {
		return payCombo;
	}

	public void setPayCombo(String payCombo) {
		this.payCombo = payCombo;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public Date getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}
    
}