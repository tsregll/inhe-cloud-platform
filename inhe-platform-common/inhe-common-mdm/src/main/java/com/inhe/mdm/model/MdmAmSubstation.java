package com.inhe.mdm.model;

import java.util.Date;

public class MdmAmSubstation {
    private String id;

    private String description;

    private String address;

    private String stationCode;

    private String orgId;

    private String deptId;

    private String volLevel;

    private Double lo;

    private Double la;

    private Short zoom;

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

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
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

    public String getVolLevel() {
        return volLevel;
    }

    public void setVolLevel(String volLevel) {
        this.volLevel = volLevel == null ? null : volLevel.trim();
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