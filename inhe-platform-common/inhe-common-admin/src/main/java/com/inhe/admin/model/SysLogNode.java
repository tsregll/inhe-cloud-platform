package com.inhe.admin.model;

import java.util.Date;

public class SysLogNode {
    private String logId;

    private String nodeName;

    private String deployId;

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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
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