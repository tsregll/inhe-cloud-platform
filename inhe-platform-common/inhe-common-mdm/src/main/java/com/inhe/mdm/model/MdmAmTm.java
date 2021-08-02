package com.inhe.mdm.model;

import java.math.BigDecimal;
import java.util.Date;

public class MdmAmTm {
    private String id;

    private String tmCode;

    private String description;

    private String address;

    private String orgId;

    private String deptId;

    private String lineId;

    private BigDecimal capacity;

    private Double lo;

    private Double la;

    private Short zoom;

    private String loadType;

    private String status;

    private String coperator;

    private Date cdate;

    private Date udate;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTmCode() {
        return tmCode;
    }

    public void setTmCode(String tmCode) {
        this.tmCode = tmCode == null ? null : tmCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public Short getZoom() {
        return zoom;
    }

    public void setZoom(Short zoom) {
        this.zoom = zoom;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType == null ? null : loadType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}