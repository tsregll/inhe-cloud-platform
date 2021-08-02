package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesDevice {
    private String devId;

    private String devName;

    private String devType;

    private String orgId;

    private String deptId;

    private Integer mPoint;

    private Integer mPointRead;

    private String mPointCompare;
    
    private String nwParams;

    private Integer nwStatus;

    private String ratio;

    private String prodId;

    private String prodType;

    private String commType;

    private String commAddr;

    private String commPassword;

    private String parentId;
    
    private String parentName;

    private String taskId;

    private BigDecimal la;

    private BigDecimal lo;

    private String address;

    private String status;

    private String online;

    private String ip;

    private Integer port;

    private String remarks;

    private String coperator;

    private Date cdate;

    private String uoperator;

    //add vt
    private String moduleType; 
    
    // 产品名称
    private String prodName;
    
    // 自定义任务关联表状态
    private String rwItemsStatus;
    
    // 自定义任务关联表的关联标识
    private String refId;
    
    // 基频
    private String frequency;

    // 信道
    private String channel;
    
    // 网络标识
    private String netId;
    
    // 0广播
    private String radio;
    
    // 可用信道
    private String allChannel;
    
    //广播信道
    private String radioChannel;
    
    // RF类型
    private String rfType;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
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

    public Integer getmPoint() {
        return mPoint;
    }

    public void setmPoint(Integer mPoint) {
        this.mPoint = mPoint;
    }

    public Integer getmPointRead() {
        return mPointRead;
    }

    public void setmPointRead(Integer mPointRead) {
        this.mPointRead = mPointRead;
    }

    public String getmPointCompare() {
        return mPointCompare;
    }

    public void setmPointCompare(String mPointCompare) {
        this.mPointCompare = mPointCompare;
    }

    public String getNwParams() {
		return nwParams;
	}

	public void setNwParams(String nwParams) {
		this.nwParams = nwParams;
	}

	public Integer getNwStatus() {
        return nwStatus;
    }

    public void setNwStatus(Integer nwStatus) {
        this.nwStatus = nwStatus;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }

    public String getCommAddr() {
        return commAddr;
    }

    public void setCommAddr(String commAddr) {
        this.commAddr = commAddr;
    }

    public String getCommPassword() {
        return commPassword;
    }

    public void setCommPassword(String commPassword) {
        this.commPassword = commPassword;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getLa() {
        return la;
    }

    public void setLa(BigDecimal la) {
        this.la = la;
    }

    public BigDecimal getLo() {
        return lo;
    }

    public void setLo(BigDecimal lo) {
        this.lo = lo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getRwItemsStatus() {
		return rwItemsStatus;
	}

	public void setRwItemsStatus(String rwItemsStatus) {
		this.rwItemsStatus = rwItemsStatus;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getAllChannel() {
		return allChannel;
	}

	public void setAllChannel(String allChannel) {
		this.allChannel = allChannel;
	}

	public String getRadioChannel() {
		return radioChannel;
	}

	public void setRadioChannel(String radioChannel) {
		this.radioChannel = radioChannel;
	}

	public String getRfType() {
		return rfType;
	}

	public void setRfType(String rfType) {
		this.rfType = rfType;
	}
	
}