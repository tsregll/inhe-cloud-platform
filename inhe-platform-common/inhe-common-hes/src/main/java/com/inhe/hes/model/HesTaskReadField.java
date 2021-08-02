package com.inhe.hes.model;

import java.util.Date;

public class HesTaskReadField  {
	
    private String taskId;

    private String fieldId;

    private String fieldParams;

    private Date nextTime;

    private Integer cycle;

    private String cycleType;

    private Integer repeatTimes;

    private Integer repeatCycle;

    private String repeatType;

    private String status;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;

    private String remarks;
    
    //add
    private String obisAndName;
    
    //add
    private String plugin;
    
    private String rwType;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
    public String getFieldParams() {
        return fieldParams;
    }

    public void setFieldParams(String fieldParams) {
        this.fieldParams = fieldParams;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(Integer repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public Integer getRepeatCycle() {
        return repeatCycle;
    }

    public void setRepeatCycle(Integer repeatCycle) {
        this.repeatCycle = repeatCycle;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getObisAndName() {
		return obisAndName;
	}

	public void setObisAndName(String obisAndName) {
		this.obisAndName = obisAndName;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public String getRwType() {
		return rwType;
	}

	public void setRwType(String rwType) {
		this.rwType = rwType;
	}
	
    
}