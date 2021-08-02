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
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.service.IMdmAmDeviceService;
import com.inhe.admin.model.SysDevice;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/archives/mainnetwork")
@CrossOrigin
public class MdmMainNetworkController {

	@Autowired
	IMdmAmDeviceService deviceService;

	@GetMapping("/search-list-main")
	public ResultInfo listPage(VtMdmAmDevice param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			param.setMeterType("0");
			resultInfo = new ResultInfo(deviceService.queryList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@GetMapping("/search-list-dev")
	public ResultInfo searchDevice(SysDevice param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			String deptId = (String) request.getAttribute(JwtUtil.JWT_DEPT);
			if(param.getDeptId()==null || param.getDeptId().indexOf(deptId) != 0) {
				param.setDeptId(deptId);
			}
			resultInfo = new ResultInfo(deviceService.publicSearchDeviceList(param, pageNo, pageSize));
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
	public ResultInfo insert(MdmAmDevice param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setCoperator(userId);
			param.setMeterType("0");
			resultInfo = new ResultInfo(deviceService.insert(param));
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
	public ResultInfo update(MdmAmDevice param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(deviceService.update(param));
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
	public ResultInfo detail(VtMdmAmDevice param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(deviceService.detail(param));
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
	public ResultInfo switchStatus(MdmAmDevice param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(deviceService.switchType(param));
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
	public ResultInfo delete(MdmAmDevice param) {
		ResultInfo resultInfo = null;
		try {
			param.setStatus("2");
			resultInfo = new ResultInfo(deviceService.delete(param));
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
	public ResultInfo importStation(@RequestParam("fileName") MultipartFile file,MdmAmDevice param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			param.setOrgId(orgId);
			param.setCoperator(userId);
			param.setMeterType("0");
			resultInfo = new ResultInfo(deviceService.upload(file, param));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, "导入失败");
		}

		return resultInfo;
	}
}