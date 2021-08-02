package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAaLineLossModel {
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
    
    private String data;//批量操作时传的参数
    
    private String volCode;//电压等级
    
    private String volDescription;//电压等级名称
    
    private String lineId;//线路id
    
    private String lineCode;//线路编号
    
    private String lineName;//线路名称
    
    private String tmId;//变压器id
    
    private String tmCode;//变压器编号
    
    private String tmName;//变压器名称
    
    private String stationMtId;//变电站主变id
    
    private String stationMtCode;//变电站主变编号
    
    private String stationMtName;//变电站主变名称
    
    private String stationCode;//变电站编号
    
    private String stationName;//变电站名称
    

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
	}

	public String getVolCode() {
		return volCode;
	}

	public void setVolCode(String volCode) {
		this.volCode = volCode == null ? null : volCode.trim();
	}

	public String getVolDescription() {
		return volDescription;
	}

	public void setVolDescription(String volDescription) {
		this.volDescription = volDescription == null ? null : volDescription.trim();
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId == null ? null : lineId.trim();
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode == null ? null : lineCode.trim();
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName == null ? null : lineName.trim();
	}

	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId == null ? null : tmId.trim();
	}

	public String getTmCode() {
		return tmCode;
	}

	public void setTmCode(String tmCode) {
		this.tmCode = tmCode == null ? null : tmCode.trim();
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName == null ? null : tmName.trim();
	}

	public String getStationMtId() {
		return stationMtId;
	}

	public void setStationMtId(String stationMtId) {
		this.stationMtId = stationMtId == null ? null : stationMtId.trim();
	}

	public String getStationMtCode() {
		return stationMtCode;
	}

	public void setStationMtCode(String stationMtCode) {
		this.stationMtCode = stationMtCode == null ? null : stationMtCode.trim();
	}

	public String getStationMtName() {
		return stationMtName;
	}

	public void setStationMtName(String stationMtName) {
		this.stationMtName = stationMtName == null ? null : stationMtName.trim();
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode == null ? null : stationCode.trim();
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName == null ? null : stationName.trim();
	}
    
    
}