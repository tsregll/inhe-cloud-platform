package com.inhe.sts.model;

import java.util.Date;

public class StsDevice {
    private String devId;

    private String orgId;

    private String devType;

    private String devNum;

    private String sgc;

    private String ken;

    private String krn;

    private String ti;

    private String ea;

    private String tct;

    private Date baseDate;

    private String tid;

    private Integer tidOffset;
    
    private String status;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getSgc() {
        return sgc;
    }

    public void setSgc(String sgc) {
        this.sgc = sgc;
    }

    public String getKen() {
        return ken;
    }

    public void setKen(String ken) {
        this.ken = ken;
    }

    public String getKrn() {
        return krn;
    }

    public void setKrn(String krn) {
        this.krn = krn;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public String getTct() {
        return tct;
    }

    public void setTct(String tct) {
        this.tct = tct;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getTidOffset() {
        return tidOffset;
    }

    public void setTidOffset(Integer tidOffset) {
        this.tidOffset = tidOffset;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}