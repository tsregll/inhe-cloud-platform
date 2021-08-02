package com.inhe.hes.model;

import java.util.Date;

public class HesPlugin {
    private String plugId;

    private String plugName;

    private String remarks;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

    private String detail;

    public String getPlugId() {
        return plugId;
    }

    public void setPlugId(String plugId) {
        this.plugId = plugId;
    }

    public String getPlugName() {
        return plugName;
    }

    public void setPlugName(String plugName) {
        this.plugName = plugName == null ? null : plugName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}