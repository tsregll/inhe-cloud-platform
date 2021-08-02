package com.inhe.asset.model;

import java.util.List;

/**
* @author 0785
* @version 创建时间：2020年12月31日 上午9:15:41
* 类说明：仓库盘点-》盘点信息
*/
public class VtInsetStockCheck  {
	
	private SkStockCheck skStockCheck;
	
	private List<SkStockCheckItems> checkItems;
	
	private String type;


	public SkStockCheck getSkStockCheck() {
		return skStockCheck;
	}

	public void setSkStockCheck(SkStockCheck skStockCheck) {
		this.skStockCheck = skStockCheck;
	}

	public List<SkStockCheckItems> getCheckItems() {
		return checkItems;
	}

	public void setCheckItems(List<SkStockCheckItems> checkItems) {
		this.checkItems = checkItems;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}