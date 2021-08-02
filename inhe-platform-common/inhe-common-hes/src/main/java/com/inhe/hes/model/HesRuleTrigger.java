package com.inhe.hes.model;

public class HesRuleTrigger {
	
	private String ruleId;

    private Byte sn;
    
    private String prodId;

    private String deviceId;

    private String triMode;

    private String triKey;

    private String triCondition;

    private String triValue;
    
    private String orgId;

    public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Byte getSn() {
		return sn;
	}

	public void setSn(Byte sn) {
		this.sn = sn;
	}

	public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getTriMode() {
        return triMode;
    }

    public void setTriMode(String triMode) {
        this.triMode = triMode == null ? null : triMode.trim();
    }

    public String getTriKey() {
        return triKey;
    }

    public void setTriKey(String triKey) {
        this.triKey = triKey == null ? null : triKey.trim();
    }

    public String getTriCondition() {
        return triCondition;
    }

    public void setTriCondition(String triCondition) {
        this.triCondition = triCondition == null ? null : triCondition.trim();
    }

    public String getTriValue() {
        return triValue;
    }

    public void setTriValue(String triValue) {
        this.triValue = triValue == null ? null : triValue.trim();
    }

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}