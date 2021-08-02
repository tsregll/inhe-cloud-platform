package com.inhe.admin.model;

import java.util.Date;

public class SysUserDetails {
    private String userId;
    
    private String userCode;

    private String name;

    private String sex;

    private Date birthday;

    private String workTel;

    private String workFax;

    private String homeAdd;

    private String homeTel;

    private String icon;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getWorkFax() {
        return workFax;
    }

    public void setWorkFax(String workFax) {
        this.workFax = workFax;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

	@Override
	public String toString() {
		return "SysUserDetails [userId=" + userId + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", workTel=" + workTel + ", workFax=" + workFax + ", homeAdd=" + homeAdd + ", homeTel=" + homeTel
				+ ", icon=" + icon + "]";
	}
}