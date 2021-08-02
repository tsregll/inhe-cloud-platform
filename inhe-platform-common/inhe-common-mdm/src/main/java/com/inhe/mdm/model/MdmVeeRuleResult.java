/**
 * @author pfr 2020-07-29
 */
package com.inhe.mdm.model;

public class MdmVeeRuleResult {
	
	private String id;
	private Integer result;
	private String remarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public MdmVeeRuleResult(String id, Integer result, String remarks) {
		super();
		this.id = id;
		this.result = result;
		this.remarks = remarks;
	}

}
