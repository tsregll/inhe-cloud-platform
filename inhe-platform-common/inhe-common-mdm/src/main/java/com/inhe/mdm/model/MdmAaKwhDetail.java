package com.inhe.mdm.model;

import java.util.Date;

public class MdmAaKwhDetail {
    private String deviceId;

    private String timeType;

    private String nowTime;
    
    private String refCode;

    private String orgId;

    private String deptId;

    private String lineId;

    private String tmId;

    private String deviceType;

    private String supplier;

    private String cstId;

    private String cstName;

    private String meterSort;

    private String measType;

    private String deviceNum;

    private String ptct;

    private Double kwhPa;

    private Double kwhPaPtct;

    private Double kwhPr;

    private Double kwhPrPtct;

    private Double kwhRaPtct;

    private Double kwhRa;

    private Double kwhRrPtct;

    private Double kwhRr;

    private Double totalKwhStart;

    private Double totalKwhEnd;

    private Double totalKwh;

    private Double kwhT1;

    private Double kwhT2;

    private Double kwhT3;

    private Double kwhT4;

    private Double kwhT5;

    private Double kwhT6;

    private Double kwhT7;

    private Double kwhT8;

    private Double oldTotalKwhStart;

    private Double oldTotalKwhEnd;

    private Double oldTotalKwh;

    private Double remainKwh;

    private Double remainDays;

    private Double lastKwhPaPtct;

    private Double lastKwhPrPtct;

    private Double lastKwhRaPtct;

    private Double lastKwhRrPtct;

    private Double lastTotalKwh;

    private Double lastKwhT1;

    private Double lastKwhT2;

    private Double lastKwhT3;

    private Double lastKwhT4;

    private Double lastKwhT5;

    private Double lastKwhT6;

    private Double lastKwhT7;

    private Double lastKwhT8;

    private String active;

    private Date cdate;

    private Double kwhPaPtctStart;

    private Double kwhPaPtctEnd;

    private Double kwhPrPtctStart;

    private Double kwhPrPtctEnd;

    private Double kwhRaPtctStart;

    private Double kwhRaPtctEnd;

    private Double kwhRrPtctStart;

    private Double kwhRrPtctEnd;
    
    private String veeResultDetail;

    private String veeEstmtDetail;

    private String veeEditDetail;
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType == null ? null : timeType.trim();
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime == null ? null : nowTime.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getTmId() {
        return tmId;
    }

