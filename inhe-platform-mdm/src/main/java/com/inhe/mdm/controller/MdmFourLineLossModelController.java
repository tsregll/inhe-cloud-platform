package com.inhe.mdm.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.model.VtMdmAaLineLossModel;
import com.inhe.mdm.model.VtMdmAaLineLossModelDve;
import com.inhe.mdm.service.IMdmAaLineLossModelService;
import com.inhe.utils.JwtUtil;


/**   
* Description :  四分线损模型
* @author : xd
* @Date : 2021年1月4日  
*/ 
@RestController
@RequestMapping("/api/loss/model/four")
@CrossOrigin
public class MdmFourLineLossModelController {

	@Autowired
	IMdmAaLineLossModelService LineLossModelService;

	/**
	 * @param 0:分区;1:分压;2：分线;3：分台区
	 * @return
	 */
	@GetMapping("/search")
	public ResultInfo listModelListPage(VtMdmAaLineLossModel param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			if("0".equals(param.getSort())){
				resultInfo = new ResultInfo(LineLossModelService.queryPartitionModelList(param, pageNo, pageSize));
			}
			if("1".equals(param.getSort())){
				resultInfo = new ResultInfo(LineLossModelService.queryVoltageModelList(param, pageNo, pageSize));
			}
			if("2".equals(param.getSort())){
				resultInfo = new ResultInfo(LineLossModelService.queryLineModelList(param, pageNo, pageSize));
			}
			if("3".equals(param.getSort())){
				resultInfo = new ResultInfo(LineLossModelService.queryTmAreaModelList(param, pageNo, pageSize));
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
	
	@GetMapping("/search-meter-in")
	public ResultInfo listModelDevPage(MdmAaLineLossModelDve param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(LineLossModelService.queryModelDevList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@GetMapping("/search-meter")
	public ResultInfo listMeterPage(VtMdmAaLineLossModelDve param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(LineLossModelService.queryMeterList(param, pageNo, pageSize));
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
	public ResultInfo insert(@RequestBody VtMdmAaLineLossModel param, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String data = param.getData();
			List<VtMdmAaLineLossModel> list = JSONObject.parseArray(data,VtMdmAaLineLossModel.class);
			for(VtMdmAaLineLossModel mdmAaLineLossModel : list){
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			mdmAaLineLossModel.setOrgId(orgId);

			String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
			mdmAaLineLossModel.setCoperator(userId);
			}
			resultInfo = new ResultInfo(LineLossModelService.insert(list));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@PostMapping("/insert-device")
	public ResultInfo insertMeter(@RequestBody VtMdmAaLineLossModelDve param) {
		ResultInfo resultInfo = null;
		try {
			String data = param.getData();
			List<MdmAaLineLossModelDve> list = JSONObject.parseArray(data,MdmAaLineLossModelDve.class);
			resultInfo = new ResultInfo(LineLossModelService.insertMeter(list,param.getId()));
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
	public ResultInfo update(@RequestBody VtMdmAaLineLossModel param) {
		ResultInfo resultInfo = null;
		try {
			String data = param.getData();
			List<MdmAaLineLossModel> list = JSONObject.parseArray(data,MdmAaLineLossModel.class);
			resultInfo = new ResultInfo(LineLossModelService.update(list));
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
	public ResultInfo delete(VtMdmAaLineLossModel param) {
		ResultInfo resultInfo = null;
		try {
			String id= param.getId();
			List<String> list = Arrays.asList(id.split(","));
			resultInfo = new ResultInfo(LineLossModelService.delete(list));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}

		return resultInfo;
	}
	
	@DeleteMapping("/delete-device")
	public ResultInfo deleteMeter(VtMdmAaLineLossModelDve param) {
		ResultInfo resultInfo = null;
		try {
			resultInfo = new ResultInfo(LineLossModelService.deleteMeter(param));
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
