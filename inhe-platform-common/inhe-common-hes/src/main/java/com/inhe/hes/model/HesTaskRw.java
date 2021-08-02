package com.inhe.hes.model;

import java.util.Date;

public class HesTaskRw {
	
    private String taskId;

    private String orgId;

    private String deptId;

    private String taskName;

    private String taskType;

    private String modelId;

    private String runMode;

    private Integer cycle;

    private String cycleType;

    private Integer repeatTimes;

    private Integer repeatCycle;

    private String repeatType;

    private Date baseTime;

    private Date nextTime;

    private String status;

    private String runId;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private String remarks;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
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

    public Date getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Date baseTime) {
        this.baseTime = baseTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}