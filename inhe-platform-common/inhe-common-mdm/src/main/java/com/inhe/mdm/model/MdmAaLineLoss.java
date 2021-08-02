package com.inhe.mdm.model;

import java.util.Date;

public class MdmAaLineLoss {
    private String id;

    private String orgId;

    private String deptId;

    private String way;

    private String refId;

    private String dataType;

    private String nowTime;

    private String refContent;

    private Double supplyKwh;

    private Double soldKwh;

    private Double lossKwh;

    private Double lossRate;

    private Double yearBasis;

    private Double linkRelativeRatio;

    private Double lineLoseIndex;

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

    public Double getSupplyKwh() {
        return supplyKwh;
    }

    public void setSupplyKwh(Double supplyKwh) {
        this.supplyKwh = supplyKwh;
    }

    public Double getSoldKwh() {
        return soldKwh;
    }

    public void setSoldKwh(Double soldKwh) {
        this.soldKwh = soldKwh;
    }

    public Double getLossKwh() {
        return lossKwh;
    }

    public void setLossKwh(Double lossKwh) {
        this.lossKwh = lossKwh;
    }

    public Double getLossRate() {
        return lossRate;
    }

    public void setLossRate(Double lossRate) {
        this.lossRate = lossRate;
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

    public Double getLineLoseIndex() {
        return lineLoseIndex;
    }

    public void setLineLoseIndex(Double lineLoseIndex) {
        this.lineLoseIndex = lineLoseIndex;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}