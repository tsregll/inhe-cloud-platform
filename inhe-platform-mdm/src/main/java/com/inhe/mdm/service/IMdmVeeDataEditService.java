package com.inhe.mdm.service;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeTaskData;

public interface IMdmVeeDataEditService {
    
    public PageBean<VtMdmVeeTaskData> queryList(VtMdmVeeTaskData param, int pageNo, int pageSize);

    public boolean update(MdmVeeTaskData param);

    public MdmVeeTaskData detail(MdmVeeTaskData param);

    public PageBean<VtMdmRulesDetail> searchFailDetail(MdmVeeTaskData param, int pageNo, int pageSize);

}
