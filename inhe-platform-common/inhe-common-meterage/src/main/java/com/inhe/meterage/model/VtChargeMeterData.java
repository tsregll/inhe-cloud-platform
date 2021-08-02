package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class VtChargeMeterData {
    private String meterDataId;

    private String orgId;

    private String deptId;
    
    private String devId;

    private String devNum;
    
    private String devName;
    
    private String address;
    
    private String rmpRate;
    
    private String dataSource;

    private String dataDate;

    private Date writeDate;

    private BigDecimal ygZsd;

    private BigDecimal ygFl1Sd;

    private BigDecimal ygFl2Sd;

    private BigDecimal ygFl3Sd;

    private BigDecimal ygFl4Sd;

    private String dataState;
    
    private String freezeState;

    private String remark;

	public String getMeterDataId() {
		return meterDataId;
	}

	public void setMeterDataId(String meterDataId) {
		this.meterDataId = meterDataId;
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

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRmpRate() {
		return rmpRate;
	}

	public void setRmpRate(String rmpRate) {
		this.rmpRate = rmpRate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public BigDecimal getYgZsd() {
		return ygZsd;
	}

	public void setYgZsd(BigDecimal ygZsd) {
		this.ygZsd = ygZsd;
	}

	public BigDecimal getYgFl1Sd() {
		return ygFl1Sd;
	}

	public void setYgFl1Sd(BigDecimal ygFl1Sd) {
		this.ygFl1Sd = ygFl1Sd;
	}

	public BigDecimal getYgFl2Sd() {
		return ygFl2Sd;
	}

	public void setYgFl2Sd(BigDecimal ygFl2Sd) {
		this.ygFl2Sd = ygFl2Sd;
	}

	public BigDecimal getYgFl3Sd() {
		return ygFl3Sd;
	}

	public void setYgFl3Sd(BigDecimal ygFl3Sd) {
		this.ygFl3Sd = ygFl3Sd;
	}

	public BigDecimal getYgFl4Sd() {
		return ygFl4Sd;
	}

	public void setYgFl4Sd(BigDecimal ygFl4Sd) {
		this.ygFl4Sd = ygFl4Sd;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	

	public String getFreezeState() {
		return freezeState;
	}

	public void setFreezeState(String freezeState) {
		this.freezeState = freezeState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}


}