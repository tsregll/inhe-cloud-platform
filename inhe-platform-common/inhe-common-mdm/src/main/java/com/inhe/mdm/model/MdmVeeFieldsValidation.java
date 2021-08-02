package com.inhe.mdm.model;

import java.util.Date;

public class MdmVeeFieldsValidation {
    private String type;

    private String validationId;
    
    private String actived;

    private String coperator;

    private Date cdate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId == null ? null : validationId.trim();
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