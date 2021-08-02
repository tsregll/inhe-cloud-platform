package com.inhe.asset.model;

import java.math.BigDecimal;
import java.util.Date;

public class SkAssetImport {
	private String id;

    private String orgId;

    private String deptId;

    private String prodId;

    private String deptOwner;

    private String stockId;

    private BigDecimal snos;

    private BigDecimal fnos;

    private String refCode;

    private String handler;

    private String remarks;

    private String status;

    private String checker;

    private Date checkTime;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    //add 
    private String typeId;
    
    private String prodCode;
    
    private String prodName;

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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getDeptOwner() {
		return deptOwner;
	}

	public void setDeptOwner(String deptOwner) {
		this.deptOwner = deptOwner;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getSnos() {
		return snos;
	}

	public void setSnos(BigDecimal snos) {
		this.snos = snos;
	}

	public BigDecimal getFnos() {
		return fnos;
	}

	public void setFnos(BigDecimal fnos) {
		this.fnos = fnos;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

}