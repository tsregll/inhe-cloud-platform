package com.inhe.admin.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public class SysUser {

	private String userId;
	
	private String userCode;

	private String userName;

	private String userDesc;

	private String userPwd;
	
	private String pwdStrength;
	
	private Date pwdChangeTime;
	
	private String pwdHistory;
	
	private String userType;

	private String mobile;

	private String email;

	private String orgId;

	private String deptId;

	private Integer roleId;

	private String roleIdOthers;

	private String userStatus;
	
	private String checkSum;
	
	private Integer loginTimes;

	private String remark;

	private String coperator;

	private Date cdate;

	private String uoperator;

	private Date udate;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getPwdStrength() {
		return pwdStrength;
	}

	public void setPwdStrength(String pwdStrength) {
		this.pwdStrength = pwdStrength;
	}

	public Date getPwdChangeTime() {
		return pwdChangeTime;
	}

	public void setPwdChangeTime(Date pwdChangeTime) {
		this.pwdChangeTime = pwdChangeTime;
	}

	public String getPwdHistory() {
		return pwdHistory;
	}

	public void setPwdHistory(String pwdHistory) {
		this.pwdHistory = pwdHistory;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleIdOthers() {
		return roleIdOthers;
	}

	public void setRoleIdOthers(String roleIdOthers) {
		this.roleIdOthers = roleIdOthers;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public boolean checkCheckSum(boolean strict) {
		if(!strict){
			return true;
		}
		String checkSum = this.getNewCheckSum();
		if(!this.checkSum.equals(checkSum)) {
			return false;
		}
		return true;
	}
	
	public void createCheckSum() {
		this.checkSum = this.getNewCheckSum();
	}
	
	private String getNewCheckSum() {
		String checkSum = "";
		Class<? extends SysUser> cls = this.getClass();  
        Field[] fields = cls.getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>();
        
        for(int i=0; i<fields.length; i++){
        	Field f = fields[i];  
        	String name = f.getName().toLowerCase();
        	String value = null;
			try {
				value = f.get(this).toString().toLowerCase();
			} catch (Exception e) {
			}
        	if("checksum".equals(name) || "udate".equals(name) || "logintimes".equals(name) || value==null){
        		continue;
        	}
        	map.put(name, value);
        }  
        Object[] set = map.keySet().toArray();
        Arrays.sort(set);
        for (Object key : set) {
        	checkSum  = checkSum + ";" +  key + ":" + map.get(key);
		}
        return DigestUtils.md5Hex(checkSum).toLowerCase();
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
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
	
	public void setAttr(SysUser sysUser) {
		Class<? extends SysUser> that = this.getClass();
		
		Class<? extends SysUser> cls = sysUser.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for(int i=0; i<fields.length; i++){
        	Field f = fields[i];  
        	String name = f.getName();
        	Object value;
			try {
				value = f.get(sysUser);
			} catch (Exception e) {
				value = null;
			}
        	if("checkSum".equals(name) || "udate".equals(name) || value==null){
        		continue;
        	}
        	try {
        		Field currF = that.getDeclaredField(name);
            	currF.setAccessible(true);
            	f.set(this, value);
        	} catch (Exception e) {
				
			}
        }  
	}
}