    public void setTmId(String tmId) {
        this.tmId = tmId == null ? null : tmId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getCstId() {
        return cstId;
    }

    public void setCstId(String cstId) {
        this.cstId = cstId == null ? null : cstId.trim();
    }

    public String getCstName() {
        return cstName;
    }

    public void setCstName(String cstName) {
        this.cstName = cstName == null ? null : cstName.trim();
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort == null ? null : meterSort.trim();
    }

    public String getMeasType() {
        return measType;
    }

    public void setMeasType(String measType) {
        this.measType = measType == null ? null : measType.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getPtct() {
        return ptct;
    }

    public void setPtct(String ptct) {
        this.ptct = ptct == null ? null : ptct.trim();
    }

    public Double getKwhPa() {
        return kwhPa;
    }

    public void setKwhPa(Double kwhPa) {
        this.kwhPa = kwhPa;
    }

    public Double getKwhPaPtct() {
        return kwhPaPtct;
    }

    public void setKwhPaPtct(Double kwhPaPtct) {
        this.kwhPaPtct = kwhPaPtct;
    }

    public Double getKwhPr() {
        return kwhPr;
    }

    public void setKwhPr(Double kwhPr) {
        this.kwhPr = kwhPr;
    }

    public Double getKwhPrPtct() {
        return kwhPrPtct;
    }

    public void setKwhPrPtct(Double kwhPrPtct) {
        this.kwhPrPtct = kwhPrPtct;
    }

    public Double getKwhRaPtct() {
        return kwhRaPtct;
    }

    public void setKwhRaPtct(Double kwhRaPtct) {
        this.kwhRaPtct = kwhRaPtct;
    }

    public Double getKwhRa() {
        return kwhRa;
    }

    public void setKwhRa(Double kwhRa) {
        this.kwhRa = kwhRa;
    }

    public Double getKwhRrPtct() {
        return kwhRrPtct;
    }

    public void setKwhRrPtct(Double kwhRrPtct) {
        this.kwhRrPtct = kwhRrPtct;
    }

    public Double getKwhRr() {
        return kwhRr;
    }

    public void setKwhRr(Double kwhRr) {
        this.kwhRr = kwhRr;
    }

    public Double getTotalKwhStart() {
        return totalKwhStart;
    }

    public void setTotalKwhStart(Double totalKwhStart) {
        this.totalKwhStart = totalKwhStart;
    }

    public Double getTotalKwhEnd() {
        return totalKwhEnd;
    }

    public void setTotalKwhEnd(Double totalKwhEnd) {
        this.totalKwhEnd = totalKwhEnd;
    }

    public Double getTotalKwh() {
        return totalKwh;
    }

    public void setTotalKwh(Double totalKwh) {
        this.totalKwh = totalKwh;
    }

    public Double getKwhT1() {
        return kwhT1;
    }

    public void setKwhT1(Double kwhT1) {
        this.kwhT1 = kwhT1;
    }

    public Double getKwhT2() {
        return kwhT2;
    }

    public void setKwhT2(Double kwhT2) {
        this.kwhT2 = kwhT2;
    }

    public Double getKwhT3() {
        return kwhT3;
    }

    public void setKwhT3(Double kwhT3) {
        this.kwhT3 = kwhT3;
    }

    public Double getKwhT4() {
        return kwhT4;
    }

    public void setKwhT4(Double kwhT4) {
        this.kwhT4 = kwhT4;
    }

    public Double getKwhT5() {
        return kwhT5;
    }

    public void setKwhT5(Double kwhT5) {
        this.kwhT5 = kwhT5;
    }

    public Double getKwhT6() {
        return kwhT6;
    }

    public void setKwhT6(Double kwhT6) {
        this.kwhT6 = kwhT6;
    }

    public Double getKwhT7() {
        return kwhT7;
    }

    public void setKwhT7(Double kwhT7) {
        this.kwhT7 = kwhT7;
    }

    public Double getKwhT8() {
        return kwhT8;
    }

    public void setKwhT8(Double kwhT8) {
        this.kwhT8 = kwhT8;
    }

    public Double getOldTotalKwhStart() {
        return oldTotalKwhStart;
    }

    public void setOldTotalKwhStart(Double oldTotalKwhStart) {
        this.oldTotalKwhStart = oldTotalKwhStart;
    }

    public Double getOldTotalKwhEnd() {
        return oldTotalKwhEnd;
    }

    public void setOldTotalKwhEnd(Double oldTotalKwhEnd) {
        this.oldTotalKwhEnd = oldTotalKwhEnd;
    }

    public Double getOldTotalKwh() {
        return oldTotalKwh;
    }

    public void setOldTotalKwh(Double oldTotalKwh) {
        this.oldTotalKwh = oldTotalKwh;
    }

    public Double getRemainKwh() {
        return remainKwh;
    }

    public void setRemainKwh(Double remainKwh) {
        this.remainKwh = remainKwh;
    }

    public Double getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(Double remainDays) {
        this.remainDays = remainDays;
    }

    public Double getLastKwhPaPtct() {
        return lastKwhPaPtct;
    }

    public void setLastKwhPaPtct(Double lastKwhPaPtct) {
        this.lastKwhPaPtct = lastKwhPaPtct;
    }

    public Double getLastKwhPrPtct() {
        return lastKwhPrPtct;
    }

    public void setLastKwhPrPtct(Double lastKwhPrPtct) {
        this.lastKwhPrPtct = lastKwhPrPtct;
    }

    public Double getLastKwhRaPtct() {
        return lastKwhRaPtct;
    }

    public void setLastKwhRaPtct(Double lastKwhRaPtct) {
        this.lastKwhRaPtct = lastKwhRaPtct;
    }

    public Double getLastKwhRrPtct() {
        return lastKwhRrPtct;
    }

    public void setLastKwhRrPtct(Double lastKwhRrPtct) {
        this.lastKwhRrPtct = lastKwhRrPtct;
    }

    public Double getLastTotalKwh() {
        return lastTotalKwh;
    }

    public void setLastTotalKwh(Double lastTotalKwh) {
        this.lastTotalKwh = lastTotalKwh;
    }

    public Double getLastKwhT1() {
        return lastKwhT1;
    }

    public void setLastKwhT1(Double lastKwhT1) {
        this.lastKwhT1 = lastKwhT1;
    }

    public Double getLastKwhT2() {
        return lastKwhT2;
    }

    public void setLastKwhT2(Double lastKwhT2) {
        this.lastKwhT2 = lastKwhT2;
    }

    public Double getLastKwhT3() {
        return lastKwhT3;
    }

    public void setLastKwhT3(Double lastKwhT3) {
        this.lastKwhT3 = lastKwhT3;
    }

    public Double getLastKwhT4() {
        return lastKwhT4;
    }

    public void setLastKwhT4(Double lastKwhT4) {
        this.lastKwhT4 = lastKwhT4;
    }

    public Double getLastKwhT5() {
        return lastKwhT5;
    }

    public void setLastKwhT5(Double lastKwhT5) {
        this.lastKwhT5 = lastKwhT5;
    }

    public Double getLastKwhT6() {
        return lastKwhT6;
    }

    public void setLastKwhT6(Double lastKwhT6) {
        this.lastKwhT6 = lastKwhT6;
    }

    public Double getLastKwhT7() {
        return lastKwhT7;
    }

    public void setLastKwhT7(Double lastKwhT7) {
        this.lastKwhT7 = lastKwhT7;
    }

    public Double getLastKwhT8() {
        return lastKwhT8;
    }

    public void setLastKwhT8(Double lastKwhT8) {
        this.lastKwhT8 = lastKwhT8;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Double getKwhPaPtctStart() {
        return kwhPaPtctStart;
    }

    public void setKwhPaPtctStart(Double kwhPaPtctStart) {
        this.kwhPaPtctStart = kwhPaPtctStart;
    }

    public Double getKwhPaPtctEnd() {
        return kwhPaPtctEnd;
    }

    public void setKwhPaPtctEnd(Double kwhPaPtctEnd) {
        this.kwhPaPtctEnd = kwhPaPtctEnd;
    }

    public Double getKwhPrPtctStart() {
        return kwhPrPtctStart;
    }

    public void setKwhPrPtctStart(Double kwhPrPtctStart) {
        this.kwhPrPtctStart = kwhPrPtctStart;
    }

    public Double getKwhPrPtctEnd() {
        return kwhPrPtctEnd;
    }

    public void setKwhPrPtctEnd(Double kwhPrPtctEnd) {
        this.kwhPrPtctEnd = kwhPrPtctEnd;
    }

    public Double getKwhRaPtctStart() {
        return kwhRaPtctStart;
    }

    public void setKwhRaPtctStart(Double kwhRaPtctStart) {
        this.kwhRaPtctStart = kwhRaPtctStart;
    }

    public Double getKwhRaPtctEnd() {
        return kwhRaPtctEnd;
    }

    public void setKwhRaPtctEnd(Double kwhRaPtctEnd) {
        this.kwhRaPtctEnd = kwhRaPtctEnd;
    }

    public Double getKwhRrPtctStart() {
        return kwhRrPtctStart;
    }

    public void setKwhRrPtctStart(Double kwhRrPtctStart) {
        this.kwhRrPtctStart = kwhRrPtctStart;
    }

    public Double getKwhRrPtctEnd() {
        return kwhRrPtctEnd;
    }

    public void setKwhRrPtctEnd(Double kwhRrPtctEnd) {
        this.kwhRrPtctEnd = kwhRrPtctEnd;
    }
    
    public String getVeeResultDetail() {
        return veeResultDetail;
    }

    public void setVeeResultDetail(String veeResultDetail) {
        this.veeResultDetail = veeResultDetail == null ? null : veeResultDetail.trim();
    }

    public String getVeeEstmtDetail() {
        return veeEstmtDetail;
    }

    public void setVeeEstmtDetail(String veeEstmtDetail) {
        this.veeEstmtDetail = veeEstmtDetail == null ? null : veeEstmtDetail.trim();
    }

    public String getVeeEditDetail() {
        return veeEditDetail;
    }

    public void setVeeEditDetail(String veeEditDetail) {
        this.veeEditDetail = veeEditDetail == null ? null : veeEditDetail.trim();
    }
}