package com.inhe.message.model;

import java.util.Date;

public class WeatherDetail {
	
	private String code;

    private String type;

    private Date time;
    
    private String city;
    
    private String value;

    private Date cdate;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}