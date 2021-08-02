package com.inhe.asset.model;

import java.math.BigDecimal;

public class VtSkStockProd {
	
	private String stockId;

	private String prodId;

	private BigDecimal nos;

	private BigDecimal freezeNos;

	private BigDecimal totalNos;

	private BigDecimal cost;

	private BigDecimal amount;

	private String notes;
	
    private String prodName;// 产品描述
    
    private String prodStatus;// 资产状态
    
    private String prodCode; //关联编号
    
    private String prodSpec;// 规格型号
    
    private String picUrl;// 图片微缩图
    
    private String typeId; // 资产类型编号
    
    private String units; // 单位
    
    private String skStockDesc; // 仓库描述
    
    private String skStockId; // 仓库id
    
    private String orgId; // 机构编号
    
    private String kind;// 资产种类
    
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
    
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getSkStockDesc() {
		return skStockDesc;
	}

	public void setSkStockDesc(String skStockDesc) {
		this.skStockDesc = skStockDesc;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSkStockId() {
		return skStockId;
	}

	public void setSkStockId(String skStockId) {
		this.skStockId = skStockId;
	}
	
}