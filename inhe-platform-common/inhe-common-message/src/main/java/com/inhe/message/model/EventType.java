package com.inhe.message.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventType {
    private String code;

    private String description;

    private String keyword;
    
    private String joinSublist;
    
    private String status;

    private String uoperator;

    private Date udate;

    private String coperator;

    private Date cdate;
    
    private Map<String, EventTypeLang> langs = new HashMap<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getJoinSublist() {
		return joinSublist;
	}

	public void setJoinSublist(String joinSublist) {
		this.joinSublist = joinSublist;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public Map<String, EventTypeLang> getLangs() {
		return langs;
	}

	public void setLangs(List<EventTypeLang> langs) {
		for (EventTypeLang eventTypeLang : langs) {
			this.langs.put(eventTypeLang.getSendType(), eventTypeLang);
		}
	}

}