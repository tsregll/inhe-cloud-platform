package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCustAccount {
    private String custId;

    private String orgId;

    private String deptId;

    private BigDecimal accountBalance;

    private BigDecimal accountReadyBalance;

    private BigDecimal refundedMoney;

    private BigDecimal tradeMoney;

    private Date tradeDate;

    private String thisOperator;

    private String tradeNo;
    
    private Date cdate;
    
    //供余额查询功能使用  2020-06-29  add wdk
    private String custNo;
    
    //供余额查询功能使用  2020-06-29  add wdk
    private String custName;
    
   //供余额查询功能使用  2020-06-29  add wdk
    private Date openTime;

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

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountReadyBalance() {
        return accountReadyBalance;
    }

    public void setAccountReadyBalance(BigDecimal accountReadyBalance) {
        this.accountReadyBalance = accountReadyBalance;
    }

    public BigDecimal getRefundedMoney() {
        return refundedMoney;
    }

    public void setRefundedMoney(BigDecimal refundedMoney) {
        this.refundedMoney = refundedMoney;
    }

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getThisOperator() {
        return thisOperator;
    }

    public void setThisOperator(String thisOperator) {
        this.thisOperator = thisOperator;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
    
	
	
	
}