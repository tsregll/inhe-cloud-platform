package com.inhe.mdm.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaStealDetail;
import com.inhe.mdm.model.VtMdmAaStealDetail;

public interface IMdmAaStealDetailService {
	 public PageBean<VtMdmAaStealDetail> queryListByDeviceNum(VtMdmAaStealDetail param, int pageNo, int pageSize);
	 
	 public PageBean<VtMdmAaStealDetail> queryListByEvent(VtMdmAaStealDetail param, int pageNo, int pageSize);
	 
	 public PageBean<VtMdmAaStealDetail> queryRuleDetailList(VtMdmAaStealDetail param, int pageNo, int pageSize);
	 
	 public boolean update(MdmAaStealDetail param);
	 
	 public PageBean<Map<String, Object>> queryKwhList(VtMdmAaStealDetail param, int pageNo, int pageSize);
	 
	 public boolean exportStealDataByDeviceNum( VtMdmAaStealDetail param, String isoCode, HttpServletResponse response) throws Exception;
	 
	 public boolean exportStealDataByEvent( VtMdmAaStealDetail param, String isoCode, HttpServletResponse response) throws Exception;
}
