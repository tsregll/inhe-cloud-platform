package com.inhe.admin.model;

import java.math.BigDecimal;
import java.util.Date;

public class SysOrg {
    private String orgId;

    private String orgName;

    private String refCode;
    
    private String industryCode;

    private String orgType;

    private String zoneCode;

    private BigDecimal longitude;

    private BigDecimal dismension;

    private String timezone;

    private String orgStatus;
    
    private String monetaryyUnit;
    
    private String monetaryyDeci;
    
    private String dateFormat;
    
    private Integer langId;
    
    private Integer equiNum;
    
    private Integer userNum;
    
    private String remark;

    private String coperator;
    
    private Date cdate;
    
    private String uoperator;

    private Date udate;
    
    private SysUser sysUser;
    
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getDismension() {
		return dismension;
	}

	public void setDismension(BigDecimal dismension) {
		this.dismension = dismension;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getMonetaryyUnit() {
		return monetaryyUnit;
	}

	public void setMonetaryyUnit(String monetaryyUnit) {
		this.monetaryyUnit = monetaryyUnit;
	}

	public String getMonetaryyDeci() {
		return monetaryyDeci;
	}

	public void setMonetaryyDeci(String monetaryyDeci) {
		this.monetaryyDeci = monetaryyDeci;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public Integer getEquiNum() {
		return equiNum;
	}

	public void setEquiNum(Integer equiNum) {
		this.equiNum = equiNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

  }