package com.inhe.sts.model;

import java.util.Date;

public class StsSmCounter {
	
	private String id;

	private Date bzDate;

    private String orgId;

    private Integer counter;
    
    private Integer counterQty;

    private Date counterTime;

    private String content;

    private String coperator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBzDate() {
        return bzDate;
    }

    public void setBzDate(Date bzDate) {
        this.bzDate = bzDate;
    }

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    
    public Integer getCounterQty() {
		return counterQty;
	}

	public void setCounterQty(Integer counterQty) {
		this.counterQty = counterQty;
	}

	public Date getCounterTime() {
        return counterTime;
    }

    public void setCounterTime(Date counterTime) {
        this.counterTime = counterTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }
}