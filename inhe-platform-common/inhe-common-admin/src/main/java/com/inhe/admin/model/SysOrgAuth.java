package com.inhe.admin.model;

import java.util.Date;

public class SysOrgAuth {
    private String authId;

    private String orgId;

    private String deployId;

    private Date loseTime;

    private String authStatus;

    private String remark;

    private Date cdate;

    private Date udate;
    
    private SysOrgDeploy sysOrgDeploy;

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public Date getLoseTime() {
		return loseTime;
	}

	public void setLoseTime(Date loseTime) {
		this.loseTime = loseTime;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
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

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public SysOrgDeploy getSysOrgDeploy() {
		return sysOrgDeploy;
	}

	public void setSysOrgDeploy(SysOrgDeploy sysOrgDeploy) {
		this.sysOrgDeploy = sysOrgDeploy;
	}

}