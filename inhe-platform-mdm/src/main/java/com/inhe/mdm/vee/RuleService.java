package com.inhe.mdm.vee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.constant.RedisKeyParam;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmVeeConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class RuleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleService.class);
	
	@Autowired
	InheRedisTemplate redisTemplate;
	@Autowired
	MdmVeeConfigDao mdmVeeConfigDao;
	
	public void init() {
		logger.info("Validation rule initialization - init");	
		redisTemplate.clear("MDM_VEE:");//程序启动初始化
		List<MdmVeeConfig> cfgs = mdmVeeConfigDao.selectAll();
		for(MdmVeeConfig cfg : cfgs) {
			process(cfg);
		}
	}
	
	public void process(MdmVeeConfig cfg) {
		logger.info("{}",cfg);
		String deptId,meterSort,meterModel,deviceId,dataTypeDetails;
		List<String> list = new ArrayList<String>();
		deptId = getCondition(cfg.getDeptId());//分支机构
		meterSort = getCondition(cfg.getMeterSort());//计量点类型
		meterModel = getCondition(cfg.getMeterModel());//型号
		deviceId = getCondition(cfg.getDeviceId());//电表设备号
		dataTypeDetails = cfg.getDataTypeDetails();
		
		cfg.setDeviceId(deviceId);
		cfg.setMeterModel(meterModel);
		cfg.setMeterSort(meterSort);
		cfg.setDeptId(deptId);
		
		list = mdmVeeConfigDao.SelectVeeRuleDevice(cfg);
		if(!StringUtils.isEmpty(deviceId)) {
			mapping(RedisKeyParam.MDM_VEE_METER,cfg.getCode(),list,dataTypeDetails);
		}
		else
		{
		    if(!StringUtils.isEmpty(meterSort)) {
                mapping(RedisKeyParam.MDM_VEE_SORT,cfg.getCode(),list,dataTypeDetails);
            }
            else
            {
                if(!StringUtils.isEmpty(deptId)) {//1
                    mapping(RedisKeyParam.MDM_VEE_DEPT,cfg.getCode(),list,dataTypeDetails);
                }
            }
		}
	}
	
	private String getCondition(String condition) {
		if(!StringUtils.isEmpty(condition)) {
			StringBuilder sb = new StringBuilder();
			String[] conditionArry = condition.split("[,\\|]");
			for(String str: conditionArry) {
				sb.append("'"+ str + "',");
			}
			return sb.substring(0, sb.length()-1);
		}else {
			return condition;
		}
		
	}
	
	private void mapping(String prefix,String taskCode,List<String> list,String dataTypeDetails) {
		JSONArray rules = JSONObject.parseArray(dataTypeDetails);
		for(String deviceNum:list) {
			if(rules!=null) {
				for(int i=0;i<rules.size();i++) {
					String valid = rules.getJSONObject(i).getString("valid");//是否有效
					if("Y".equals(valid)) {
						String dataType = rules.getJSONObject(i).getString("data_type");
						JSONArray fieldids = rules.getJSONObject(i).getJSONArray("fieldids");
						JSONArray validationRules = rules.getJSONObject(i).getJSONArray("validation_rules");
						if(validationRules!=null) {
							if(fieldids!=null) {//数据项
								for(int j=0;j<fieldids.size();j++) {
									String field = fieldids.getJSONObject(j).getString("id");
									String key = prefix + deviceNum+"_"+field +"_"+dataType;
									String sValue = taskCode+"#"+validationRules.toJSONString();
									if (redisTemplate.hasKey(key))
									{
										if (!sValue.equals(redisTemplate.getValue(key))) 
											logger.info("Verification rule conflict OLD:" +key +" - "+ redisTemplate.getValue(key)+" NEW:"+key+" - "+sValue);	
									}
									redisTemplate.set(key, sValue,18000,TimeUnit.DAYS);
								}
							}else {//用电量、其他数据
								String key = prefix + deviceNum+"_"+dataType;
								String sValue = taskCode+"#"+validationRules.toJSONString();
								if (redisTemplate.hasKey(key))
								{
									if (!sValue.equals(redisTemplate.getValue(key))) 
										logger.info("Verification rule conflict OLD:" +key +" - "+ redisTemplate.getValue(key)+" NEW:"+key+" - "+sValue);	
								}
								redisTemplate.set(key,sValue,18000,TimeUnit.DAYS);
							}
						}
						JSONArray estimateRules = rules.getJSONObject(i).getJSONArray("estimate_rules");
						if(estimateRules!=null) {
							if(fieldids!=null) {//数据项
								for(int j=0;j<fieldids.size();j++) {
									String field = fieldids.getJSONObject(j).getString("id");
									String key = prefix +"EST:"+ deviceNum+"_"+field +"_"+dataType;
									String sValue = taskCode+"#"+estimateRules.toJSONString();
									if (redisTemplate.hasKey(key))
									{
										if (!sValue.equals(redisTemplate.getValue(key))) 
											logger.info("estimate rule conflict OLD:" +key +" - "+ redisTemplate.getValue(key)+" NEW:"+key+" - "+sValue);	
									}
									redisTemplate.set(key, sValue,18000,TimeUnit.DAYS);
								}
							}else {//用电量、其他数据
								String key = prefix +"EST:" + deviceNum+"_"+dataType;
								String sValue = taskCode+"#"+estimateRules.toJSONString();
								if (redisTemplate.hasKey(key))
								{
									if (!sValue.equals(redisTemplate.getValue(key))) 
										logger.info("estimate rule conflict OLD:" +key +" - "+ redisTemplate.getValue(key)+" NEW:"+key+" - "+sValue);	
								}
								redisTemplate.set(key,sValue,18000,TimeUnit.DAYS);
							}
						}
					}
				}
			}
		}
	}
}
