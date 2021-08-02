package com.inhe.admin.dto;

public class VTAuthLogin {
    private String password;

    private String remember_me;

    private String username;

    private String email;

    private String mobile;
    
    private String token;
    
    private String captcha;
    
    private String pblKey;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemember_me() {
		return remember_me;
	}

	public void setRemember_me(String remember_me) {
		this.remember_me = remember_me;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getPblKey() {
		return pblKey;
	}

	public void setPblKey(String pblKey) {
		this.pblKey = pblKey;
	}

	@Override
	public String toString() {
		return "SysAuthLogin [password=" + password + ", remember_me=" + remember_me + ", username=" + username
				+ ", email=" + email + ", mobile=" + mobile + ", captcha=" + captcha + ", pblKey=" + pblKey + "]";
	}
	
}