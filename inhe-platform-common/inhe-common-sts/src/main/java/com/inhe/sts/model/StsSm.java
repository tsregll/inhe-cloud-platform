package com.inhe.sts.model;

import java.util.Date;
import java.util.List;

public class StsSm {
    private String id;

    private String orgId;
    
    private String smid;

    private String kind;

    private String type;

    private String description;

    private String version;

    private String slottd;

    private Integer trans;

    private Date transExpired;

    private String ip;

    private Integer port;

    private String status;

    private String loadRst;

    private String enabled;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    // add
    private String stsSmLoadRstCode;
    
    private String stsFilesCode;
    
    private String opStatus;
    
    private List<StsFiles> files;
    
    private List<StsSmKeys> keys;

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
    
    public String getSmid() {
		return smid;
	}

	public void setSmid(String smid) {
		this.smid = smid;
	}

	public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSlottd() {
        return slottd;
    }

    public void setSlottd(String slottd) {
        this.slottd = slottd;
    }

    public Integer getTrans() {
        return trans;
    }

    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    public Date getTransExpired() {
        return transExpired;
    }

    public void setTransExpired(Date transExpired) {
        this.transExpired = transExpired;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoadRst() {
        return loadRst;
    }

    public void setLoadRst(String loadRst) {
        this.loadRst = loadRst;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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

    public String getUoperator() {
        return uoperator;
    }

    public void setUoperator(String uoperator) {
        this.uoperator = uoperator;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

	public String getStsSmLoadRstCode() {
		return stsSmLoadRstCode;
	}

	public void setStsSmLoadRstCode(String stsSmLoadRstCode) {
		this.stsSmLoadRstCode = stsSmLoadRstCode;
	}

	public String getStsFilesCode() {
		return stsFilesCode;
	}

	public void setStsFilesCode(String stsFilesCode) {
		this.stsFilesCode = stsFilesCode;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}

	public List<StsFiles> getFiles() {
		return files;
	}

	public void setFiles(List<StsFiles> files) {
		this.files = files;
	}

	public List<StsSmKeys> getKeys() {
		return keys;
	}

	public void setKeys(List<StsSmKeys> keys) {
		this.keys = keys;
	}
	
}