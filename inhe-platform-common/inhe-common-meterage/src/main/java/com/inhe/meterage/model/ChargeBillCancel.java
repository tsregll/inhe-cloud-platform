package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeBillCancel {
    private String id;

    private String orgId;

    private String deptId;

    private String custId;

    private String contractNo;

    private Date cTime;

    private String cOperateType;

    private String cOperateCode;

    private String cTransCode;

    private Date cTransTime;

    private BigDecimal cAmount;

    private BigDecimal cStampTax;

    private String cReasonCode;

    private String cRemarks;

    private String status;

    private Date auditTime;

    private String auditor;

    private String auditNotes;

    private String coperator;

    private Date cdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getcOperateType() {
        return cOperateType;
    }

    public void setcOperateType(String cOperateType) {
        this.cOperateType = cOperateType;
    }

    public String getcOperateCode() {
        return cOperateCode;
    }

    public void setcOperateCode(String cOperateCode) {
        this.cOperateCode = cOperateCode;
    }

    public String getcTransCode() {
        return cTransCode;
    }

    public void setcTransCode(String cTransCode) {
        this.cTransCode = cTransCode;
    }

    public Date getcTransTime() {
        return cTransTime;
    }

    public void setcTransTime(Date cTransTime) {
        this.cTransTime = cTransTime;
    }

    public BigDecimal getcAmount() {
        return cAmount;
    }

    public void setcAmount(BigDecimal cAmount) {
        this.cAmount = cAmount;
    }

    public BigDecimal getcStampTax() {
        return cStampTax;
    }

    public void setcStampTax(BigDecimal cStampTax) {
        this.cStampTax = cStampTax;
    }

    public String getcReasonCode() {
        return cReasonCode;
    }

    public void setcReasonCode(String cReasonCode) {
        this.cReasonCode = cReasonCode;
    }

    public String getcRemarks() {
        return cRemarks;
    }

    public void setcRemarks(String cRemarks) {
        this.cRemarks = cRemarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditNotes() {
        return auditNotes;
    }

    public void setAuditNotes(String auditNotes) {
        this.auditNotes = auditNotes;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}