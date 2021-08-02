package com.inhe.mdm.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAmSubstationMt;
import com.inhe.mdm.model.VtMdmAmSubstationMt;

public interface IMdmAmSubstationMtService {
	public PageBean<VtMdmAmSubstationMt> queryList(VtMdmAmSubstationMt param, int pageNo, int pageSize);

	public boolean insert(MdmAmSubstationMt mdmAmSubstationMt);

	public boolean update(MdmAmSubstationMt mdmAmSubstationMt);

	public VtMdmAmSubstationMt detail(VtMdmAmSubstationMt mdmAmSubstationMt);

	public Boolean delete(MdmAmSubstationMt mdmAmSubstationMt);

	public boolean switchType(MdmAmSubstationMt mdmAmSubstationMt);

	public JSONObject upload(MultipartFile file, MdmAmSubstationMt mdmAmSubstationMt) throws Exception;
}
