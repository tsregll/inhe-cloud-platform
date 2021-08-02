package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeOpenClose {
    private String openCloseId;

    private String userId;

    private Date openDate;

    private Date closeDate;

    private String orgId;

    private String deptId;

    private BigDecimal openMoney;

    private BigDecimal closeMoney;

    private BigDecimal tradeMoney;

    private String status;
    
    private int tradeCount;
    
    private BigDecimal totalTradeMoney;

    public String getOpenCloseId() {
        return openCloseId;
    }

    public void setOpenCloseId(String openCloseId) {
        this.openCloseId = openCloseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
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

    public BigDecimal getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(BigDecimal openMoney) {
        this.openMoney = openMoney;
    }

    public BigDecimal getCloseMoney() {
        return closeMoney;
    }

    public void setCloseMoney(BigDecimal closeMoney) {
        this.closeMoney = closeMoney;
    }

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public BigDecimal getTotalTradeMoney() {
		return totalTradeMoney;
	}

	public void setTotalTradeMoney(BigDecimal totalTradeMoney) {
		this.totalTradeMoney = totalTradeMoney;
	}
    
}