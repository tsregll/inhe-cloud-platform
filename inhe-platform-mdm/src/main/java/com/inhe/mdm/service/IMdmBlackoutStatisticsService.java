package com.inhe.mdm.service;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaPowerOff;
import com.inhe.mdm.model.VtMdmAaPowerOff;

public interface IMdmBlackoutStatisticsService {
	
	public PageBean<VtMdmAaPowerOff> queryList(MdmAaPowerOff param, int pageNo, int pageSize);
	
	public boolean exportBlackoutStatisticsDataByDept(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportBlackoutStatisticsDataByLine(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportBlackoutStatisticsDataByTm(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception;
}
