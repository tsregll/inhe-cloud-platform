package com.inhe.mdm.service;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.VtMdmAaPowerOffDetail;

public interface IMdmBlackoutEventService {
	
	public PageBean<VtMdmAaPowerOffDetail> queryList(VtMdmAaPowerOffDetail param, int pageNo, int pageSize);
	
	public boolean exportBlackoutEventData(VtMdmAaPowerOffDetail param, String isoCode, HttpServletResponse response) throws Exception;
}
