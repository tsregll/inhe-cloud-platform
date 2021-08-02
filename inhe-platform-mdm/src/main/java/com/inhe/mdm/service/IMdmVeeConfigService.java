package com.inhe.mdm.service;

import com.alibaba.fastjson.JSONArray;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmField;
import com.inhe.mdm.model.MdmVeeConfig;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.model.VtMdmField;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeFields;

public interface IMdmVeeConfigService {
    
    public PageBean<MdmVeeConfig> queryList(MdmVeeConfig param, int pageNo, int pageSize) throws Exception;

    public JSONArray getValRuleJsonByType(String type) throws Exception;
    
    public JSONArray getEstRuleJsonByType(String type) throws Exception;
    
    public boolean insert(MdmVeeConfig mdmVeeConfig);

    public boolean delete(String code);

    public boolean update(MdmVeeConfig param);
    
    public MdmVeeConfig detail(MdmVeeConfig param);

    public boolean switchActived(MdmVeeConfig param);

    public String searchCode();

    public PageBean<VtMdmVeeFields> searchListFields(VtMdmVeeFields param, int pageNo, int pageSize);

    public PageBean<VtMdmAmDevice> searchListDevice(VtMdmAmDevice param, int pageNo, int pageSize);

    public PageBean<MdmField> searchFieldsDetail(VtMdmField param, int pageNo, int pageSize);

    public PageBean<VtMdmRulesDetail> searchRulesDetail(VtMdmRulesDetail param, int pageNo, int pageSize);

}
