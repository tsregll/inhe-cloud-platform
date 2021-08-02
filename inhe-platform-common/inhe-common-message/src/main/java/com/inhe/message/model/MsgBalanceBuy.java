package com.inhe.message.model;

import java.math.BigDecimal;
import java.util.Date;

public class MsgBalanceBuy {
    private String buyId;

    private String orgId;

    private String type;

    private Integer total;

    private BigDecimal amt;

    private String coperator;

    private Date cdate;

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId == null ? null : buyId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
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
}