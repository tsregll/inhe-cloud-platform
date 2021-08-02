package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.VtMdmAaLineLoss;
import com.inhe.mdm.service.IMdmAaLineLossReportService;
import com.inhe.mdm.service.IMdmSysTaskPlanService;
import com.inhe.node.service.ISysTaskService;
import com.inhe.utils.JwtUtil;


/**   
* Description :  主网线损报表
* @author : xd
* @Date : 2021年1月4日  
*/ 
@RestController
@RequestMapping("/api/loss/report/main")
@CrossOrigin
public class MdmMainLineLossReportController {

	@Autowired
	IMdmAaLineLossReportService lineLossReportService;
	
	@Autowired
    IMdmSysTaskPlanService mdmSysTaskPlanService;
	
	@Autowired
	ISysTaskService sysTaskServiceImpl;

	@GetMapping("/search")
	public ResultInfo ModelListPage(VtMdmAaLineLoss param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(lineLossReportService.queryReportList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@GetMapping("/search-detail")
	public ResultInfo detailListPage(VtMdmAaLineLoss param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(lineLossReportService.querySupplyDetailList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@GetMapping("/search-chart")
	public ResultInfo lineLossChatListPage(VtMdmAaLineLoss param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(lineLossReportService.querylineLossChatList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@PostMapping("/update-reset")
	public ResultInfo reset(String taskCode, String dataTime) {
		ResultInfo resultInfo = null;
		try {
			String planCode = mdmSysTaskPlanService.selectPlanCodeByTaskCode(taskCode, dataTime);
			resultInfo = new ResultInfo(sysTaskServiceImpl.reset(planCode));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@GetMapping("/export")
	public void exportData(VtMdmAaLineLoss param, String isoCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			if ("4".equals(param.getWay()) ) {
				lineLossReportService.exportDataByMainLine(param, isoCode, response);
			} 
			if ("5".equals(param.getWay()) ) {
				lineLossReportService.exportDataByMainTransformer(param, isoCode, response);
			} 
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/export-chart")
	public void exportChartData(VtMdmAaLineLoss param, String isoCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			lineLossReportService.exportChartData(param, isoCode, response);
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
