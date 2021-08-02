package com.inhe.message.model;

import java.util.Date;

public class EventRole {
	
    private String code;
    
    private String orgId;
    
    private String description;
    
    private String typeList;

    private String emails;

    private String mobiles;

    private String users;
    
    public String status;

    private String uoperator;

    private Date udate;

    private String coperator;

    private Date cdate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeList() {
		return typeList;
	}

	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails == null ? null : emails.trim();
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? null : mobiles.trim();
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users == null ? null : users.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUoperator() {
        return uoperator;
    }

    public void setUoperator(String uoperator) {
        this.uoperator = uoperator == null ? null : uoperator.trim();
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator == null ? null : coperator.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}