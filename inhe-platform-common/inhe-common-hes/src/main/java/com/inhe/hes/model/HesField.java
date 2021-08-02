package com.inhe.hes.model;

import java.util.Date;

public class HesField {
    private String id;

    private String type;

    private String industryType;

    private String shortCode;

    private Integer orderNo;

    private String description;

    private String fieldType;

    private String rwType;

    private String dataType;

    private String dataRange;

    private String dataFormat;

    private String dataUnit;

    private String plugin;
    
    private String actived;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    //add
    private String prodId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getRwType() {
        return rwType;
    }

    public void setRwType(String rwType) {
        this.rwType = rwType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }
    
	public String getActived() {
		return actived;
	}

	public void setActived(String actived) {
		this.actived = actived;
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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
    
    
}