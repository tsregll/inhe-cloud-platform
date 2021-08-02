package com.inhe.mdm.controller;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.VtMdmAaLineLoss;
import com.inhe.mdm.service.IMdmAaLineLossService;
import com.inhe.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**   
* Description :  线损分析
* @author : xd
* @Date : 2021年1月4日  
*/ 
@RestController
@RequestMapping("/api/loss/analysis")
@CrossOrigin
public class MdmAaLineLossController {

	@Autowired
	IMdmAaLineLossService LineLossService;

	@GetMapping("/search")
	public ResultInfo listModelListPage(VtMdmAaLineLoss param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(LineLossService.queryLineLossList(param, pageNo, pageSize));
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
