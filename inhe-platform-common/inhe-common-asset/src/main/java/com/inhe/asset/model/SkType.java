package com.inhe.asset.model;

import java.util.Date;

public class SkType {
	
	private String orgId;

    private String id;
	
    private String parentId;
    
    private String parentDescription;
    
    private String kind;

    private String description;

    private String subItem;

    private Short orderNo;

    private String status;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
	
	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSubItem() {
        return subItem;
    }

    public void setSubItem(String subItem) {
        this.subItem = subItem == null ? null : subItem.trim();
    }

    public Short getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public String getParentDescription() {
		return parentDescription;
	}

	public void setParentDescription(String parentDescription) {
		this.parentDescription = parentDescription == null ? null:parentDescription.trim();
	}
}