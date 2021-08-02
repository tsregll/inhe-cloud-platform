package com.inhe.sts.model;

import java.util.Date;

public class StsSmLoadRst {
    private String code;

    private String smid;

    private Date rstTime;

    private Date klTime;

    private String opStatus;

    private String opResult;

    private String coperator;

    private Date cdate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSmid() {
        return smid;
    }

    public void setSmid(String smid) {
        this.smid = smid;
    }

    public Date getRstTime() {
        return rstTime;
    }

    public void setRstTime(Date rstTime) {
        this.rstTime = rstTime;
    }

    public Date getKlTime() {
        return klTime;
    }

    public void setKlTime(Date klTime) {
        this.klTime = klTime;
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}