package com.inhe.sts.dto;

import java.util.Date;

public class VTStsSm {
	
    private String id;

    private String smid;

    private String kind;

    private String type;

    private String ip;
    
    private Integer port;
    
    private Integer regno;
    
    private String sgc;

	private String krn;

	private String ken;
	
	private Date baseTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmid() {
		return smid;
	}

	public void setSmid(String smid) {
		this.smid = smid;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getRegno() {
		return regno;
	}

	public void setRegno(Integer regno) {
		this.regno = regno;
	}

	public String getSgc() {
		return sgc;
	}

	public void setSgc(String sgc) {
		this.sgc = sgc;
	}

	public String getKrn() {
		return krn;
	}

	public void setKrn(String krn) {
		this.krn = krn;
	}

	public String getKen() {
		return ken;
	}

	public void setKen(String ken) {
		this.ken = ken;
	}

	public Date getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(Date baseTime) {
		this.baseTime = baseTime;
	}
	
}