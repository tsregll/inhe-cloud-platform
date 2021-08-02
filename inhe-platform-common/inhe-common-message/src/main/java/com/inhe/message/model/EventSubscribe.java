package com.inhe.message.model;

import java.util.Date;

public class EventSubscribe {
	
	private String refCode;
	
	private String numb;

    private String eventType;
    
    private String numbType;
	
    private String params;

    private Date cdate;
    
    public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
    
    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb == null ? null : numb.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

	public String getNumbType() {
		return numbType;
	}

	public void setNumbType(String numbType) {
		this.numbType = numbType;
	}

	public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}