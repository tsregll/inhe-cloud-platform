package com.inhe.message.model;

import java.util.Date;

public class MsgVerify {
	
    private String code;

    private String receive;

    private String cdkey;

    private Date createTime;

    private Date dueTime;

    private String valid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}