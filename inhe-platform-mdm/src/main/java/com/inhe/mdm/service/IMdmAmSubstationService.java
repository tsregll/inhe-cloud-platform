package com.inhe.mdm.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAmSubstation;
import com.inhe.mdm.model.VtMdmAmSubstation;


public interface IMdmAmSubstationService {
    
    public PageBean<VtMdmAmSubstation> queryList(VtMdmAmSubstation param, int pageNo, int pageSize);
    
    public boolean insert(MdmAmSubstation mdmAmSubstation);
    
    public boolean update(MdmAmSubstation mdmAmSubstation);
    
    public MdmAmSubstation detail(MdmAmSubstation mdmAmSubstation);
    
    public Boolean delete(MdmAmSubstation mdmAmSubstation);
    
    public boolean switchType(MdmAmSubstation mdmAmSubstation);
    
    public boolean updateLaLo(MdmAmSubstation mdmAmSubstation);
    
    public JSONObject upload(MultipartFile file,MdmAmSubstation mdmAmSubstation) throws Exception;
}
