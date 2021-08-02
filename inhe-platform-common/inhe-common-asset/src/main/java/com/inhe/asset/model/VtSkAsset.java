package com.inhe.asset.model;

import java.util.Date;

public class VtSkAsset extends SkAsset {
    
    private String orgId;
    
    private String prodCode;
    
    private String prodName;
    
    private String pvdCode;
    
    private String kind;
    
    private String skAssetIoId;
    
    private String remarks;
    
    private String operateType;
    
    private String isAsset;
    
    private Date startTime;
    
    private Date endTime;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getPvdCode() {
		return pvdCode;
	}

	public void setPvdCode(String pvdCode) {
		this.pvdCode = pvdCode;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getSkAssetIoId() {
		return skAssetIoId;
	}

	public void setSkAssetIoId(String skAssetIoId) {
		this.skAssetIoId = skAssetIoId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIsAsset() {
		return isAsset;
	}

	public void setIsAsset(String isAsset) {
		this.isAsset = isAsset;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}