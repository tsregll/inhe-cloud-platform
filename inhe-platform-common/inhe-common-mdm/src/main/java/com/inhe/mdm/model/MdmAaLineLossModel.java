package com.inhe.mdm.model;

import java.util.Date;

public class MdmAaLineLossModel {
    private String id;

    private String sort;

    private String orgId;

    private String refId;

    private String generationWay;

    private Short supplyInCount;

    private Short supplyOutCount;

    private String generationFlag;

    private Date generateTime;

    private Date deleteTime;

    private Double lossIndexDay;

    private Double lossIndexMonth;

    private String coperator;

    private Date cdate;

    private String deptId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getGenerationWay() {
        return generationWay;
    }

    public void setGenerationWay(String generationWay) {
        this.generationWay = generationWay == null ? null : generationWay.trim();
    }

    public Short getSupplyInCount() {
        return supplyInCount;
    }

    public void setSupplyInCount(Short supplyInCount) {
        this.supplyInCount = supplyInCount;
    }

    public Short getSupplyOutCount() {
        return supplyOutCount;
    }

    public void setSupplyOutCount(Short supplyOutCount) {
        this.supplyOutCount = supplyOutCount;
    }

    public String getGenerationFlag() {
        return generationFlag;
    }

    public void setGenerationFlag(String generationFlag) {
        this.generationFlag = generationFlag == null ? null : generationFlag.trim();
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Double getLossIndexDay() {
        return lossIndexDay;
    }

    public void setLossIndexDay(Double lossIndexDay) {
        this.lossIndexDay = lossIndexDay;
    }

    public Double getLossIndexMonth() {
        return lossIndexMonth;
    }

    public void setLossIndexMonth(Double lossIndexMonth) {
        this.lossIndexMonth = lossIndexMonth;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }
}