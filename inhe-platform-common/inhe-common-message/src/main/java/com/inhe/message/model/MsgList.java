package com.inhe.message.model;

import java.util.Date;

public class MsgList {
	
    private String code;
    
    private String orgId;

    private String source;
    
    private String eventType;

    private String refCode;

    private Short opTimes;

    private Date opStart;

    private Date opEnd;

    private String opStatus;
    
    private String detail;
    
    private String errMsg;

    private String coperator;

    private Date cdate;
    
    private Boolean mailFinish = false;
    
    private Boolean smsFinish = false;
    
    private Boolean userFinish = false;
    
    private String receive;

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

	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
    
    public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public Short getOpTimes() {
        return opTimes;
    }

    public void setOpTimes(Short opTimes) {
        this.opTimes = opTimes;
    }

    public Date getOpStart() {
        return opStart;
    }

    public void setOpStart(Date opStart) {
        this.opStart = opStart;
    }

    public Date getOpEnd() {
        return opEnd;
    }

    public void setOpEnd(Date opEnd) {
        this.opEnd = opEnd;
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus == null ? null : opStatus.trim();
    }
    
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getErrMsg() {
		return errMsg == null ? "" : errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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

	public Boolean getMailFinish() {
		return mailFinish;
	}

	public void setMailFinish(Boolean mailFinish) {
		this.mailFinish = mailFinish;
	}

	public Boolean getSmsFinish() {
		return smsFinish;
	}

	public void setSmsFinish(Boolean smsFinish) {
		this.smsFinish = smsFinish;
	}

	public Boolean getUserFinish() {
		return userFinish;
	}

	public void setUserFinish(Boolean userFinish) {
		this.userFinish = userFinish;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}
}