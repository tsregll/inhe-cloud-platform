package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeRptBillDialy {
	
    private Date transDate;

    private String orgId;

    private String deptId;
    
    private String contractType;

    private String payType;

    private Integer contractCount;

    private Integer billCount;

    private BigDecimal measureQty;

    private BigDecimal measureAmt;

    private BigDecimal measureTax;

    private BigDecimal feeAmt;

    private BigDecimal feeTax;

    private BigDecimal debtAmt;

    private BigDecimal debtTax;

    private BigDecimal stamp;

    private BigDecimal amoun;
    
    
    // 供客户查询功能使用  2020-07-06  add wdk
    private String startDate;
    
    // 供客户查询功能使用  2020-07-06  add wdk
    private String endDate;
    
    // 供客户查询功能使用  2020-07-06  add wdk
    private String transMonth; 

    public String getTransMonth() {
		return transMonth;
	}

	public void setTransMonth(String transMonth) {
		this.transMonth = transMonth;
	}

	public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getContractCount() {
        return contractCount;
    }

    public void setContractCount(Integer contractCount) {
        this.contractCount = contractCount;
    }

    public Integer getBillCount() {
        return billCount;
    }

    public void setBillCount(Integer billCount) {
        this.billCount = billCount;
    }

    public BigDecimal getMeasureQty() {
        return measureQty;
    }

    public void setMeasureQty(BigDecimal measureQty) {
        this.measureQty = measureQty;
    }

    public BigDecimal getMeasureAmt() {
        return measureAmt;
    }

    public void setMeasureAmt(BigDecimal measureAmt) {
        this.measureAmt = measureAmt;
    }

    public BigDecimal getMeasureTax() {
        return measureTax;
    }

    public void setMeasureTax(BigDecimal measureTax) {
        this.measureTax = measureTax;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public BigDecimal getFeeTax() {
        return feeTax;
    }

    public void setFeeTax(BigDecimal feeTax) {
        this.feeTax = feeTax;
    }

    public BigDecimal getDebtAmt() {
        return debtAmt;
    }

    public void setDebtAmt(BigDecimal debtAmt) {
        this.debtAmt = debtAmt;
    }

    public BigDecimal getDebtTax() {
        return debtTax;
    }

    public void setDebtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
    }

    public BigDecimal getStamp() {
        return stamp;
    }

    public void setStamp(BigDecimal stamp) {
        this.stamp = stamp;
    }

    public BigDecimal getAmoun() {
        return amoun;
    }

    public void setAmoun(BigDecimal amoun) {
        this.amoun = amoun;
    }

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    
    
}