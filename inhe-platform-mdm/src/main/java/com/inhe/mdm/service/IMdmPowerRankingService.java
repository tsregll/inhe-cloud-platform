package com.inhe.mdm.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaKwh;
import com.inhe.mdm.model.VtMdmAaKwhDetail;

public interface IMdmPowerRankingService {
	public PageBean<MdmAaKwh> queryListByLine(MdmAaKwh param, int pageNo, int pageSize);
	
	public PageBean<Map<String, Object>> queryListByMeter(VtMdmAaKwhDetail param, int pageNo, int pageSize);

	public boolean exportPowerDataByLine(MdmAaKwh mdmAaKwh, String isoCode, HttpServletResponse response) throws Exception;
	
	public boolean exportPowerDataByMeter(VtMdmAaKwhDetail param, String isoCode, HttpServletResponse response) throws Exception;
}
