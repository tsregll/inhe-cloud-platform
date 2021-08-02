package com.inhe.hes.model;

public class HesRuleAction {
	
	private String ruleId;

    private Byte sn;
	
    private String actMode;

    private String actTarget;

    private String actField;

    private String actValue;

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

	public String getActMode() {
        return actMode;
    }

    public void setActMode(String actMode) {
        this.actMode = actMode == null ? null : actMode.trim();
    }

    public String getActTarget() {
        return actTarget;
    }

    public void setActTarget(String actTarget) {
        this.actTarget = actTarget == null ? null : actTarget.trim();
    }

    public String getActField() {
        return actField;
    }

    public void setActField(String actField) {
        this.actField = actField == null ? null : actField.trim();
    }

    public String getActValue() {
        return actValue;
    }

    public void setActValue(String actValue) {
        this.actValue = actValue == null ? null : actValue.trim();
    }
}