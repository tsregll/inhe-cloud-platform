package com.inhe.mdm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.ResultInfo;
import com.inhe.mdm.model.MdmVeeConfig;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.model.VtMdmField;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeFields;
import com.inhe.mdm.service.IMdmVeeConfigService;
import com.inhe.utils.JwtUtil;

@RestController
@RequestMapping("/api/vee/config")
@CrossOrigin
public class MdmVeeConfigController {

	@Autowired
	IMdmVeeConfigService mdmVeeConfigService;

	@GetMapping("/search-list-config")
	public ResultInfo listPage(MdmVeeConfig param, int pageNo, int pageSize, HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
		    String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
            param.setOrgId(orgId);
			resultInfo = new ResultInfo(mdmVeeConfigService.queryList(param, pageNo, pageSize));
		} catch (InheExceptionBase e) {
			resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resultInfo = new ResultInfo(-10000, e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}

	@GetMapping("/search-list-vrule")
    public ResultInfo listValRule(String type) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.getValRuleJsonByType(type));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-list-erule")
    public ResultInfo listEstRule(String type) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.getEstRuleJsonByType(type));
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
    public ResultInfo insert(@RequestBody MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
            param.setOrgId(orgId);
            String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
            param.setCoperator(userId);
            resultInfo = new ResultInfo(mdmVeeConfigService.insert(param));
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
    public ResultInfo delete(MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.delete(param.getCode()));
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
    public ResultInfo update(@RequestBody MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
            param.setCoperator(userId);
            resultInfo = new ResultInfo(mdmVeeConfigService.update(param));
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
    public ResultInfo detailLoad(MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.detail(param));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/detail")
    public ResultInfo detail(MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.detail(param));
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
    public ResultInfo switchActived(MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            String userId = (String) request.getAttribute(JwtUtil.JWT_USERID);
            param.setCoperator(userId);
            resultInfo = new ResultInfo(mdmVeeConfigService.switchActived(param));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-code")
    public ResultInfo searchCode(MdmVeeConfig param, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.searchCode());
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-list-fields")
    public ResultInfo searchListFields(VtMdmVeeFields param, int pageNo, int pageSize) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.searchListFields(param, pageNo, pageSize));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-list-devices")
    public ResultInfo searchListDevice(VtMdmAmDevice param, int pageNo, int pageSize, HttpServletRequest request) {
        ResultInfo resultInfo = null;
        try {
            String orgId = (String) request.getAttribute(JwtUtil.JWT_ORG);
            param.setOrgId(orgId);
            resultInfo = new ResultInfo(mdmVeeConfigService.searchListDevice(param, pageNo, pageSize));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-fields-detail")
    public ResultInfo searchFieldsDetail(VtMdmField param, int pageNo, int pageSize) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.searchFieldsDetail(param, pageNo, pageSize));
        } catch (InheExceptionBase e) {
            resultInfo = new ResultInfo(e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo = new ResultInfo(-10000, e.getMessage());
            e.printStackTrace();
        }
        return resultInfo;
    }
	
	@GetMapping("/search-rules-detail")
    public ResultInfo searchRulesDetail(VtMdmRulesDetail param, int pageNo, int pageSize) {
        ResultInfo resultInfo = null;
        try {
            resultInfo = new ResultInfo(mdmVeeConfigService.searchRulesDetail(param, pageNo, pageSize));
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
