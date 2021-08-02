package com.inhe.mdm.model;

import java.math.BigDecimal;
import java.util.Date;

public class MdmDayFreezing {
    private Integer dataType;

    private String deviceId;

    private String dataPeriod;

    private String fzDataType;
    
    private String deviceNum;

    private String orgId;

    private String deptId;

    private String readType;

    private String readStatus;

    private Short scTimes;

    private String cmdCode;

    private String opStatus;

    private Double valueFreezing;

    private Date readTime;

    private String ptct;

    private Double ptctValueFreezing;

    private Double valueFreezingT1;

    private Double ptctValueFreezingT1;

    private Double valueFreezingT2;

    private Double ptctValueFreezingT2;

    private Double valueFreezingT3;

    private Double ptctValueFreezingT3;

    private Double valueFreezingT4;

    private Double ptctValueFreezingT4;

    private Double valueFreezingT5;

    private Double ptctValueFreezingT5;

    private Double valueFreezingT6;

    private Double ptctValueFreezingT6;

    private Double valueFreezingT7;

    private Double ptctValueFreezingT7;

    private Double valueFreezingT8;

    private Double ptctValueFreezingT8;

    private Double kwhPa;

    private Double kwhPaPtct;

    private Double kwhPr;

    private Double kwhPrPtct;

    private Double kwhRa;

    private Double kwhRaPtct;

    private Double kwhRr;

    private Double kwhRrPtct;

    private String appRcvStatus;

    private String appReceiver;

    private String dataSource;

    private BigDecimal factor;

    private BigDecimal compensateRate;

    private BigDecimal maxDemand;

    private Date maxDemandTime;

    private String coperator;

    private Date cdate;

    private Date udate;

    private Double kwhPaT1;

    private Double kwhPaPtctT1;

    private Double kwhPaT2;

    private Double kwhPaPtctT2;

    private Double kwhPaT3;

    private Double kwhPaPtctT3;

    private Double kwhPaT4;

    private Double kwhPaPtctT4;

    private Double kwhPrT1;

    private Double kwhPrPtctT1;

    private Double kwhPrT2;

    private Double kwhPrPtctT2;

    private Double kwhPrT3;

    private Double kwhPrPtctT3;

    private Double kwhPrT4;

    private Double kwhPrPtctT4;

    private Double kwhRaT1;

    private Double kwhRaPtctT1;

    private Double kwhRaT2;

    private Double kwhRaPtctT2;

    private Double kwhRaT3;

    private Double kwhRaPtctT3;

    private Double kwhRaT4;

    private Double kwhRaPtctT4;

    private Double kwhRrT1;

    private Double kwhRrPtctT1;

    private Double kwhRrT2;

    private Double kwhRrPtctT2;

    private Double kwhRrT3;

    private Double kwhRrPtctT3;

    private Double kwhRrT4;

    private Double kwhRrPtctT4;
    
    private String veeResultDetail;

    private String veeEstmtDetail;

    private String veeEditDetail;
    
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDataPeriod() {
        return dataPeriod;
    }

    public void setDataPeriod(String dataPeriod) {
        this.dataPeriod = dataPeriod == null ? null : dataPeriod.trim();
    }

    public String getFzDataType() {
        return fzDataType;
    }

    public void setFzDataType(String fzDataType) {
        this.fzDataType = fzDataType == null ? null : fzDataType.trim();
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
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

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType == null ? null : readType.trim();
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
    }

    public Short getScTimes() {
        return scTimes;
    }

    public void setScTimes(Short scTimes) {
        this.scTimes = scTimes;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode == null ? null : cmdCode.trim();
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus == null ? null : opStatus.trim();
    }

    public Double getValueFreezing() {
        return valueFreezing;
    }

