package com.inhe.mdm.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.VtMdmAmLine;

public interface IMdmAmLineService {
	public PageBean<VtMdmAmLine> queryList(VtMdmAmLine param, int pageNo, int pageSize);

	public boolean insert(MdmAmLine mdmAmLine);

	public boolean update(MdmAmLine mdmAmLine);

	public VtMdmAmLine detail(VtMdmAmLine mdmAmLine);

	public Boolean delete(MdmAmLine mdmAmLine);

	public boolean switchType(MdmAmLine mdmAmLine);

	public JSONObject upload(MultipartFile file, MdmAmLine mdmAmLine) throws Exception;
}
