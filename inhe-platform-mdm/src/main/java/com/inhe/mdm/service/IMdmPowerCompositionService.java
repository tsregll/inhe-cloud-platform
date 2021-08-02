package com.inhe.mdm.service;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaKwh;

public interface IMdmPowerCompositionService {
	
	public PageBean<MdmAaKwh> queryList(MdmAaKwh param, int pageNo, int pageSize);
	
	public boolean exportPowerData(MdmAaKwh param, String isoCode, HttpServletResponse response) throws Exception;

}
