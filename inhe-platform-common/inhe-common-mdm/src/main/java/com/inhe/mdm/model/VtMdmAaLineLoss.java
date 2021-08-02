package com.inhe.mdm.model;

import java.util.Date;

public class VtMdmAaLineLoss {
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
    
    private String tableType;//字典类型
    
    private String code;//字典上级编号
    
    private String description;//字典描述
    
    private String field;//线损区间拼接
    
    private String min;//线损区间最小值
    
    private String max;//线损区间最大值
    
    private String wholeYear;//计算年累计的年份
	
	private Double yearSupplyKwh;//年累计供电量(kWh)
	
	private Double yearSoldKwh;//年累计售电量(kWh)
	
	private Double yearLossKwh;//年累计损耗电量(kWh)
	
	private Double yearLossRate;//年累计损耗率(%)
	
	private String lineName;//线路名称
	
	private String lineCode;//线路编号
	
	private String tmName;//变压器名称
	
	private String substationName;//所属变电站名称
	    
	private String stationMtName;//变电站主变名称
	
	private String stationMtCode;//变电站主变编号
	
	private String supplyDire;//供电方向
	
	private String deviceNum;//设备号
	
	private String meterName;//计量点名称
	
	private String startTime;//开始日期
	    
	private String endTime;//结束日期

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

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min == null ? null : min.trim();
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max == null ? null : max.trim();
	}

	public String getWholeYear() {
		return wholeYear;
	}

	public void setWholeYear(String wholeYear) {
		this.wholeYear = wholeYear;
	}

	public Double getYearSupplyKwh() {
		return yearSupplyKwh;
	}

	public void setYearSupplyKwh(Double yearSupplyKwh) {
		this.yearSupplyKwh = yearSupplyKwh;
	}

	public Double getYearSoldKwh() {
		return yearSoldKwh;
	}

	public void setYearSoldKwh(Double yearSoldKwh) {
		this.yearSoldKwh = yearSoldKwh;
	}

	public Double getYearLossKwh() {
		return yearLossKwh;
	}

	public void setYearLossKwh(Double yearLossKwh) {
		this.yearLossKwh = yearLossKwh;
	}

	public Double getYearLossRate() {
		return yearLossRate;
	}

	public void setYearLossRate(Double yearLossRate) {
		this.yearLossRate = yearLossRate;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName == null ? null : lineName.trim();
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode  == null ? null : lineCode.trim();
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName == null ? null : tmName.trim();
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName == null ? null : substationName.trim();
	}

	public String getStationMtName() {
		return stationMtName;
	}

	public void setStationMtName(String stationMtName) {
		this.stationMtName = stationMtName == null ? null : stationMtName.trim();
	}

	public String getStationMtCode() {
		return stationMtCode;
	}

	public void setStationMtCode(String stationMtCode) {
		this.stationMtCode = stationMtCode  == null ? null : stationMtCode.trim();
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

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName == null ? null : meterName.trim();
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}
	
}