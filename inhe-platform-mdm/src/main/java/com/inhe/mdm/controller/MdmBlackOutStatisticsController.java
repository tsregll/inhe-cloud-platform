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
import com.inhe.mdm.model.MdmAaPowerOff;
import com.inhe.mdm.service.IMdmBlackoutStatisticsService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/analysis/blackout/statistics")
@CrossOrigin
/**   
* Description :  停电统计
* @author : xd
* @Date : 2021年3月29日  
*/ 
public class MdmBlackOutStatisticsController {
	
	@Autowired
	IMdmBlackoutStatisticsService blackoutStatisticsService;

	@GetMapping("/search")
	public ResultInfo listEventPage(MdmAaPowerOff param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(blackoutStatisticsService.queryList(param, pageNo, pageSize));
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
	public void exportData(MdmAaPowerOff param, String isoCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			if("0".equals(param.getWay())){
				blackoutStatisticsService.exportBlackoutStatisticsDataByDept(param, isoCode, response);
			}else if("1".equals(param.getWay())){
				blackoutStatisticsService.exportBlackoutStatisticsDataByLine(param, isoCode, response);
			}else {
				blackoutStatisticsService.exportBlackoutStatisticsDataByTm(param, isoCode, response);
			}
		} catch (InheExceptionBase e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
