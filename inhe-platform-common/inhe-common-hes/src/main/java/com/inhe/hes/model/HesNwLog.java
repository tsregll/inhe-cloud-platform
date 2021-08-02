package com.inhe.hes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HesNwLog {
	
	private String nwId;

	private Short execStep;

    private Date execTime;

    private String nwDetail;

    private String important;

    private String opResult;

    private String frameDetail;
    
    
    //add wdk 2020-08-06
    private Date nwStartTime;

    private Date nwEndTime;

    private BigDecimal nwprogress;

    private String nwResult;
    
    public String getNwId() {
        return nwId;
    }

    public void setNwId(String nwId) {
        this.nwId = nwId;
    }

    public Short getExecStep() {
        return execStep;
    }

    public void setExecStep(Short execStep) {
        this.execStep = execStep;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getNwDetail() {
        return nwDetail;
    }

    public void setNwDetail(String nwDetail) {
        this.nwDetail = nwDetail;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getFrameDetail() {
        return frameDetail;
    }

    public void setFrameDetail(String frameDetail) {
        this.frameDetail = frameDetail;
    }

	public Date getNwStartTime() {
		return nwStartTime;
	}

	public void setNwStartTime(Date nwStartTime) {
		this.nwStartTime = nwStartTime;
	}

	public Date getNwEndTime() {
		return nwEndTime;
	}

	public void setNwEndTime(Date nwEndTime) {
		this.nwEndTime = nwEndTime;
	}

	public BigDecimal getNwprogress() {
		return nwprogress;
	}

	public void setNwprogress(BigDecimal nwprogress) {
		this.nwprogress = nwprogress;
	}

	public String getNwResult() {
		return nwResult;
	}

	public void setNwResult(String nwResult) {
		this.nwResult = nwResult;
	}


    
    
    
}