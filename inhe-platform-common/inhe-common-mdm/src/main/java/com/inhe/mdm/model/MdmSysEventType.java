package com.inhe.mdm.model;

public class MdmSysEventType {
    private Integer code;

    private String refCode;

    private Short pdelay;

    private String description;

    private String type;

    private String subionFlag;

    private String fieldId;

    private String params;

    private String processType;

    private String mode;

    private String level;

    private String sendType;

    private String funId;

    private String tempWeb;

    private String tempWebType;

    private String tempSms;

    private String tempEmail;

    private String actived;

    private String stealFlag;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public Short getPdelay() {
        return pdelay;
    }

    public void setPdelay(Short pdelay) {
        this.pdelay = pdelay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSubionFlag() {
        return subionFlag;
    }

    public void setSubionFlag(String subionFlag) {
        this.subionFlag = subionFlag == null ? null : subionFlag.trim();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType == null ? null : processType.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public String getFunId() {
        return funId;
    }

    public void setFunId(String funId) {
        this.funId = funId == null ? null : funId.trim();
    }

    public String getTempWeb() {
        return tempWeb;
    }

    public void setTempWeb(String tempWeb) {
        this.tempWeb = tempWeb == null ? null : tempWeb.trim();
    }

    public String getTempWebType() {
        return tempWebType;
    }

    public void setTempWebType(String tempWebType) {
        this.tempWebType = tempWebType == null ? null : tempWebType.trim();
    }

    public String getTempSms() {
        return tempSms;
    }

    public void setTempSms(String tempSms) {
        this.tempSms = tempSms == null ? null : tempSms.trim();
    }

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail == null ? null : tempEmail.trim();
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived == null ? null : actived.trim();
    }

    public String getStealFlag() {
        return stealFlag;
    }

    public void setStealFlag(String stealFlag) {
        this.stealFlag = stealFlag == null ? null : stealFlag.trim();
    }
}