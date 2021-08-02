package com.inhe.admin.model;

import java.util.Date;

public class SysRegion {
    private String code;

    private String description;

    private String refCode;

    private Integer orderNo;

    private String v1;

    private String v2;

    private String v3;

    private String v4;
    
    private String v5;

    private String v6;

    private String v7;

    private String v8;
    
    private String v9;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1 == null ? null : v1.trim();
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2 == null ? null : v2.trim();
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3 == null ? null : v3.trim();
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4 == null ? null : v4.trim();
    }

    public String getV5() {
		return v5;
	}

	public void setV5(String v5) {
		this.v5 = v5;
	}

	public String getV6() {
		return v6;
	}

	public void setV6(String v6) {
		this.v6 = v6;
	}

	public String getV7() {
		return v7;
	}

	public void setV7(String v7) {
		this.v7 = v7;
	}

	public String getV8() {
		return v8;
	}

	public void setV8(String v8) {
		this.v8 = v8;
	}

	public String getV9() {
		return v9;
	}

	public void setV9(String v9) {
		this.v9 = v9;
	}

	public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator == null ? null : coperator.trim();
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
        this.uoperator = uoperator == null ? null : uoperator.trim();
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}