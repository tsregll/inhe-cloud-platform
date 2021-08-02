package com.inhe.mdm.service;

import java.util.Map;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.VtMdmAaLineLoss;

public interface IMdmAaLineLossService {
	
	public PageBean<Map<String, Object>> queryLineLossList(VtMdmAaLineLoss param, int pageNo, int pageSize);
	
}
