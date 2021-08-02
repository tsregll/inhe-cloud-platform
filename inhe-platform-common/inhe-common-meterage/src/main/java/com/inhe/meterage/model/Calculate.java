package com.inhe.meterage.model;

import java.math.BigDecimal;

/**
 * 费用收缴计算实体类
 */
public class Calculate {
	
	private String verifyCode; // 验证码
	
	private String userId; // 当前登录用户
	
    private String devId; // 表号

    private String payType; // 支付方式：0-现金，1-个人账户

    private String orgId; // 机构编号

    private String deptId; // 部门编号

    private double payment; // 计算金额
    
    private BigDecimal totalPayMoney; // 支付金额
    
    private String contractNo; // 合同编号

    private String tariffId; // 系统费率id

    private Integer tariffVersion; // 费率版本号

    private String buyElectricityType; // 预付费购买方式：0-金额购买，1-数量购买

    private double buyElectricityCount; // 根据预付费购买方式决定，金额或数量

    private String prePayOrPostPayType; // 预付费还是后付费

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	public BigDecimal getTotalPayMoney() {
		return totalPayMoney;
	}

	public void setTotalPayMoney(BigDecimal totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public Integer getTariffVersion() {
		return tariffVersion;
	}

	public void setTariffVersion(Integer tariffVersion) {
		this.tariffVersion = tariffVersion;
	}

	public String getBuyElectricityType() {
		return buyElectricityType;
	}

	public void setBuyElectricityType(String buyElectricityType) {
		this.buyElectricityType = buyElectricityType;
	}

	public double getBuyElectricityCount() {
		return buyElectricityCount;
	}

	public void setBuyElectricityCount(double buyElectricityCount) {
		this.buyElectricityCount = buyElectricityCount;
	}

	public String getPrePayOrPostPayType() {
		return prePayOrPostPayType;
	}

	public void setPrePayOrPostPayType(String prePayOrPostPayType) {
		this.prePayOrPostPayType = prePayOrPostPayType;
	}
    
}
