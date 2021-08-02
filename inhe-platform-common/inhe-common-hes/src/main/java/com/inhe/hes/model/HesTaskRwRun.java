package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesTaskRwRun {
	
	private String taskId;

    private String runId;
	
    private Date runStart;

    private Date runEnd;

    private Integer runStatus;

    private Integer runDevice;

    private Integer runCmd;

    private Integer runCmdSuccess;

    private Integer runCmdFailed;

    private BigDecimal runPercent;

    public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}

	public Date getRunStart() {
        return runStart;
    }

    public void setRunStart(Date runStart) {
        this.runStart = runStart;
    }

    public Date getRunEnd() {
        return runEnd;
    }

    public void setRunEnd(Date runEnd) {
        this.runEnd = runEnd;
    }

    public Integer getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}

	public Integer getRunDevice() {
        return runDevice;
    }

    public void setRunDevice(Integer runDevice) {
        this.runDevice = runDevice;
    }

    public Integer getRunCmd() {
        return runCmd;
    }

    public void setRunCmd(Integer runCmd) {
        this.runCmd = runCmd;
    }

    public Integer getRunCmdSuccess() {
		return runCmdSuccess;
	}

	public void setRunCmdSuccess(Integer runCmdSuccess) {
		this.runCmdSuccess = runCmdSuccess;
	}

	public Integer getRunCmdFailed() {
		return runCmdFailed;
	}

	public void setRunCmdFailed(Integer runCmdFailed) {
		this.runCmdFailed = runCmdFailed;
	}

	public BigDecimal getRunPercent() {
        return runPercent;
    }

    public void setRunPercent(BigDecimal runPercent) {
        this.runPercent = runPercent;
    }
}