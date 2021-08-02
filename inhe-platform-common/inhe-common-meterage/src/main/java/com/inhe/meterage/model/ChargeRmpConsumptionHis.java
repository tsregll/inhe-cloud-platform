package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeRmpConsumptionHis {
	
    private String contractNo;

    private Integer periodNo;

    private String orgId;

    private String devId;

    private String devNum;

    private Date calcTime;

    private Date lastDate;

    private String lastSource;

    private BigDecimal lastYgZsd;

    private BigDecimal lastYgFl1Sd;

    private BigDecimal lastYgFl2Sd;

    private BigDecimal lastYgFl3Sd;

    private BigDecimal lastYgFl4Sd;

    private String thisSource;

    private Date thisDate;

    private BigDecimal thisYgZsd;

    private BigDecimal thisYgFl1Sd;

    private BigDecimal thisYgFl2Sd;

    private BigDecimal thisYgFl3Sd;

    private BigDecimal thisYgFl4Sd;

    private BigDecimal rmpUsage;

    private BigDecimal rmpFl1Usage;

    private BigDecimal rmpFl2Usage;

    private BigDecimal rmpFl3Usage;

    private BigDecimal rmpFl4Usage;

    private String billCode;

    private String remark;

    private Date cdate;
    
    private String custNo;
    
    private String custName;
    
    private String contractName;
    
    private String contractType;
    
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(Integer periodNo) {
        this.periodNo = periodNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public Date getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(Date calcTime) {
        this.calcTime = calcTime;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastSource() {
        return lastSource;
    }

    public void setLastSource(String lastSource) {
        this.lastSource = lastSource;
    }

    public BigDecimal getLastYgZsd() {
        return lastYgZsd;
    }

    public void setLastYgZsd(BigDecimal lastYgZsd) {
        this.lastYgZsd = lastYgZsd;
    }

    public BigDecimal getLastYgFl1Sd() {
        return lastYgFl1Sd;
    }

    public void setLastYgFl1Sd(BigDecimal lastYgFl1Sd) {
        this.lastYgFl1Sd = lastYgFl1Sd;
    }

    public BigDecimal getLastYgFl2Sd() {
        return lastYgFl2Sd;
    }

    public void setLastYgFl2Sd(BigDecimal lastYgFl2Sd) {
        this.lastYgFl2Sd = lastYgFl2Sd;
    }

    public BigDecimal getLastYgFl3Sd() {
        return lastYgFl3Sd;
    }

    public void setLastYgFl3Sd(BigDecimal lastYgFl3Sd) {
        this.lastYgFl3Sd = lastYgFl3Sd;
    }

    public BigDecimal getLastYgFl4Sd() {
        return lastYgFl4Sd;
    }

    public void setLastYgFl4Sd(BigDecimal lastYgFl4Sd) {
        this.lastYgFl4Sd = lastYgFl4Sd;
    }

    public String getThisSource() {
        return thisSource;
    }

    public void setThisSource(String thisSource) {
        this.thisSource = thisSource;
    }

    public Date getThisDate() {
        return thisDate;
    }

    public void setThisDate(Date thisDate) {
        this.thisDate = thisDate;
    }

    public BigDecimal getThisYgZsd() {
        return thisYgZsd;
    }

    public void setThisYgZsd(BigDecimal thisYgZsd) {
        this.thisYgZsd = thisYgZsd;
    }

    public BigDecimal getThisYgFl1Sd() {
        return thisYgFl1Sd;
    }

    public void setThisYgFl1Sd(BigDecimal thisYgFl1Sd) {
        this.thisYgFl1Sd = thisYgFl1Sd;
    }

    public BigDecimal getThisYgFl2Sd() {
        return thisYgFl2Sd;
    }

    public void setThisYgFl2Sd(BigDecimal thisYgFl2Sd) {
        this.thisYgFl2Sd = thisYgFl2Sd;
    }

    public BigDecimal getThisYgFl3Sd() {
        return thisYgFl3Sd;
    }

    public void setThisYgFl3Sd(BigDecimal thisYgFl3Sd) {
        this.thisYgFl3Sd = thisYgFl3Sd;
    }

    public BigDecimal getThisYgFl4Sd() {
        return thisYgFl4Sd;
    }

    public void setThisYgFl4Sd(BigDecimal thisYgFl4Sd) {
        this.thisYgFl4Sd = thisYgFl4Sd;
    }

    public BigDecimal getRmpUsage() {
        return rmpUsage;
    }

    public void setRmpUsage(BigDecimal rmpUsage) {
        this.rmpUsage = rmpUsage;
    }

    public BigDecimal getRmpFl1Usage() {
        return rmpFl1Usage;
    }

    public void setRmpFl1Usage(BigDecimal rmpFl1Usage) {
        this.rmpFl1Usage = rmpFl1Usage;
    }

    public BigDecimal getRmpFl2Usage() {
        return rmpFl2Usage;
    }

    public void setRmpFl2Usage(BigDecimal rmpFl2Usage) {
        this.rmpFl2Usage = rmpFl2Usage;
    }

    public BigDecimal getRmpFl3Usage() {
        return rmpFl3Usage;
    }

    public void setRmpFl3Usage(BigDecimal rmpFl3Usage) {
        this.rmpFl3Usage = rmpFl3Usage;
    }

    public BigDecimal getRmpFl4Usage() {
        return rmpFl4Usage;
    }

    public void setRmpFl4Usage(BigDecimal rmpFl4Usage) {
        this.rmpFl4Usage = rmpFl4Usage;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
    
}