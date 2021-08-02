package com.inhe.asset.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VtSkStockMove {
    private String id;

    private String orgId;

    private String deptId;

    private String code;

    private Date bzTime;

    private String stockIdO;

    private String stockIdI;

    private String handler;

    private BigDecimal nos;

    private BigDecimal amt;

    private String remarks;

    private String status;

    private String checker;

    private Date checkTime;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    // add
    private List<VtSkStockMoveItems> moveItems;
    
    private Date startTime;
    
    private Date endTime;
    
    private String type;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBzTime() {
		return bzTime;
	}

	public void setBzTime(Date bzTime) {
		this.bzTime = bzTime;
	}

	public String getStockIdO() {
		return stockIdO;
	}

	public void setStockIdO(String stockIdO) {
		this.stockIdO = stockIdO;
	}

	public String getStockIdI() {
		return stockIdI;
	}

	public void setStockIdI(String stockIdI) {
		this.stockIdI = stockIdI;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public BigDecimal getNos() {
		return nos;
	}

	public void setNos(BigDecimal nos) {
		this.nos = nos;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<VtSkStockMoveItems> getMoveItems() {
		return moveItems;
	}

	public void setMoveItems(List<VtSkStockMoveItems> moveItems) {
		this.moveItems = moveItems;
	}
    
}