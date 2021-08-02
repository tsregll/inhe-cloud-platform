package com.inhe.asset.model;

import java.math.BigDecimal;

public class SkAssetImportItems {
	private String id;

    private String importId;

    private String prodId;

    private String assetCode;

    private BigDecimal nos;

    private Integer state;

    private String note;
    
    //add
    private String assetName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public BigDecimal getNos() {
		return nos;
	}

	public void setNos(BigDecimal nos) {
		this.nos = nos;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

    
}