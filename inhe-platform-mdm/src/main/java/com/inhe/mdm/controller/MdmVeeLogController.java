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
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.VtMdmVeeTaskData;
import com.inhe.mdm.service.IMdmVeeLogService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/vee/veelog")
@CrossOrigin
public class MdmVeeLogController {

	@Autowired
	IMdmVeeLogService mdmVeeLogService;

	@GetMapping("/search")
	public ResultInfo listPage(VtMdmVeeTaskData param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
			param.setOrgId(orgId);
			resultInfo = new ResultInfo(mdmVeeLogService.queryList(param, pageNo, pageSize));
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
    public ResultInfo update(MdmVeeTaskData param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeLogService.update(param));
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
    public ResultInfo updateLoad(MdmVeeTaskData param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeLogService.detail(param));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
    
    @GetMapping("/search-fail-detail")
    public ResultInfo searchFailDetail(MdmVeeTaskData param, int pageNo, int pageSize) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeLogService.searchFailDetail(param, pageNo, pageSize));
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
