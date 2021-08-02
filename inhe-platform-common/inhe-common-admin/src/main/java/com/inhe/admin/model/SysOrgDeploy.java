package com.inhe.admin.model;

import java.util.Date;

public class SysOrgDeploy {
	
    private String deployId;

    private String orgId;

    private String deployName;
    
    private String deployDescription;
    
    private String deployPwd;
    
    private String deployImg;

    private String isPublic;

    private String online;

    private String deployStatus;

    private String remark;

    private Date cdate;

    private Date udate;

    public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeployName() {
		return deployName;
	}

	public void setDeployName(String deployName) {
		this.deployName = deployName;
	}

	public String getDeployDescription() {
		return deployDescription;
	}

	public void setDeployDescription(String deployDescription) {
		this.deployDescription = deployDescription;
	}

	public String getDeployPwd() {
		return deployPwd;
	}

	public void setDeployPwd(String deployPwd) {
		this.deployPwd = deployPwd;
	}

	public String getDeployImg() {
		return deployImg;
	}

	public void setDeployImg(String deployImg) {
		this.deployImg = deployImg;
	}

	public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

	public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online == null ? null : online.trim();
    }

	public String getDeployStatus() {
		return deployStatus;
	}

	public void setDeployStatus(String deployStatus) {
		this.deployStatus = deployStatus;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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