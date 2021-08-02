package com.inhe.admin.model;

import java.util.Date;

public class SysLogUser {
    private String logId;

    private String userId;

    private String orgId;

    private String ip;

    private String actionId;

    private Date operTime;
    
    private Date operTimeT;

    private String content;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Date getOperTimeT() {
		return operTimeT;
	}

	public void setOperTimeT(Date operTimeT) {
		this.operTimeT = operTimeT;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}