package com.inhe.api.model;

import java.math.BigDecimal;
import java.util.Date;

public class OtwOrder {
    private String id;
    
    private String type;

    private String userId;

    private String prodId;

    private String vendorId;

    private String contractNo;
    
    private String contractType;

    private String contractOrder;

    private String payOrder;

    private BigDecimal amt;

    private String status;
    
    private String preDetail;
    
    private String payParams;
    
    private String payReturn;
    
    private String formalDetail;
    
    private String refundDetail;
    
    private Date cdate;

    private Date udate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

    public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractOrder() {
		return contractOrder;
	}

	public void setContractOrder(String contractOrder) {
		this.contractOrder = contractOrder;
	}

	public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder == null ? null : payOrder.trim();
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPreDetail() {
		return preDetail;
	}

	public void setPreDetail(String preDetail) {
		this.preDetail = preDetail;
	}

	public String getPayParams() {
		return payParams;
	}

	public void setPayParams(String payParams) {
		this.payParams = payParams;
	}

	public String getPayReturn() {
		return payReturn;
	}

	public void setPayReturn(String payReturn) {
		this.payReturn = payReturn;
	}

	public String getFormalDetail() {
		return formalDetail;
	}

	public void setFormalDetail(String formalDetail) {
		this.formalDetail = formalDetail;
	}

	public String getRefundDetail() {
		return refundDetail;
	}

	public void setRefundDetail(String refundDetail) {
		this.refundDetail = refundDetail;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}