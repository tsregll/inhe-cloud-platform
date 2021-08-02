package com.inhe.mdm.model;

import java.util.Date;

public class MdmVeeFieldsEstimate {
    private String type;

    private String estimateId;
    
    private String actived;

    private String coperator;

    private Date cdate;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId == null ? null : estimateId.trim();
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived == null ? null : actived.trim();
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