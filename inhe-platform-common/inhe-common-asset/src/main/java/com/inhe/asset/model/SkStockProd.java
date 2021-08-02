package com.inhe.asset.model;

import java.math.BigDecimal;

public class SkStockProd {

	private String stockId;

	private String prodId;

	private BigDecimal nos;

	private BigDecimal freezeNos;

	private BigDecimal totalNos;

	private BigDecimal cost;

	private BigDecimal amount;

	private String notes;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public BigDecimal getFreezeNos() {
		return freezeNos;
	}

	public void setFreezeNos(BigDecimal freezeNos) {
		this.freezeNos = freezeNos;
	}

	public BigDecimal getTotalNos() {
		return totalNos;
	}

	public void setTotalNos(BigDecimal totalNos) {
		this.totalNos = totalNos;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}