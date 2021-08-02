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
import com.inhe.mdm.model.MdmAaStealDetail;
import com.inhe.mdm.model.VtMdmAaStealDetail;
import com.inhe.mdm.service.IMdmAaStealDetailService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/steal/analysis")
@CrossOrigin
public class MdmAaStealDetailController {

	@Autowired
	IMdmAaStealDetailService stealDetailService;

	@GetMapping("/search")
	public ResultInfo listDeviceNumPage(VtMdmAaStealDetail param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			if ("1".equals(param.getType())) {
				resultInfo = new ResultInfo(stealDetailService.queryListByDeviceNum(param, pageNo, pageSize));
			} else {
				resultInfo = new ResultInfo(stealDetailService.queryListByEvent(param, pageNo, pageSize));
			}

		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@GetMapping("/search-event")
	public ResultInfo listEventPage(VtMdmAaStealDetail param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(stealDetailService.queryListByEvent(param, pageNo, pageSize));
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
	public ResultInfo listRuleDetailPage(VtMdmAaStealDetail param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(stealDetailService.queryRuleDetailList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@GetMapping("/search-kwh")
	public ResultInfo selectKwh(VtMdmAaStealDetail param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(stealDetailService.queryKwhList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@PostMapping("/update")
	public ResultInfo update(MdmAaStealDetail param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(stealDetailService.update(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	/**
	 * 导出数据
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/export")
	public void exportData(VtMdmAaStealDetail param, String isoCode, HttpServletRequest request,
			HttpServletResponse response) {
		String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
		param.setOrgId(orgId);
		try {
			if ("1".equals(param.getType())) {
				stealDetailService.exportStealDataByDeviceNum(param, isoCode, response);
			} else {
				stealDetailService.exportStealDataByEvent(param, isoCode, response);
			}
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
