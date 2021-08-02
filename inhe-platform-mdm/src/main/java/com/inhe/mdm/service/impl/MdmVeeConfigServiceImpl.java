package com.inhe.mdm.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmFieldDao;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.dao.MdmVeeEstimateDao;
import com.inhe.mdm.dao.MdmVeeFieldsDao;
import com.inhe.mdm.dao.MdmVeeFieldsEstimateDao;
import com.inhe.mdm.dao.MdmVeeFieldsValidationDao;
import com.inhe.mdm.dao.MdmVeeValidationDao;
import com.inhe.mdm.model.MdmField;
import com.inhe.mdm.model.MdmVeeConfig;
import com.inhe.mdm.model.MdmVeeEstimate;
import com.inhe.mdm.model.MdmVeeValidation;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.model.VtMdmField;
import com.inhe.mdm.model.VtMdmRulesDetail;
import com.inhe.mdm.model.VtMdmVeeFields;
import com.inhe.mdm.service.IMdmVeeConfigService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.vee.RuleService;
import com.inhe.node.service.impl.SysUserServiceImpl;
import com.inhe.utils.DateUtil;

/**
 * @Description : vee校验规则配置服务
 * @author : guhf
 * @Date : 2020/12/16
 */
@Service
public class MdmVeeConfigServiceImpl implements IMdmVeeConfigService , ApplicationRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(MdmVeeConfigServiceImpl.class);
    
    private final Map<String, MdmVeeValidation> mdmVeeValidationMap = new ConcurrentHashMap<String, MdmVeeValidation>();
    
    private final Map<String, MdmVeeEstimate> mdmVeeEstimateMap = new ConcurrentHashMap<String, MdmVeeEstimate>();
    
    @Autowired
    MdmVeeConfigDao mdmVeeConfigDao;
    
    @Autowired
    MdmVeeFieldsValidationDao mdmVeeFieldsValidationDao;
    
    @Autowired
    MdmVeeFieldsEstimateDao mdmVeeFieldsEstimateDao;
    
    @Autowired
    MdmVeeValidationDao mdmVeeValidationDao;
    
    @Autowired
    MdmVeeEstimateDao mdmVeeEstimateDao;
    
    @Autowired
    MdmVeeFieldsDao mdmVeeFieldsDao;
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    
    @Autowired
    MdmFieldDao mdmFieldDao;
    
    @Autowired
    RuleService ruleService;
    
    @Autowired
    SysUserServiceImpl sysUserServiceImpl; 
    
    @Override
    public void run(ApplicationArguments args) throws Exception {       
        initValRules();
        initEstRules();
    }
    
    @Override
    public PageBean<MdmVeeConfig> queryList(MdmVeeConfig param, int pageNo, int pageSize) throws Exception {
        int totalCount = mdmVeeConfigDao.selectCount(param);
        List<MdmVeeConfig> rows = mdmVeeConfigDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
        for(MdmVeeConfig row : rows){
        	JSONObject detail = sysUserServiceImpl.detail(row.getCoperator());
        	String name = JSON.parseObject(detail.getString("detail")).getString("name");
        	row.setCoperator(name);
        }
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<MdmVeeConfig> pageBean = new PageBean<MdmVeeConfig>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    @Override
    public PageBean<VtMdmVeeFields> searchListFields(VtMdmVeeFields param, int pageNo, int pageSize) {
        int totalCount = mdmVeeFieldsDao.selectCount(param);
        List<VtMdmVeeFields> rows = mdmVeeFieldsDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmVeeFields> pageBean = new PageBean<VtMdmVeeFields>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    @Override
    public PageBean<VtMdmAmDevice> searchListDevice(VtMdmAmDevice param, int pageNo, int pageSize) {
        int totalCount = mdmAmDeviceDao.selectCount(param);
        List<VtMdmAmDevice> rows = mdmAmDeviceDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        // 封装分页数据
        PageBean<VtMdmAmDevice> pageBean = new PageBean<VtMdmAmDevice>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }
    
    @Override
    public PageBean<MdmField> searchFieldsDetail(VtMdmField param, int pageNo, int pageSize) {
        PageBean<MdmField> pageBean = new PageBean<MdmField>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        MdmVeeConfig mdmVeeConfig = mdmVeeConfigDao.selectByPrimaryKey(param.getCode());
        JSONArray details = JSON.parseArray(mdmVeeConfig.getDataTypeDetails());
        String dataType = param.getDataType();
        JSONArray fieldIds = new JSONArray();
        for (int i = 0; i < details.size(); i++) {
            JSONObject item =details.getJSONObject(i);
            if(dataType.equals(item.getString("data_type"))) {
                fieldIds = item.getJSONArray("fieldids");
                break;
            }
        }
        int totalCount = fieldIds.size();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < totalCount; i++) {
            builder.append("\'").append(fieldIds.getJSONObject(i).getString("id")).append("\'").append(",");
        }
        String fields = builder.toString();
        if(fields==null || fields.length() == 0) {
            pageBean.setTotalCount(0);
            pageBean.setTotalPage(0);
            pageBean.setData(null);
            return pageBean;
        }else if(fields.length() > 0) {
            fields=fields.substring(0, fields.length()-1);
            param.setFieldIds(fields);
            List<MdmField> rows = mdmFieldDao.searchFieldsDetail(param, (pageNo - 1) * pageSize, pageSize);
            logger.info("rows: - {}", rows);
            int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
            pageBean.setTotalCount(totalCount);
            pageBean.setTotalPage(totalPage);
            pageBean.setData(rows);
        }
        return pageBean;
    }
    
    @Override
    public PageBean<VtMdmRulesDetail> searchRulesDetail(VtMdmRulesDetail param, int pageNo, int pageSize) {
        PageBean<VtMdmRulesDetail> pageBean = new PageBean<VtMdmRulesDetail>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        List<VtMdmRulesDetail> rows = new ArrayList<VtMdmRulesDetail>();
        initValRules();
        initEstRules();
        
        MdmVeeConfig mdmVeeConfig = mdmVeeConfigDao.selectByPrimaryKey(param.getCode());
        JSONArray details = JSON.parseArray(mdmVeeConfig.getDataTypeDetails());
        String dataType = param.getDataType();
        String ruleType = param.getRuleType();
        JSONArray validationRules = new JSONArray();
        JSONArray estimateRules = new JSONArray();
        int totalCount = 0 ;
        for (int i = 0; i < details.size(); i++) {
            JSONObject item =details.getJSONObject(i);
            if(dataType.equals(item.getString("data_type"))) {
                if("1".equals(ruleType)) {
                    validationRules = item.getJSONArray("validation_rules");
                    if(validationRules == null) {
                        break;
                    }
                    totalCount = validationRules.size();
                    for (int j = 0; j < totalCount; j++) {
                        JSONObject idObject = (JSONObject) validationRules.get(j);
                        MdmVeeValidation mdmVeeValidation = mdmVeeValidationMap.get(idObject.getString("id"));
                        String name = mdmVeeValidation.getName();
                        String nameParam = param.getName();
                        if(StringUtils.isNotEmpty(nameParam) && name.indexOf(param.getName()) < 0) {
                            continue;
                        }
                        VtMdmRulesDetail rulesDetail = new VtMdmRulesDetail();
                        rulesDetail.setId(idObject.getString("id"));
                        rulesDetail.setDataType(dataType);
                        rulesDetail.setName(mdmVeeValidation.getName());
                        rulesDetail.setPass(idObject.getString("pass"));
                        rulesDetail.setAlarmFlag(idObject.getString("alarm_flag"));
                        rows.add(rulesDetail);
                    }
                    
                }else if("2".equals(ruleType)) {
                    estimateRules = item.getJSONArray("estimate_rules");
                    if(estimateRules == null) {
                        break;
                    }
                    totalCount = estimateRules.size();
                    for (int j = 0; j < totalCount; j++) {
                        JSONObject idObject = (JSONObject) estimateRules.get(j);
                        MdmVeeEstimate mdmVeeEstimate = mdmVeeEstimateMap.get(idObject.getString("id"));
                        String name = mdmVeeEstimate.getName();
                        String nameParam = param.getName();
                        if(StringUtils.isNotEmpty(nameParam) && name.indexOf(param.getName()) < 0) {
                            continue;
                        }
                        VtMdmRulesDetail rulesDetail = new VtMdmRulesDetail();
                        rulesDetail.setId(idObject.getString("id"));
                        rulesDetail.setDataType(dataType);
                        rulesDetail.setName(mdmVeeEstimate.getName());
                        JSONArray params = idObject.getJSONArray("params");
                        StringBuilder builder = new StringBuilder();
                        for (int k = 0; k < params.size(); k++) {
                            builder.append(((JSONObject) params.get(k)).getString("value")).append(",");
                        }
                        String paramsStr = builder.toString();
                        if(paramsStr.length() > 0) {
                            paramsStr=paramsStr.substring(0, paramsStr.length()-1);
                        }
                        rulesDetail.setParams(paramsStr);
                        rows.add(rulesDetail);
                    }
                }
                break;
            }
        }
        logger.info("rows: - {}", rows);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(rows);
        return pageBean;
    }


    @Override
    public JSONArray getValRuleJsonByType(String type) throws Exception {
        JSONArray result = new JSONArray();
        List<String> validationIds = mdmVeeFieldsValidationDao.selectByType(type);
        if(validationIds!=null) {
            for(String validationId:validationIds) {
                MdmVeeValidation mdmVeeValidation = mdmVeeValidationMap.get(validationId);
                JSONObject valRule = valRule2Json(mdmVeeValidation);
                result.add(valRule);
            }
        }
        return result;
    }
    
    @Override
    public JSONArray getEstRuleJsonByType(String type) throws Exception {
        JSONArray result = new JSONArray();
        List<String> estimateIds = mdmVeeFieldsEstimateDao.selectByType(type);
        if(estimateIds!=null) {
            for(String estimateId:estimateIds) {
                MdmVeeEstimate mdmVeeEstimate = mdmVeeEstimateMap.get(estimateId);
                JSONObject valRule = estRule2Json(mdmVeeEstimate);
                result.add(valRule);
            }
        }
        return result;
    }
    
    @Override
    public boolean insert(MdmVeeConfig mdmVeeConfig) {
        mdmVeeConfig.setCdate(new Date());
        mdmVeeConfigDao.insertSelective(mdmVeeConfig);
        //更新缓存
        ruleService.process(mdmVeeConfig);
        return true;
    }
    
    @Override
    public String searchCode() {
        String maxCode = mdmVeeConfigDao.selectMaxCode();
        if (maxCode == null) {
            return "B001";
        }
        return MdmCodeUtil.getConfigCode(maxCode);
    }
    
    @Override
    public boolean delete(String code) {
        int result = mdmVeeConfigDao.deleteByPrimaryKey(code);
        if (result <= 0) {
            throw new InheExceptionBase(-100054, "删除记录失败");
        }
        return true;
    }
    
    @Override
    public boolean update(MdmVeeConfig mdmVeeConfig) {
        int result = mdmVeeConfigDao.updateByPrimaryKeySelective(mdmVeeConfig);
        if (result <= 0) {
            throw new InheExceptionBase(-100053, "更新记录失败");
        }
        //更新缓存
        ruleService.process(mdmVeeConfig);
        return true;
    }
    
    @Override
    public MdmVeeConfig detail(MdmVeeConfig mdmVeeConfig) {
        MdmVeeConfig result = mdmVeeConfigDao.selectByPrimaryKey(mdmVeeConfig.getCode());
        if (result == null) {
            throw new InheExceptionBase(-101080, "找不到指定配置");
        }
        return result;
    }
    
    @Override
    public boolean switchActived(MdmVeeConfig mdmVeeConfig) {
        MdmVeeConfig cfg = detail(mdmVeeConfig);
        cfg.setActived(mdmVeeConfig.getActived());
        cfg.setCoperator(mdmVeeConfig.getCoperator());
        return update(cfg);
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
    
    private JSONObject valRule2Json(MdmVeeValidation mdmVeeValidation) throws Exception {
        JSONObject ruleObject = new JSONObject();
        ruleObject.put("id", mdmVeeValidation.getId());
        ruleObject.put("name", mdmVeeValidation.getName());
        ruleObject.put("nameLang", mdmVeeValidation.getNameLang());
        
        JSONObject detailObject = new JSONObject();
        detailObject.put("id", mdmVeeValidation.getId());
        detailObject.put("name", mdmVeeValidation.getName());
        detailObject.put("nameLang", mdmVeeValidation.getNameLang());
        detailObject.put("parameCount", mdmVeeValidation.getParameCount());
        
        Short parameCount = mdmVeeValidation.getParameCount();
        for (int i = 1; i <= parameCount; i++) {
            Method getParame = mdmVeeValidation.getClass().getMethod("getParame"+i);
            Method getParameLang = mdmVeeValidation.getClass().getMethod("getParame"+i+"Lang");
            Method getParameIgnore = mdmVeeValidation.getClass().getMethod("getParame"+i+"Ignore");
            String parame = (String) getParame.invoke(mdmVeeValidation);
            String parameLang = (String) getParameLang.invoke(mdmVeeValidation);
            String parameIgnore = (String) getParameIgnore.invoke(mdmVeeValidation);
            detailObject.put("parame"+i, parame);
            detailObject.put("parame"+i+"Lang", parameLang);
            detailObject.put("parame"+i+"Ignore", parameIgnore);
        }
        detailObject.put("eventType", mdmVeeValidation.getEventType());
        detailObject.put("actived", mdmVeeValidation.getActived());
        detailObject.put("coperator", mdmVeeValidation.getCoperator());
        Date cDate = mdmVeeValidation.getCdate();
        if(cDate != null) {
            detailObject.put("cdate", DateUtil.dateToString(cDate, DateUtil.DATE_TIME_WITH_LINE));
        }
        ruleObject.put("detail", detailObject);
        return ruleObject;
    }
    
    private JSONObject estRule2Json(MdmVeeEstimate mdmVeeEstimate) throws Exception {
        JSONObject ruleObject = new JSONObject();
        ruleObject.put("id", mdmVeeEstimate.getId());
        ruleObject.put("name", mdmVeeEstimate.getName());
        ruleObject.put("nameLang", mdmVeeEstimate.getNameLang());
        
        JSONObject detailObject = new JSONObject();
        detailObject.put("id", mdmVeeEstimate.getId());
        detailObject.put("name", mdmVeeEstimate.getName());
        detailObject.put("nameLang", mdmVeeEstimate.getNameLang());
        detailObject.put("parameCount", mdmVeeEstimate.getParameCount());
        
        Short parameCount = mdmVeeEstimate.getParameCount();
        for (int i = 1; i <= parameCount; i++) {
            Method getParame = mdmVeeEstimate.getClass().getMethod("getParame"+i);
            Method getParameLang = mdmVeeEstimate.getClass().getMethod("getParame"+i+"Lang");
            String parame = (String) getParame.invoke(mdmVeeEstimate);
            String parameLang = (String) getParameLang.invoke(mdmVeeEstimate);
            detailObject.put("parame"+i, parame);
            detailObject.put("parame"+i+"Lang", parameLang);
        }
        detailObject.put("actived", mdmVeeEstimate.getActived());
        detailObject.put("coperator", mdmVeeEstimate.getCoperator());
        detailObject.put("cdate", DateUtil.dateToString(mdmVeeEstimate.getCdate(), DateUtil.DATE_TIME_WITH_LINE));
        ruleObject.put("detail", detailObject);
        return ruleObject;
    }

}
