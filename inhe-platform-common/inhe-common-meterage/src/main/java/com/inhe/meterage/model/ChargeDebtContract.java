package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeDebtContract {
    private String debtId;

    private String orgId;

    private String deptId;

    private String custId;

    private String debtTemplateId;

    private String contractNo;

    private Date execDate;

    private String payType;

    private Integer dm;

    private BigDecimal payDm;

    private BigDecimal conAmt;

    private BigDecimal amt;

    private String includeVat;

    private BigDecimal vat;

    private BigDecimal paid;

    private Date paidDate;

    private Date expiryDate;

    private String actived;

    private String status;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;
    
    private String debtNo;

    private String debtName;

    private BigDecimal debtIncludeStampsMoney;
    
    private BigDecimal debtVat;
    
    private BigDecimal stampsMoney;
    
    private String debtStatus;
    
    //供欠款查询功能使用  2020-06-24  add wdk
    private String startRegistDate;
    
  //供欠款查询功能使用  2020-06-24  add wdk
    private String endRegistDate;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String custNoAndName;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String custNo;
    
    //供合同查询功能使用  2020-06-24  add wdk
    private String contractName;
    
    //供合同查询功能使用 电气水  2020-06-24  add wdk  
    private String contractType;
    
    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
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

    public String getDebtTemplateId() {
        return debtTemplateId;
    }

    public void setDebtTemplateId(String debtTemplateId) {
        this.debtTemplateId = debtTemplateId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getExecDate() {
        return execDate;
    }

    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getDm() {
        return dm;
    }

    public void setDm(Integer dm) {
        this.dm = dm;
    }

    public BigDecimal getPayDm() {
        return payDm;
    }

    public void setPayDm(BigDecimal payDm) {
        this.payDm = payDm;
    }

    public BigDecimal getConAmt() {
        return conAmt;
    }

    public void setConAmt(BigDecimal conAmt) {
        this.conAmt = conAmt;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getIncludeVat() {
        return includeVat;
    }

    public void setIncludeVat(String includeVat) {
        this.includeVat = includeVat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

	public String getDebtNo() {
		return debtNo;
	}

	public void setDebtNo(String debtNo) {
		this.debtNo = debtNo;
	}

	public String getDebtName() {
		return debtName;
	}

	public void setDebtName(String debtName) {
		this.debtName = debtName;
	}

	public BigDecimal getDebtIncludeStampsMoney() {
		return debtIncludeStampsMoney;
	}

	public void setDebtIncludeStampsMoney(BigDecimal debtIncludeStampsMoney) {
		this.debtIncludeStampsMoney = debtIncludeStampsMoney;
	}

	public BigDecimal getDebtVat() {
		return debtVat;
	}

	public void setDebtVat(BigDecimal debtVat) {
		this.debtVat = debtVat;
	}

	public BigDecimal getStampsMoney() {
		return stampsMoney;
	}

	public void setStampsMoney(BigDecimal stampsMoney) {
		this.stampsMoney = stampsMoney;
	}
	
	public String getDebtStatus() {
		return debtStatus;
	}

	public void setDebtStatus(String debtStatus) {
		this.debtStatus = debtStatus;
	}

	public String getStartRegistDate() {
		return startRegistDate;
	}

	public void setStartRegistDate(String startRegistDate) {
		this.startRegistDate = startRegistDate;
	}

	public String getEndRegistDate() {
		return endRegistDate;
	}

	public void setEndRegistDate(String endRegistDate) {
		this.endRegistDate = endRegistDate;
	}

	public String getCustNoAndName() {
		return custNoAndName;
	}

	public void setCustNoAndName(String custNoAndName) {
		this.custNoAndName = custNoAndName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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