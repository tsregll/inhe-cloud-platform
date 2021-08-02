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
import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.VtMdmAmTm;
import com.inhe.mdm.service.IMdmAmTmService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/archives/structure/transformer")
@CrossOrigin
public class MdmAmTmController {

	@Autowired
	IMdmAmTmService tmService;

	@GetMapping("/search-list-tm")
	public ResultInfo listPage(VtMdmAmTm param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(tmService.queryList(param, pageNo, pageSize));
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
	public ResultInfo insert(MdmAmTm param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);

			String deptId = (String) request.getAttribute(JwtUtil.JWT_DEPT);

			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setCoperator(userId);
			if (param.getDeptId() == null || param.getDeptId().indexOf(deptId) != 0) {
				throw new InheExceptionBase(-101082, "超过当前操作员所在部门范围");
			}
			resultInfo = new ResultInfo(tmService.insert(param));
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
	public ResultInfo update(MdmAmTm param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(tmService.update(param));
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
	public ResultInfo detail(VtMdmAmTm param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(tmService.detail(param));
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
	public ResultInfo switchStatus(MdmAmTm param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(tmService.switchType(param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@PostMapping("/update-lalo")
	public ResultInfo updateLaLo(MdmAmTm param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(tmService.updateLaLo(param));
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
	public ResultInfo delete(MdmAmTm param) {
		ResultInfo resultInfo = null;
		try {
			param.setStatus("2");
			resultInfo = new ResultInfo(tmService.delete(param));
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
	public ResultInfo importStation(@RequestParam("fileName") MultipartFile file, MdmAmTm param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setOrgId(orgId);
			param.setCoperator(userId);
			resultInfo = new ResultInfo(tmService.upload(file, param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, "导入失败");
		}

		return resultInfo;
	}
}
