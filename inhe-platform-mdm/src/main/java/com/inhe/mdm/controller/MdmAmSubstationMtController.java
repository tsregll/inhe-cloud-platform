package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.MdmAmSubstationMt;
import com.inhe.mdm.model.VtMdmAmSubstationMt;
import com.inhe.mdm.service.IMdmAmSubstationMtService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/archives/structure/maintransformer")
@CrossOrigin
public class MdmAmSubstationMtController {

	@Autowired
	IMdmAmSubstationMtService substationMtService;

	@GetMapping("/search-list-main")
	public ResultInfo listPage(VtMdmAmSubstationMt param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(substationMtService.queryList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@PostMapping("/insert")
	public ResultInfo insert(MdmAmSubstationMt param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setCoperator(userId);
			resultInfo = new ResultInfo(substationMtService.insert(param));
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
	public ResultInfo update(MdmAmSubstationMt param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(substationMtService.update(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@GetMapping("/update-load")
	public ResultInfo detail(VtMdmAmSubstationMt param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(substationMtService.detail(param));
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
	public ResultInfo switchStatus(MdmAmSubstationMt param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(substationMtService.switchType(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@DeleteMapping("/delete")
	public ResultInfo delete(MdmAmSubstationMt param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			param.setStatus("2");
			resultInfo = new ResultInfo(substationMtService.delete(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}

		return resultInfo;
	}

	@PostMapping("/import")
	public ResultInfo importStation(@RequestParam("fileName") MultipartFile file,MdmAmSubstationMt param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setOrgId(orgId);
			param.setCoperator(userId);
			resultInfo = new ResultInfo(substationMtService.upload(file, param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, "导入失败");
		}

		return resultInfo;
	}
}
