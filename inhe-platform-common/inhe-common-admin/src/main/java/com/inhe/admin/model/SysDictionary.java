package com.inhe.admin.model;

import java.util.Date;
import java.util.List;

public class SysDictionary {
	
	private String id;

	private String tableType;

	private String parentCode;

	private String code;
	
    private String description;

    private Integer orderNo;

    private String v1;

    private String v2;

    private String v3;

    private String v4;

    private String remark;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    private String table;
    
    private List<SysDictionary> children;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<SysDictionary> getChildren() {
		return children;
	}

	public void setChildren(List<SysDictionary> children) {
		this.children = children;
	}
}