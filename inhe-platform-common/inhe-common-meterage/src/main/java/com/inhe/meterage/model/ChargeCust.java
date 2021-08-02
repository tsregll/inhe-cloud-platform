package com.inhe.meterage.model;

import java.util.Date;

public class ChargeCust {
    private String custId;

    private String orgId;

    private String deptId;

    private String custNo;

    private String custName;

    private Date openTime;

    private String custTel;
    
    private String overdraftMoney;
    
    private String isProtect;

    private String custAddr;

    private String zipCode;

    private String custFax;

    private String email;

    private String custStatus;

    private Date cdate;

    private String coperator;

    private Date udate;

    private String uoperator;

    private String remark;
    
    //供客户查询功能使用  2020-06-28  add wdk
    private String startRegistDate;
    
  //供客户查询功能使用  2020-06-28  add wdk
    private String endRegistDate;
    
    //供客户查询功能使用  2020-06-28  add wdk
    private Integer contractCount;
    
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

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }
    
    public String getOverdraftMoney() {
		return overdraftMoney;
	}

	public void setOverdraftMoney(String overdraftMoney) {
		this.overdraftMoney = overdraftMoney;
	}

	public String getIsProtect() {
		return isProtect;
	}

	public void setIsProtect(String isProtect) {
		this.isProtect = isProtect;
	}

	public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
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

	public Integer getContractCount() {
		return contractCount;
	}

	public void setContractCount(Integer contractCount) {
		this.contractCount = contractCount;
	}
    
    
    

}