package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAmSubstationDao;
import com.inhe.mdm.service.IMdmDashboardService;


/**   
* Description :  mdm系统首页服务
* @author : xd
* @Date : 2021年3月18日  
*/ 
@Service
public class MdmDashboardServiceImpl implements IMdmDashboardService {
	@Autowired
	MdmAmSubstationDao substationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmDashboardServiceImpl.class);
	
	@Override
	public PageBean<Map<String, Object>> queryCountList(String orgId,String deptId){
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Map<String, Object> row = substationDao.selectDashboardCount(orgId,deptId);
		rows.add(row);
		logger.info("rows: - {}", rows);
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setData(rows);
		return pageBean;
	}

}
