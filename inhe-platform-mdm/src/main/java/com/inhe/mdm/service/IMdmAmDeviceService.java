package com.inhe.mdm.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.admin.model.SysDevice;

public interface IMdmAmDeviceService {
	public PageBean<VtMdmAmDevice> queryList(VtMdmAmDevice param, int pageNo, int pageSize);
	
	public PageBean<SysDevice> publicSearchDeviceList(SysDevice param, int pageNo, int pageSize) throws Exception;

	public boolean insert(MdmAmDevice mdmAmDevice) throws Exception;

	public boolean update(MdmAmDevice mdmAmDevice);

	public VtMdmAmDevice detail(VtMdmAmDevice mdmAmDevice);

	public Boolean delete(MdmAmDevice mdmAmDevice) throws Exception;

	public boolean switchType(MdmAmDevice mdmAmDevice) throws Exception;

	public JSONObject upload(MultipartFile file, MdmAmDevice mdmAmDevice) throws Exception;
}
