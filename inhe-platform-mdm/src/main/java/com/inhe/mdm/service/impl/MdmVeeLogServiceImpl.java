package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmVeeEstimateDao;
import com.inhe.mdm.dao.MdmVeeTaskDataDao;
import com.inhe.mdm.dao.MdmVeeValidationDao;
import com.inhe.mdm.model.MdmVeeEstimate;
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.MdmVeeValidation;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeTaskData;
import com.inhe.mdm.service.IMdmVeeLogService;

/**
 * @Description : vee校验规则日志服务
 * @author : guhf
 * @Date : 2020/12/16
 */
@Service
public class MdmVeeLogServiceImpl implements IMdmVeeLogService , ApplicationRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(MdmVeeLogServiceImpl.class);
    
    private final Map<String, MdmVeeValidation> mdmVeeValidationMap = new ConcurrentHashMap<String, MdmVeeValidation>();
    
    private final Map<String, MdmVeeEstimate> mdmVeeEstimateMap = new ConcurrentHashMap<String, MdmVeeEstimate>();
    
    @Autowired
    MdmVeeTaskDataDao mdmVeeTaskDataDao;
    
    @Autowired
    MdmVeeValidationDao mdmVeeValidationDao;
    
    @Autowired
    MdmVeeEstimateDao mdmVeeEstimateDao;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {       
        initValRules();
        initEstRules();
    }
    
    @Override
    public PageBean<VtMdmVeeTaskData> queryList(VtMdmVeeTaskData param, int pageNo, int pageSize) {
        int totalCount = mdmVeeTaskDataDao.selectCount(param);
        List<VtMdmVeeTaskData> rows = mdmVeeTaskDataDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmVeeTaskData> pageBean = new PageBean<VtMdmVeeTaskData>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    @Override
    public boolean update(MdmVeeTaskData mdmVeeTaskData) {
        JSONObject param = JSONObject.parseObject(mdmVeeTaskData.getId());
        mdmVeeTaskData.setDataType(param.getInteger("dataType"));
        mdmVeeTaskData.setId(param.getString("id"));
        mdmVeeTaskData.setStatus("1");
        mdmVeeTaskData.setOpResult("S");
        int result = mdmVeeTaskDataDao.updateByPrimaryKeySelective(mdmVeeTaskData);
        if (result <= 0) {
            throw new InheExceptionBase(-100053, "更新记录失败");
        }
        return true;
    }
    
    @Override
    public MdmVeeTaskData detail(MdmVeeTaskData mdmVeeTaskData) {
        MdmVeeTaskData result = mdmVeeTaskDataDao.selectByPrimaryKey(mdmVeeTaskData);
        if (result == null) {
            throw new InheExceptionBase(-101081, "找不到指定记录");
        }
        return result;
    }

    @Override
    public PageBean<VtMdmRulesDetail> searchFailDetail(MdmVeeTaskData param, int pageNo, int pageSize) {
        List<VtMdmRulesDetail> result = new ArrayList<VtMdmRulesDetail>();
        MdmVeeTaskData mdmVeeTaskData = detail(param);
        JSONArray detailArray = JSONArray.parseArray(mdmVeeTaskData.getVeeResultDetails());
        if(detailArray != null) {
            for (int i = 0; i < detailArray.size(); i++) {
                JSONObject item = (JSONObject) detailArray.get(i);
                VtMdmRulesDetail vtMdmRulesDetail = new VtMdmRulesDetail();
                String id = item.getString("id");
                vtMdmRulesDetail.setId(id);
                vtMdmRulesDetail.setRemarks(item.getString("remarks"));
                vtMdmRulesDetail.setResult(item.getString("result"));
                if(mdmVeeValidationMap.containsKey(id)) {
                    vtMdmRulesDetail.setName(mdmVeeValidationMap.get(id).getName());
                } else if(mdmVeeEstimateMap.containsKey(id)) {
                    vtMdmRulesDetail.setName(mdmVeeEstimateMap.get(id).getName());
                }
                result.add(vtMdmRulesDetail);
            }
        }
        int totalCount = result.size();
        int start = (pageNo - 1) * pageSize;
        int end = pageNo * pageSize < totalCount? (pageNo * pageSize):totalCount;
        List<VtMdmRulesDetail> rows = result.subList(start, end);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmRulesDetail> pageBean = new PageBean<VtMdmRulesDetail>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    private void initValRules() {
        MdmVeeValidation param = new MdmVeeValidation();
        param.setActived("Y");
        List<MdmVeeValidation> mdmVeeValidationList = mdmVeeValidationDao.selectAll(param);
        for(MdmVeeValidation item:mdmVeeValidationList) {
            mdmVeeValidationMap.put(item.getId(), item);
        }
    }
    
    private void initEstRules() {
        MdmVeeEstimate param = new MdmVeeEstimate();
        param.setActived("Y");
        List<MdmVeeEstimate> mdmVeeEstimateList = mdmVeeEstimateDao.selectAll(param);
        for(MdmVeeEstimate item:mdmVeeEstimateList) {
            mdmVeeEstimateMap.put(item.getId(), item);
        }
    }
    
}
