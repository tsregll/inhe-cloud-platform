package com.inhe.mdm.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.VtMdmAmTm;

public interface IMdmAmTmService {
	public PageBean<VtMdmAmTm> queryList(VtMdmAmTm param, int pageNo, int pageSize);

	public boolean insert(MdmAmTm mdmAmTm);

	public boolean update(MdmAmTm mdmAmTm);

	public VtMdmAmTm detail(VtMdmAmTm mdmAmTm);

	public Boolean delete(MdmAmTm mdmAmTm);

	public boolean switchType(MdmAmTm mdmAmTm);
	
	public boolean updateLaLo(MdmAmTm mdmAmTm);

	public JSONObject upload(MultipartFile file, MdmAmTm mdmAmTm) throws Exception;
}
