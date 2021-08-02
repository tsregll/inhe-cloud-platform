package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeContractEstimate {
    private String contractNo;

    private Date creditTime;

    private BigDecimal creditAmt;

    private Integer creditDays;

    private Date estDayTime;

    private BigDecimal estDayAmt;

    private Date estMonthTime;

    private BigDecimal estMonthAmt;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public Integer getCreditDays() {
        return creditDays;
    }

    public void setCreditDays(Integer creditDays) {
        this.creditDays = creditDays;
    }

    public Date getEstDayTime() {
        return estDayTime;
    }

    public void setEstDayTime(Date estDayTime) {
        this.estDayTime = estDayTime;
    }

    public BigDecimal getEstDayAmt() {
        return estDayAmt;
    }

    public void setEstDayAmt(BigDecimal estDayAmt) {
        this.estDayAmt = estDayAmt;
    }

    public Date getEstMonthTime() {
        return estMonthTime;
    }

    public void setEstMonthTime(Date estMonthTime) {
        this.estMonthTime = estMonthTime;
    }

    public BigDecimal getEstMonthAmt() {
        return estMonthAmt;
    }

    public void setEstMonthAmt(BigDecimal estMonthAmt) {
        this.estMonthAmt = estMonthAmt;
    }
}