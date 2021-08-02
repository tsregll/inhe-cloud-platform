package com.inhe.mdm.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaLineLoss;
import com.inhe.mdm.model.VtMdmAaLineLoss;

public interface IMdmAaLineLossReportService {
	public PageBean<VtMdmAaLineLoss> queryReportList(VtMdmAaLineLoss param, int pageNo, int pageSize);
	
	public PageBean<Map<String, Object>> querySupplyDetailList(VtMdmAaLineLoss param, int pageNo, int pageSize);
	
	public PageBean<MdmAaLineLoss> querylineLossChatList(VtMdmAaLineLoss param, int pageNo, int pageSize);
	
	public boolean exportDataByDept(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportDataByVol(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportDataByLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportMonthDataByLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportDataByTm(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportMonthDataByTm(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportDataByMainLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportDataByMainTransformer(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportChartData(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception;

}
