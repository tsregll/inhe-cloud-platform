package com.inhe.mdm.service;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaStealRule;

public interface IMdmAaStealRuleService {
	
	 public PageBean<MdmAaStealRule> queryList(MdmAaStealRule param, int pageNo, int pageSize);
	 
	 public boolean update(MdmAaStealRule param);
}
