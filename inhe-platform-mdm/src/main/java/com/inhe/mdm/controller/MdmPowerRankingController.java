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
import com.inhe.mdm.model.MdmAaKwh;
import com.inhe.mdm.model.VtMdmAaKwhDetail;
import com.inhe.mdm.service.IMdmPowerRankingService;
import com.inhe.mdm.service.IMdmSysTaskPlanService;
import com.inhe.node.service.ISysTaskService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/analysis/consumption/rank")
@CrossOrigin
/**   
* Description :  电量排名分析
* @author : xd
* @Date : 2020年12月29日  
*/ 
public class MdmPowerRankingController {
	@Autowired
	IMdmPowerRankingService powerRankService;
	
	@Autowired
	IMdmSysTaskPlanService mdmSysTaskPlanService;
	
	@Autowired
	ISysTaskService sysTaskServiceImpl;

	@GetMapping("/search")
	public ResultInfo listEventPage(VtMdmAaKwhDetail param,MdmAaKwh mdmAaKwh, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			mdmAaKwh.setOrgId(orgId);
			if ("1".equals(param.getWay())) {
				resultInfo = new ResultInfo(powerRankService.queryListByLine(mdmAaKwh, pageNo, pageSize));
			} else {
				resultInfo = new ResultInfo(powerRankService.queryListByMeter(param, pageNo, pageSize));
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
	
	@PostMapping("/update-reset")
	public ResultInfo reset(String taskCode, String dataTime) {
		ResultInfo resultInfo = null;
		try {
			String[] taskCodes = taskCode.split(",");
			for(int i = 0; i<taskCodes.length; i++){
			String planCode = mdmSysTaskPlanService.selectPlanCodeByTaskCode(taskCodes[i], dataTime);
			resultInfo = new ResultInfo(sysTaskServiceImpl.reset(planCode));
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
	
	@GetMapping("/export")
	public void exportData(VtMdmAaKwhDetail param,MdmAaKwh mdmAaKwh, String isoCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			mdmAaKwh.setOrgId(orgId);
			if ("1".equals(param.getWay())) {
				powerRankService.exportPowerDataByLine(mdmAaKwh, isoCode, response);
			} else {
				powerRankService.exportPowerDataByMeter(param, isoCode, response);
			}
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
