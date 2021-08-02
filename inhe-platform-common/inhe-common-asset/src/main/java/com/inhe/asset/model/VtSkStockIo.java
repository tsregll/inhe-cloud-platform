package com.inhe.asset.model;

import java.util.Date;
import java.util.List;

public class VtSkStockIo extends SkStockIo {
	
    private List<VtSkStockIoItems> ioItems;
    
    private Date startTime;
    
    private Date endTime;
    
    private String type;

	public List<VtSkStockIoItems> getIoItems() {
		return ioItems;
	}

	public void setIoItems(List<VtSkStockIoItems> ioItems) {
		this.ioItems = ioItems;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}