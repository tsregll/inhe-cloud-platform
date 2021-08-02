package com.inhe.sts.model;

import java.util.Date;

public class StsTransactions {
	
	private String orgId;

    private String id;
    
    private Date transTime;

    private String transType;

    private String refId;

    private String deviceNum;

    private String keyChanged;

    private Short keyRollover;

    private String oldKey;

    private String newKey;

    private String kcToken;

    private String cmValue;

    private String cmNToken;

    private Date cmCTime;

    private String cmCToken;
    
    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getKeyChanged() {
		return keyChanged;
	}

	public void setKeyChanged(String keyChanged) {
		this.keyChanged = keyChanged;
	}

	public Short getKeyRollover() {
		return keyRollover;
	}

	public void setKeyRollover(Short keyRollover) {
		this.keyRollover = keyRollover;
	}

	public String getOldKey() {
		return oldKey;
	}

	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}

	public String getNewKey() {
		return newKey;
	}

	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}

	public String getKcToken() {
		return kcToken;
	}

	public void setKcToken(String kcToken) {
		this.kcToken = kcToken;
	}

	public String getCmValue() {
		return cmValue;
	}

	public void setCmValue(String cmValue) {
		this.cmValue = cmValue;
	}

	public String getCmNToken() {
		return cmNToken;
	}

	public void setCmNToken(String cmNToken) {
		this.cmNToken = cmNToken;
	}

	public Date getCmCTime() {
		return cmCTime;
	}

	public void setCmCTime(Date cmCTime) {
		this.cmCTime = cmCTime;
	}

	public String getCmCToken() {
		return cmCToken;
	}

	public void setCmCToken(String cmCToken) {
		this.cmCToken = cmCToken;
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
    
}