package com.inhe.message.model;

import java.util.Date;

public class MsgListSms {
	
	private String code;

    private String mobile;

    private Short times;
    
    private String content;
    
    private Date sendTime;

    private String sendStatus;
    
    private String eventType;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
    
    public Short getTimes() {
        return times;
    }

    public void setTimes(Short times) {
        this.times = times;
    }
    
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
    
    public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}