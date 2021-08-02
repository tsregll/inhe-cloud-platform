package com.inhe.asset.model;

import java.util.Date;
import java.util.List;

public class SkAssetIo {
	
	private String id;

    private String orgId;

    private String deptId;

    private Date opTime;

    private String opType;

    private String refCode;

    private String remarks;

    private Integer nos;

    private String field01;

    private String field02;

    private String field03;

    private String field04;

    private String field05;

    private String field06;

    private String status;

    private String checker;

    private Date checkTime;
    
    private String checkContent;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    // add
    private String skAssetItemsId;
    
    private String skAssetId;
    
    private List<SkAssetIoItems> items;
    
    private String type;
    
    private Date startTime;
    
    private Date endTime;
    
    private String stockId;
    
    private String deptOwner;
    
    private String dealType;// 处置类型
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getNos() {
		return nos;
	}

	public void setNos(Integer nos) {
		this.nos = nos;
	}

	public String getField01() {
		return field01;
	}

	public void setField01(String field01) {
		this.field01 = field01;
	}

	public String getField02() {
		return field02;
	}

	public void setField02(String field02) {
		this.field02 = field02;
	}

	public String getField03() {
		return field03;
	}

	public void setField03(String field03) {
		this.field03 = field03;
	}

	public String getField04() {
		return field04;
	}

	public void setField04(String field04) {
		this.field04 = field04;
	}

	public String getField05() {
		return field05;
	}

	public void setField05(String field05) {
		this.field05 = field05;
	}

	public String getField06() {
		return field06;
	}

	public void setField06(String field06) {
		this.field06 = field06;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
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

	public String getUoperator() {
		return uoperator;
	}

	public void setUoperator(String uoperator) {
		this.uoperator = uoperator;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getSkAssetItemsId() {
		return skAssetItemsId;
	}

	public void setSkAssetItemsId(String skAssetItemsId) {
		this.skAssetItemsId = skAssetItemsId;
	}

	public String getSkAssetId() {
		return skAssetId;
	}

	public void setSkAssetId(String skAssetId) {
		this.skAssetId = skAssetId;
	}

	public List<SkAssetIoItems> getItems() {
		return items;
	}

	public void setItems(List<SkAssetIoItems> items) {
		this.items = items;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getDeptOwner() {
		return deptOwner;
	}

	public void setDeptOwner(String deptOwner) {
		this.deptOwner = deptOwner;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
}