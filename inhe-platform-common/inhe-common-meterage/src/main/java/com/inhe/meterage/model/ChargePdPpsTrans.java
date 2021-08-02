package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargePdPpsTrans {
    private String chargeCode;

    private String operateType;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;
    
    private String openCloseId;

    private Date payTime;

    private String transId;

    private BigDecimal payMoney;

    private BigDecimal stampsMoney;

    private String payType;
    
    private BigDecimal accBalance;

    private String coperator;

    private Date cdate;

    private String additionText1;
    
    private String devNum;

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
    
    public String getOpenCloseId() {
		return openCloseId;
	}

	public void setOpenCloseId(String openCloseId) {
		this.openCloseId = openCloseId;
	}

	public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getStampsMoney() {
        return stampsMoney;
    }

    public void setStampsMoney(BigDecimal stampsMoney) {
        this.stampsMoney = stampsMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    public BigDecimal getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(BigDecimal accBalance) {
		this.accBalance = accBalance;
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

    public String getAdditionText1() {
        return additionText1;
    }

    public void setAdditionText1(String additionText1) {
        this.additionText1 = additionText1;
    }

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}
    
}