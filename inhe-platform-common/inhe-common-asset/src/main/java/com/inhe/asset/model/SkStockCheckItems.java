package com.inhe.asset.model;

import java.math.BigDecimal;

public class SkStockCheckItems {
    private String id;

    private String prodId;

    private Integer sn;

    private BigDecimal nos1;

    private BigDecimal nos2;

    private BigDecimal nos3;

    private BigDecimal price;

    private BigDecimal amt;

    private String note;
    
    //add
    private String prodName;
    
    //add
    private String units;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public BigDecimal getNos1() {
        return nos1;
    }

    public void setNos1(BigDecimal nos1) {
        this.nos1 = nos1;
    }

    public BigDecimal getNos2() {
        return nos2;
    }

    public void setNos2(BigDecimal nos2) {
        this.nos2 = nos2;
    }

    public BigDecimal getNos3() {
        return nos3;
    }

    public void setNos3(BigDecimal nos3) {
        this.nos3 = nos3;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
    
    
}