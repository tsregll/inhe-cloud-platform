package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.service.IMdmDashboardService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/dashboard/index1")
@CrossOrigin
public class MdmDashboardController {
	@Autowired
	IMdmDashboardService mdmDashboardService;
	
	@Autowired
	HttpServletRequest request;

	@GetMapping("/search")
	public ResultInfo listPage() {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			String deptId = (String) request.getAttribute(JwtUtil.JWT_DEPT);
			resultInfo = new ResultInfo(mdmDashboardService.queryCountList(orgId,deptId));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
}