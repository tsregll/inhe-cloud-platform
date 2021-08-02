package com.inhe.asset.model;

import java.util.Date;

public class SkAssetLife {
	
	private String id;

	private Date opTime;
	
	private String opUser;

    private String opType;

    private String opContent;

    private String docId;
    
    private String docRefCode;

    private String prodId;

    private String status;

    private String lastStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpContent() {
		return opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocRefCode() {
		return docRefCode;
	}

	public void setDocRefCode(String docRefCode) {
		this.docRefCode = docRefCode;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

    
}