package com.inhe.mdm.model;

public class MdmVeeFields {
    private String type;

    private String fieldId;
    
    private String orderNo;

    private String actived;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }
    
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived == null ? null : actived.trim();
    }
}