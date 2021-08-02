package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCustConsumptionBill {
    private String billId;

    private String rmpMonthFeeId;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private String devId;

    private String tariffId;

    private Integer tariffVersion;

    private Date billStartTime;

    private Date billEndTime;

    private Integer billPeriod;

    private String billStatus;

    private Integer payCount;

    private BigDecimal givingAmount;

    private BigDecimal givingMoney;
    
    private Date givingTiTime;
    
    private BigDecimal givingTiAmt;

    private BigDecimal calcAmount;

    private BigDecimal vendAmount;

    private BigDecimal consumptionMoney;

    private BigDecimal consumptionVat;

    private BigDecimal firstLevelMoney;

    private BigDecimal firstLevelVat;

    private BigDecimal secondLevelMoney;

    private BigDecimal secondLevelVat;

    private BigDecimal thirdLevelMoney;

    private BigDecimal thirdLevelVat;

    private BigDecimal fourthLevelMoney;

    private BigDecimal fourthLevelVat;

    private BigDecimal fifthLevelMoney;

    private BigDecimal fifthLevelVat;

    private BigDecimal sixthLevelMoney;

    private BigDecimal sixthLevelVat;

    private BigDecimal seventhLevelMoney;

    private BigDecimal seventhLevelVat;

    private BigDecimal eighthLevelMoney;

    private BigDecimal eighthLevelVat;

    private BigDecimal ninthLevelMoney;

    private BigDecimal ninthLevelVat;

    private BigDecimal tenthLevelMoney;

    private BigDecimal tenthLevelVat;

    private String coperator;

    private Date cdate;

    private Date udate;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getRmpMonthFeeId() {
        return rmpMonthFeeId;
    }

    public void setRmpMonthFeeId(String rmpMonthFeeId) {
        this.rmpMonthFeeId = rmpMonthFeeId;
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

    public Date getBillStartTime() {
        return billStartTime;
    }

    public void setBillStartTime(Date billStartTime) {
        this.billStartTime = billStartTime;
    }

    public Date getBillEndTime() {
        return billEndTime;
    }

    public void setBillEndTime(Date billEndTime) {
        this.billEndTime = billEndTime;
    }

    public Integer getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(Integer billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public BigDecimal getGivingAmount() {
        return givingAmount;
    }

    public void setGivingAmount(BigDecimal givingAmount) {
        this.givingAmount = givingAmount;
    }

    public BigDecimal getGivingMoney() {
        return givingMoney;
    }

    public void setGivingMoney(BigDecimal givingMoney) {
        this.givingMoney = givingMoney;
    }
    
    public Date getGivingTiTime() {
		return givingTiTime;
	}

	public void setGivingTiTime(Date givingTiTime) {
		this.givingTiTime = givingTiTime;
	}

	public BigDecimal getGivingTiAmt() {
		return givingTiAmt;
	}

	public void setGivingTiAmt(BigDecimal givingTiAmt) {
		this.givingTiAmt = givingTiAmt;
	}

	public BigDecimal getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(BigDecimal calcAmount) {
        this.calcAmount = calcAmount;
    }

    public BigDecimal getVendAmount() {
        return vendAmount;
    }

    public void setVendAmount(BigDecimal vendAmount) {
        this.vendAmount = vendAmount;
    }

    public BigDecimal getConsumptionMoney() {
        return consumptionMoney;
    }

    public void setConsumptionMoney(BigDecimal consumptionMoney) {
        this.consumptionMoney = consumptionMoney;
    }

    public BigDecimal getConsumptionVat() {
        return consumptionVat;
    }

    public void setConsumptionVat(BigDecimal consumptionVat) {
        this.consumptionVat = consumptionVat;
    }

    public BigDecimal getFirstLevelMoney() {
        return firstLevelMoney;
    }

    public void setFirstLevelMoney(BigDecimal firstLevelMoney) {
        this.firstLevelMoney = firstLevelMoney;
    }

    public BigDecimal getFirstLevelVat() {
        return firstLevelVat;
    }

    public void setFirstLevelVat(BigDecimal firstLevelVat) {
        this.firstLevelVat = firstLevelVat;
    }

    public BigDecimal getSecondLevelMoney() {
        return secondLevelMoney;
    }

    public void setSecondLevelMoney(BigDecimal secondLevelMoney) {
        this.secondLevelMoney = secondLevelMoney;
    }

    public BigDecimal getSecondLevelVat() {
        return secondLevelVat;
    }

    public void setSecondLevelVat(BigDecimal secondLevelVat) {
        this.secondLevelVat = secondLevelVat;
    }

    public BigDecimal getThirdLevelMoney() {
        return thirdLevelMoney;
    }

    public void setThirdLevelMoney(BigDecimal thirdLevelMoney) {
        this.thirdLevelMoney = thirdLevelMoney;
    }

    public BigDecimal getThirdLevelVat() {
        return thirdLevelVat;
    }

    public void setThirdLevelVat(BigDecimal thirdLevelVat) {
        this.thirdLevelVat = thirdLevelVat;
    }

    public BigDecimal getFourthLevelMoney() {
        return fourthLevelMoney;
    }

    public void setFourthLevelMoney(BigDecimal fourthLevelMoney) {
        this.fourthLevelMoney = fourthLevelMoney;
    }

    public BigDecimal getFourthLevelVat() {
        return fourthLevelVat;
    }

    public void setFourthLevelVat(BigDecimal fourthLevelVat) {
        this.fourthLevelVat = fourthLevelVat;
    }

    public BigDecimal getFifthLevelMoney() {
        return fifthLevelMoney;
    }

    public void setFifthLevelMoney(BigDecimal fifthLevelMoney) {
        this.fifthLevelMoney = fifthLevelMoney;
    }

    public BigDecimal getFifthLevelVat() {
        return fifthLevelVat;
    }

    public void setFifthLevelVat(BigDecimal fifthLevelVat) {
        this.fifthLevelVat = fifthLevelVat;
    }

    public BigDecimal getSixthLevelMoney() {
        return sixthLevelMoney;
    }

    public void setSixthLevelMoney(BigDecimal sixthLevelMoney) {
        this.sixthLevelMoney = sixthLevelMoney;
    }

    public BigDecimal getSixthLevelVat() {
        return sixthLevelVat;
    }

    public void setSixthLevelVat(BigDecimal sixthLevelVat) {
        this.sixthLevelVat = sixthLevelVat;
    }

    public BigDecimal getSeventhLevelMoney() {
        return seventhLevelMoney;
    }

    public void setSeventhLevelMoney(BigDecimal seventhLevelMoney) {
        this.seventhLevelMoney = seventhLevelMoney;
    }

    public BigDecimal getSeventhLevelVat() {
        return seventhLevelVat;
    }

    public void setSeventhLevelVat(BigDecimal seventhLevelVat) {
        this.seventhLevelVat = seventhLevelVat;
    }

    public BigDecimal getEighthLevelMoney() {
        return eighthLevelMoney;
    }

    public void setEighthLevelMoney(BigDecimal eighthLevelMoney) {
        this.eighthLevelMoney = eighthLevelMoney;
    }

    public BigDecimal getEighthLevelVat() {
        return eighthLevelVat;
    }

    public void setEighthLevelVat(BigDecimal eighthLevelVat) {
        this.eighthLevelVat = eighthLevelVat;
    }

    public BigDecimal getNinthLevelMoney() {
        return ninthLevelMoney;
    }

    public void setNinthLevelMoney(BigDecimal ninthLevelMoney) {
        this.ninthLevelMoney = ninthLevelMoney;
    }

    public BigDecimal getNinthLevelVat() {
        return ninthLevelVat;
    }

    public void setNinthLevelVat(BigDecimal ninthLevelVat) {
        this.ninthLevelVat = ninthLevelVat;
    }

    public BigDecimal getTenthLevelMoney() {
        return tenthLevelMoney;
    }

    public void setTenthLevelMoney(BigDecimal tenthLevelMoney) {
        this.tenthLevelMoney = tenthLevelMoney;
    }

    public BigDecimal getTenthLevelVat() {
        return tenthLevelVat;
    }

    public void setTenthLevelVat(BigDecimal tenthLevelVat) {
        this.tenthLevelVat = tenthLevelVat;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}