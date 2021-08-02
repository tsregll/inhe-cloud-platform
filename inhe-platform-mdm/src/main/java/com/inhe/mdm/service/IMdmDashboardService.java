package com.inhe.mdm.service;

import java.util.Map;

import com.inhe.build.result.PageBean;

public interface IMdmDashboardService {
	
	public PageBean<Map<String, Object>> queryCountList(String orgId,String deptId);

}
