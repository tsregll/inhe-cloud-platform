package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.VtMdmAaPowerOffDetail;
import com.inhe.mdm.service.IMdmBlackoutEventService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/analysis/blackout/event")
@CrossOrigin
/**   
* Description :  停电事件
* @author : xd
* @Date : 2021年3月26日  
*/ 
public class MdmBlackOutEventController {
	
	@Autowired
	IMdmBlackoutEventService blackoutEventService;

	@GetMapping("/search")
	public ResultInfo listEventPage(VtMdmAaPowerOffDetail param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(blackoutEventService.queryList(param, pageNo, pageSize));
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
	public void exportData(VtMdmAaPowerOffDetail param, String isoCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			blackoutEventService.exportBlackoutEventData(param, isoCode, response);
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