    public void setValueFreezing(Double valueFreezing) {
        this.valueFreezing = valueFreezing;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getPtct() {
        return ptct;
    }

    public void setPtct(String ptct) {
        this.ptct = ptct == null ? null : ptct.trim();
    }

    public Double getPtctValueFreezing() {
        return ptctValueFreezing;
    }

    public void setPtctValueFreezing(Double ptctValueFreezing) {
        this.ptctValueFreezing = ptctValueFreezing;
    }

    public Double getValueFreezingT1() {
        return valueFreezingT1;
    }

    public void setValueFreezingT1(Double valueFreezingT1) {
        this.valueFreezingT1 = valueFreezingT1;
    }

    public Double getPtctValueFreezingT1() {
        return ptctValueFreezingT1;
    }

    public void setPtctValueFreezingT1(Double ptctValueFreezingT1) {
        this.ptctValueFreezingT1 = ptctValueFreezingT1;
    }

    public Double getValueFreezingT2() {
        return valueFreezingT2;
    }

    public void setValueFreezingT2(Double valueFreezingT2) {
        this.valueFreezingT2 = valueFreezingT2;
    }

    public Double getPtctValueFreezingT2() {
        return ptctValueFreezingT2;
    }

    public void setPtctValueFreezingT2(Double ptctValueFreezingT2) {
        this.ptctValueFreezingT2 = ptctValueFreezingT2;
    }

    public Double getValueFreezingT3() {
        return valueFreezingT3;
    }

    public void setValueFreezingT3(Double valueFreezingT3) {
        this.valueFreezingT3 = valueFreezingT3;
    }

    public Double getPtctValueFreezingT3() {
        return ptctValueFreezingT3;
    }

    public void setPtctValueFreezingT3(Double ptctValueFreezingT3) {
        this.ptctValueFreezingT3 = ptctValueFreezingT3;
    }

    public Double getValueFreezingT4() {
        return valueFreezingT4;
    }

    public void setValueFreezingT4(Double valueFreezingT4) {
        this.valueFreezingT4 = valueFreezingT4;
    }

    public Double getPtctValueFreezingT4() {
        return ptctValueFreezingT4;
    }

    public void setPtctValueFreezingT4(Double ptctValueFreezingT4) {
        this.ptctValueFreezingT4 = ptctValueFreezingT4;
    }

    public Double getValueFreezingT5() {
        return valueFreezingT5;
    }

    public void setValueFreezingT5(Double valueFreezingT5) {
        this.valueFreezingT5 = valueFreezingT5;
    }

    public Double getPtctValueFreezingT5() {
        return ptctValueFreezingT5;
    }

    public void setPtctValueFreezingT5(Double ptctValueFreezingT5) {
        this.ptctValueFreezingT5 = ptctValueFreezingT5;
    }

    public Double getValueFreezingT6() {
        return valueFreezingT6;
    }

    public void setValueFreezingT6(Double valueFreezingT6) {
        this.valueFreezingT6 = valueFreezingT6;
    }

    public Double getPtctValueFreezingT6() {
        return ptctValueFreezingT6;
    }

    public void setPtctValueFreezingT6(Double ptctValueFreezingT6) {
        this.ptctValueFreezingT6 = ptctValueFreezingT6;
    }

    public Double getValueFreezingT7() {
        return valueFreezingT7;
    }

    public void setValueFreezingT7(Double valueFreezingT7) {
        this.valueFreezingT7 = valueFreezingT7;
    }

    public Double getPtctValueFreezingT7() {
        return ptctValueFreezingT7;
    }

    public void setPtctValueFreezingT7(Double ptctValueFreezingT7) {
        this.ptctValueFreezingT7 = ptctValueFreezingT7;
    }

    public Double getValueFreezingT8() {
        return valueFreezingT8;
    }

    public void setValueFreezingT8(Double valueFreezingT8) {
        this.valueFreezingT8 = valueFreezingT8;
    }

    public Double getPtctValueFreezingT8() {
        return ptctValueFreezingT8;
    }

    public void setPtctValueFreezingT8(Double ptctValueFreezingT8) {
        this.ptctValueFreezingT8 = ptctValueFreezingT8;
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

    public Double getKwhRa() {
        return kwhRa;
    }

    public void setKwhRa(Double kwhRa) {
        this.kwhRa = kwhRa;
    }

    public Double getKwhRaPtct() {
        return kwhRaPtct;
    }

    public void setKwhRaPtct(Double kwhRaPtct) {
        this.kwhRaPtct = kwhRaPtct;
    }

    public Double getKwhRr() {
        return kwhRr;
    }

    public void setKwhRr(Double kwhRr) {
        this.kwhRr = kwhRr;
    }

    public Double getKwhRrPtct() {
        return kwhRrPtct;
    }

    public void setKwhRrPtct(Double kwhRrPtct) {
        this.kwhRrPtct = kwhRrPtct;
    }

    public String getAppRcvStatus() {
        return appRcvStatus;
    }

    public void setAppRcvStatus(String appRcvStatus) {
        this.appRcvStatus = appRcvStatus == null ? null : appRcvStatus.trim();
    }

    public String getAppReceiver() {
        return appReceiver;
    }

    public void setAppReceiver(String appReceiver) {
        this.appReceiver = appReceiver == null ? null : appReceiver.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public BigDecimal getCompensateRate() {
        return compensateRate;
    }

    public void setCompensateRate(BigDecimal compensateRate) {
        this.compensateRate = compensateRate;
    }

    public BigDecimal getMaxDemand() {
        return maxDemand;
    }

    public void setMaxDemand(BigDecimal maxDemand) {
        this.maxDemand = maxDemand;
    }

    public Date getMaxDemandTime() {
        return maxDemandTime;
    }

    public void setMaxDemandTime(Date maxDemandTime) {
        this.maxDemandTime = maxDemandTime;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator == null ? null : coperator.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Double getKwhPaT1() {
        return kwhPaT1;
    }

    public void setKwhPaT1(Double kwhPaT1) {
        this.kwhPaT1 = kwhPaT1;
    }

    public Double getKwhPaPtctT1() {
        return kwhPaPtctT1;
    }

    public void setKwhPaPtctT1(Double kwhPaPtctT1) {
        this.kwhPaPtctT1 = kwhPaPtctT1;
    }

    public Double getKwhPaT2() {
        return kwhPaT2;
    }

    public void setKwhPaT2(Double kwhPaT2) {
        this.kwhPaT2 = kwhPaT2;
    }

    public Double getKwhPaPtctT2() {
        return kwhPaPtctT2;
    }

    public void setKwhPaPtctT2(Double kwhPaPtctT2) {
        this.kwhPaPtctT2 = kwhPaPtctT2;
    }

    public Double getKwhPaT3() {
        return kwhPaT3;
    }

    public void setKwhPaT3(Double kwhPaT3) {
        this.kwhPaT3 = kwhPaT3;
    }

    public Double getKwhPaPtctT3() {
        return kwhPaPtctT3;
    }

    public void setKwhPaPtctT3(Double kwhPaPtctT3) {
        this.kwhPaPtctT3 = kwhPaPtctT3;
    }

    public Double getKwhPaT4() {
        return kwhPaT4;
    }

    public void setKwhPaT4(Double kwhPaT4) {
        this.kwhPaT4 = kwhPaT4;
    }

    public Double getKwhPaPtctT4() {
        return kwhPaPtctT4;
    }

    public void setKwhPaPtctT4(Double kwhPaPtctT4) {
        this.kwhPaPtctT4 = kwhPaPtctT4;
    }

    public Double getKwhPrT1() {
        return kwhPrT1;
    }

    public void setKwhPrT1(Double kwhPrT1) {
        this.kwhPrT1 = kwhPrT1;
    }

    public Double getKwhPrPtctT1() {
        return kwhPrPtctT1;
    }

    public void setKwhPrPtctT1(Double kwhPrPtctT1) {
        this.kwhPrPtctT1 = kwhPrPtctT1;
    }

    public Double getKwhPrT2() {
        return kwhPrT2;
    }

    public void setKwhPrT2(Double kwhPrT2) {
        this.kwhPrT2 = kwhPrT2;
    }

    public Double getKwhPrPtctT2() {
        return kwhPrPtctT2;
    }

    public void setKwhPrPtctT2(Double kwhPrPtctT2) {
        this.kwhPrPtctT2 = kwhPrPtctT2;
    }

    public Double getKwhPrT3() {
        return kwhPrT3;
    }

    public void setKwhPrT3(Double kwhPrT3) {
        this.kwhPrT3 = kwhPrT3;
    }

    public Double getKwhPrPtctT3() {
        return kwhPrPtctT3;
    }

    public void setKwhPrPtctT3(Double kwhPrPtctT3) {
        this.kwhPrPtctT3 = kwhPrPtctT3;
    }

    public Double getKwhPrT4() {
        return kwhPrT4;
    }

    public void setKwhPrT4(Double kwhPrT4) {
        this.kwhPrT4 = kwhPrT4;
    }

    public Double getKwhPrPtctT4() {
        return kwhPrPtctT4;
    }

    public void setKwhPrPtctT4(Double kwhPrPtctT4) {
        this.kwhPrPtctT4 = kwhPrPtctT4;
    }

    public Double getKwhRaT1() {
        return kwhRaT1;
    }

    public void setKwhRaT1(Double kwhRaT1) {
        this.kwhRaT1 = kwhRaT1;
    }

    public Double getKwhRaPtctT1() {
        return kwhRaPtctT1;
    }

    public void setKwhRaPtctT1(Double kwhRaPtctT1) {
        this.kwhRaPtctT1 = kwhRaPtctT1;
    }

    public Double getKwhRaT2() {
        return kwhRaT2;
    }

    public void setKwhRaT2(Double kwhRaT2) {
        this.kwhRaT2 = kwhRaT2;
    }

    public Double getKwhRaPtctT2() {
        return kwhRaPtctT2;
    }

    public void setKwhRaPtctT2(Double kwhRaPtctT2) {
        this.kwhRaPtctT2 = kwhRaPtctT2;
    }

    public Double getKwhRaT3() {
        return kwhRaT3;
    }

    public void setKwhRaT3(Double kwhRaT3) {
        this.kwhRaT3 = kwhRaT3;
    }

    public Double getKwhRaPtctT3() {
        return kwhRaPtctT3;
    }

    public void setKwhRaPtctT3(Double kwhRaPtctT3) {
        this.kwhRaPtctT3 = kwhRaPtctT3;
    }

    public Double getKwhRaT4() {
        return kwhRaT4;
    }

    public void setKwhRaT4(Double kwhRaT4) {
        this.kwhRaT4 = kwhRaT4;
    }

    public Double getKwhRaPtctT4() {
        return kwhRaPtctT4;
    }

    public void setKwhRaPtctT4(Double kwhRaPtctT4) {
        this.kwhRaPtctT4 = kwhRaPtctT4;
    }

    public Double getKwhRrT1() {
        return kwhRrT1;
    }

    public void setKwhRrT1(Double kwhRrT1) {
        this.kwhRrT1 = kwhRrT1;
    }

    public Double getKwhRrPtctT1() {
        return kwhRrPtctT1;
    }

    public void setKwhRrPtctT1(Double kwhRrPtctT1) {
        this.kwhRrPtctT1 = kwhRrPtctT1;
    }

    public Double getKwhRrT2() {
        return kwhRrT2;
    }

    public void setKwhRrT2(Double kwhRrT2) {
        this.kwhRrT2 = kwhRrT2;
    }

    public Double getKwhRrPtctT2() {
        return kwhRrPtctT2;
    }

    public void setKwhRrPtctT2(Double kwhRrPtctT2) {
        this.kwhRrPtctT2 = kwhRrPtctT2;
    }

    public Double getKwhRrT3() {
        return kwhRrT3;
    }

    public void setKwhRrT3(Double kwhRrT3) {
        this.kwhRrT3 = kwhRrT3;
    }

    public Double getKwhRrPtctT3() {
        return kwhRrPtctT3;
    }

    public void setKwhRrPtctT3(Double kwhRrPtctT3) {
        this.kwhRrPtctT3 = kwhRrPtctT3;
    }

    public Double getKwhRrT4() {
        return kwhRrT4;
    }

    public void setKwhRrT4(Double kwhRrT4) {
        this.kwhRrT4 = kwhRrT4;
    }

    public Double getKwhRrPtctT4() {
        return kwhRrPtctT4;
    }

    public void setKwhRrPtctT4(Double kwhRrPtctT4) {
        this.kwhRrPtctT4 = kwhRrPtctT4;
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