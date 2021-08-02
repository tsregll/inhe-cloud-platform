package com.inhe.asset.model;

import java.math.BigDecimal;

public class VtSkStockIoItems {

	private String id;

	private Integer sn;
	
	private String prodId;

	private BigDecimal nos;

	private BigDecimal price;

	private BigDecimal amt;

	private String notes;

	// add
	private String prodCode;
	
	private String prodName;
	
	private String prodSpec;
	
	private String prodUnits;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public BigDecimal getNos() {
		return nos;
	}

	public void setNos(BigDecimal nos) {
		this.nos = nos;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public String getProdUnits() {
		return prodUnits;
	}

	public void setProdUnits(String prodUnits) {
		this.prodUnits = prodUnits;
	}
	
}