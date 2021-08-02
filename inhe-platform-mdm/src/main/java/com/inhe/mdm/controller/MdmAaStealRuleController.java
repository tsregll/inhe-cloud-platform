package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.MdmAaStealRule;
import com.inhe.mdm.service.IMdmAaStealRuleService;

@RestController
@RequestMapping("/api/steal/theme")
@CrossOrigin
public class MdmAaStealRuleController {

	@Autowired
	IMdmAaStealRuleService stealRuleService;

	@GetMapping("/search")
	public ResultInfo listPage(MdmAaStealRule param, int pageNo, int pageSize) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(stealRuleService.queryList(param, pageNo, pageSize));
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
	public ResultInfo update(MdmAaStealRule param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(stealRuleService.update(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@PostMapping("/switch")
	public ResultInfo switchStatus(MdmAaStealRule param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(stealRuleService.update(param));
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
