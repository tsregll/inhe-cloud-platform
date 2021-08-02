package com.inhe.admin.model;


import java.math.BigDecimal;
import java.util.Date;

public class SysDevice {
    private String devId;

    private String orgId;

    private String deptId;

    private String type;
    
    private String devType;

    private String devNum;

    private String devNo;

    private String address;

    private String params;

    private BigDecimal lo;

    private BigDecimal la;

    private String status;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

    private String remarks;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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

    public String getType() {
        return type;
    }

    public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setType(String type) {
        this.type = type;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public BigDecimal getLo() {
        return lo;
    }

    public void setLo(BigDecimal lo) {
        this.lo = lo;
    }

    public BigDecimal getLa() {
        return la;
    }

    public void setLa(BigDecimal la) {
        this.la = la;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUoperator() {
        return uoperator;
    }

    public void setUoperator(String uoperator) {
        this.uoperator = uoperator;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cdate == null) ? 0 : cdate.hashCode());
		result = prime * result + ((coperator == null) ? 0 : coperator.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((devId == null) ? 0 : devId.hashCode());
		result = prime * result + ((devNo == null) ? 0 : devNo.hashCode());
		result = prime * result + ((devNum == null) ? 0 : devNum.hashCode());
		result = prime * result + ((la == null) ? 0 : la.hashCode());
		result = prime * result + ((lo == null) ? 0 : lo.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((udate == null) ? 0 : udate.hashCode());
		result = prime * result + ((uoperator == null) ? 0 : uoperator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SysDevice) {
			SysDevice device = (SysDevice) obj;
			return this.devId.equals(device.devId);
		}
		return false;
	}
    
}