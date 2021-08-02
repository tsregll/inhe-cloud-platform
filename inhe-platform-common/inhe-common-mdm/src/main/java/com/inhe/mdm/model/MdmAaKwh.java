package com.inhe.mdm.model;

import java.util.Date;

public class MdmAaKwh {
    private String id;

    private String orgId;

    private String deptId;

    private String way;

    private String refId;

    private String dataType;

    private String nowTime;

    private String refContent;

    private Double totalKwhStart;

    private Double totalKwhEnd;

    private Double totalKwh;

    private Double priLinePriTmKwh;

    private Double pubLinePriTmKwh;

    private Double pubLinePubTmKwh;

    private Double yearBasis;

    private Double linkRelativeRatio;

    private Date cdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime == null ? null : nowTime.trim();
    }

    public String getRefContent() {
        return refContent;
    }

    public void setRefContent(String refContent) {
        this.refContent = refContent == null ? null : refContent.trim();
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

    public Double getPriLinePriTmKwh() {
        return priLinePriTmKwh;
    }

    public void setPriLinePriTmKwh(Double priLinePriTmKwh) {
        this.priLinePriTmKwh = priLinePriTmKwh;
    }

    public Double getPubLinePriTmKwh() {
        return pubLinePriTmKwh;
    }

    public void setPubLinePriTmKwh(Double pubLinePriTmKwh) {
        this.pubLinePriTmKwh = pubLinePriTmKwh;
    }

    public Double getPubLinePubTmKwh() {
        return pubLinePubTmKwh;
    }

    public void setPubLinePubTmKwh(Double pubLinePubTmKwh) {
        this.pubLinePubTmKwh = pubLinePubTmKwh;
    }

    public Double getYearBasis() {
        return yearBasis;
    }

    public void setYearBasis(Double yearBasis) {
        this.yearBasis = yearBasis;
    }

    public Double getLinkRelativeRatio() {
        return linkRelativeRatio;
    }

    public void setLinkRelativeRatio(Double linkRelativeRatio) {
        this.linkRelativeRatio = linkRelativeRatio;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}