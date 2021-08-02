package com.inhe.mdm.model;

public class VtMdmAaLineLossModelDve {
    private String id;

    private String deviceId;

    private String supplyDire;
    
    private String deviceNum;

    private String orgId;

    private String deptId;

    private String meteringDire;

    private String meterSort;

    private String meterName;

    private String volLevel;
    
    private String data;//选择计量点批量操作前端传参
    
    private Short supplyInCount;//供入计量点数量
    
    private Short supplyOutCount;//供出 计量点数量
    
    private int supNum;//MdmAaLineLossModelDve里面单个计量点的数量
    
    private String sort;//线损类别
    
    private String status;//计量点的状态
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getSupplyDire() {
        return supplyDire;
    }

    public void setSupplyDire(String supplyDire) {
        this.supplyDire = supplyDire == null ? null : supplyDire.trim();
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

    public String getMeteringDire() {
        return meteringDire;
    }

    public void setMeteringDire(String meteringDire) {
        this.meteringDire = meteringDire == null ? null : meteringDire.trim();
    }

    public String getMeterSort() {
        return meterSort;
    }

    public void setMeterSort(String meterSort) {
        this.meterSort = meterSort == null ? null : meterSort.trim();
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName == null ? null : meterName.trim();
    }

    public String getVolLevel() {
        return volLevel;
    }

    public void setVolLevel(String volLevel) {
        this.volLevel = volLevel == null ? null : volLevel.trim();
    }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
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

	public int getSupNum() {
		return supNum;
	}

	public void setSupNum(int supNum) {
		this.supNum = supNum;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
}