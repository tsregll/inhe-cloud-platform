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
import com.inhe.mdm.model.MdmAmSubstation;
import com.inhe.mdm.model.VtMdmAmSubstation;
import com.inhe.mdm.service.IMdmAmSubstationService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/archives/structure/substation")
@CrossOrigin
public class MdmAmSubstationController {

	@Autowired
	IMdmAmSubstationService substationService;

	@GetMapping("/search-list-sub")
	public ResultInfo listPage(VtMdmAmSubstation param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(substationService.queryList(param, pageNo, pageSize));
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
	public ResultInfo insert(MdmAmSubstation param, HttpServletRequest request) {
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
			resultInfo = new ResultInfo(substationService.insert(param));
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
	public ResultInfo update(MdmAmSubstation param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String deptId = (String) request.getAttribute(JwtUtil.JWT_DEPT);
			if (param.getDeptId() == null || param.getDeptId().indexOf(deptId) != 0) {
				throw new InheExceptionBase(-101082, "超过当前操作员所在部门范围");
			}
			resultInfo = new ResultInfo(substationService.update(param));
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
	public ResultInfo detail(MdmAmSubstation param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(substationService.detail(param));
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
	public ResultInfo switchStatus(MdmAmSubstation param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(substationService.switchType(param));
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
	public ResultInfo updateLaLo(MdmAmSubstation param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(substationService.updateLaLo(param));
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
	public ResultInfo delete(MdmAmSubstation param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			param.setStatus("2");
			resultInfo = new ResultInfo(substationService.delete(param));
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
	public ResultInfo importStation(@RequestParam("fileName") MultipartFile file, MdmAmSubstation param,
			HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			String deptId = (String) request.getAttribute(JwtUtil.JWT_DEPT);
			if (param.getDeptId().indexOf(deptId) != 0) {
				throw new InheExceptionBase(-101082, "超过当前操作员所在部门范围");
			}
			param.setOrgId(orgId);
			param.setCoperator(userId);
			resultInfo = new ResultInfo(substationService.upload(file, param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, "导入失败");
		}

		return resultInfo;
	}
}
