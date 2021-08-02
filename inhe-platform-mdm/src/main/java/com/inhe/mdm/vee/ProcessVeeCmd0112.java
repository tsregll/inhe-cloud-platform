package com.inhe.mdm.vee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.constant.RedisKeyParam;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.dao.MdmVeeTaskDataDao;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.vee.VeeR001Impl;
import com.inhe.mdm.vee.VeeR002Impl;
import com.inhe.mdm.vee.VeeR003Impl;
import com.inhe.mdm.vee.VeeR004Impl;
import com.inhe.mdm.vee.VeeR005Impl;
import com.inhe.mdm.vee.VeeR006Impl;
import com.inhe.mdm.vee.VeeR007Impl;
import com.inhe.mdm.vee.VeeR008Impl;
import com.inhe.mdm.vee.VeeR009Impl;
import com.inhe.mdm.vee.VeeR010Impl;
import com.inhe.mdm.vee.VeeR011Impl;
import com.inhe.mdm.vee.VeeR012Impl;
import com.inhe.mdm.vee.VeeR013Impl;
import com.inhe.mdm.vee.VeeR014Impl;
import com.inhe.mdm.vee.VeeR015Impl;
import com.inhe.mdm.vee.VeeR016Impl;
import com.inhe.mdm.vee.VeeR017Impl;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.vee.VeeE001Impl;
import org.springframework.util.StringUtils;
import com.inhe.utils.DateUtil;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description 数据校验消息订阅
 * @author 
 * @date 2020/12/08
 */
@Service
public class ProcessVeeCmd0112 {
    
    private static final Logger logger = LoggerFactory.getLogger(ProcessVeeCmd0112.class);
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    @Autowired
    InheRedisTemplate redisTemplate;
    @Autowired
    InheRedisTemplate inheRedisTemplate;
    @Autowired
    VeeR001Impl r001;
    @Autowired
    VeeR002Impl r002;
    @Autowired
    VeeR003Impl r003;
    @Autowired
    VeeR004Impl r004;
    @Autowired
    VeeR005Impl r005;
    @Autowired
    VeeR006Impl r006;
    @Autowired
    VeeR007Impl r007;
    @Autowired
    VeeR008Impl r008;
    @Autowired
    VeeR009Impl r009;
    @Autowired
    VeeR010Impl r010;
    @Autowired
    VeeR011Impl r011;
    @Autowired
    VeeR012Impl r012;
    @Autowired
    VeeR013Impl r013;
    @Autowired
    VeeR014Impl r014;
    @Autowired
    VeeR015Impl r015;
    @Autowired
    VeeR016Impl r016;
    @Autowired
    VeeR017Impl r017;
    @Autowired
    VeeE001Impl e001;
    @Autowired
    MdmVeeComServiceImpl commonService;
    @Autowired
    MdmVeeTaskDataDao mdmVeeTaskDataDao;
    @Autowired
    MdmVeeConfigDao mdmVeeConfigDao;
    @Value("${spring.application.name}")
    private String serviceName;
    
    
    public String process(JSONObject msgBody) {
        if (msgBody != null ) {
            String key = null;
            JSONObject content = msgBody.getJSONObject("content");
            String sort =msgBody.getString("sort");  //0:校验  1：预估  
            String deviceId = content.getString("device_id");
            MdmAmDevice device = mdmAmDeviceDao.selectByPrimaryKey(deviceId);//实时查询
            if(device == null) {
                throw new InheExceptionBase(-10000,"can not find device : " + deviceId);
            }
            String dataType = content.getString("data_type");//BAL消息格式修改过，需要决定是否匹配 - pfr210125
            String cmd = content.getString("cmd");  
            //String type = content.getString("type");//校验数据类型
            String type = commonService.parseType(dataType,cmd);
            
            if(StringUtils.isEmpty(cmd)) {
                key = device.getDeviceNum() +"_" + type;
            }else {
                key = device.getDeviceNum() +"_" +cmd+"_"+ type;
            }
            
            if(device != null) {
                String rules = getRulesByKey(key,sort);//规则；
                if(!StringUtils.isEmpty(rules)) {
                    logger.info("Received Msg:" +msgBody);
                    String cmdDesc = "";
                    if (cmd != null) {
                      if(redisTemplate.hasKey(RedisKeyParam.MDM_VEE_FIELDS + cmd)) {
                        cmdDesc=redisTemplate.getValue(RedisKeyParam.MDM_VEE_FIELDS + cmd);
                       }
                      else
                      {
                        cmdDesc = mdmVeeConfigDao.selectDescByCmd(cmd) ;
                        if (cmdDesc==null)
                            cmdDesc = cmd;
                        redisTemplate.set(RedisKeyParam.MDM_VEE_FIELDS + cmd,cmdDesc,10,TimeUnit.DAYS);
                      }
                    }
                    else
                    {
                        if ("09".equals(type))
                            cmdDesc = "DAYKWH";
                        if ("10".equals(type))
                            cmdDesc = "MONTHKWH";
                    }
                    String taskCode = "B001";
                    if(rules.split("#").length>1) {
                        taskCode = rules.split("#")[0];
                        rules  = rules.split("#")[1];   
                    }
                    String recordCode = MdmCodeUtil.getVeeTaskDataCode().substring(0, 25);
                    scanRule(device,type,taskCode,recordCode,cmdDesc,rules,msgBody);
                }
            }   
        }
        
        return "0";
    }

    private String getRulesByKey(String key,String sort) {  
        String rules =null;
        String str = "";
        if (sort.equals("0"))
          str = "";
        if (sort.equals("1"))
          str = "EST:";
        key = str + key;
        if(redisTemplate.hasKey(RedisKeyParam.MDM_VEE_METER + key)) {
            rules=redisTemplate.getValue(RedisKeyParam.MDM_VEE_METER + key);          
        }
        else {
            if(redisTemplate.hasKey(RedisKeyParam.MDM_VEE_CON + key)) {
                rules=redisTemplate.getValue(RedisKeyParam.MDM_VEE_CON + key);
            }
            else {
                if(redisTemplate.hasKey(RedisKeyParam.MDM_VEE_SORT + key)) {
                    rules=redisTemplate.getValue(RedisKeyParam.MDM_VEE_SORT + key);
                }
                else {
                    if(redisTemplate.hasKey(RedisKeyParam.MDM_VEE_DEPT + key)) {// 机构分层,由低到高
                        rules=redisTemplate.getValue(RedisKeyParam.MDM_VEE_DEPT + key);
                    }
                }
            }
        }
        return rules;
    }
    
    private void scanRule(MdmAmDevice device,String type,String taskCode,String recordCode,String cmdDesc,String rules,JSONObject msgBody) {
        JSONArray ruleArray = JSONObject.parseArray(rules);
        JSONArray result = new JSONArray();
        msgBody.put("rCode", recordCode);
        msgBody.put("cmdDesc", cmdDesc);
        try {
            for(int i=0;i<ruleArray.size();i++) {
                JSONObject rule = ruleArray.getJSONObject(i);
                String ifPass = rule.getString("pass");
                if(!execRule(device,rule,msgBody,result) && "N".equals(ifPass)) {//验证未通过，是否继续下一个验证
                    break;
                }
            }
        }
        finally
        {
            insertData(device,taskCode,recordCode,type,msgBody,result); 
            
        }   
    }
    
    private void insertData(MdmAmDevice device,String taskCode,String recordCode,String type,JSONObject msg,JSONArray result) {
        try {
        String sort = msg.getString("sort");
        JSONObject content = msg.getJSONObject("content");
        String dateStr = content.getString("time");
        String dataType = content.getString("data_type");
        String msgType = msg.getString("type"); 
        
        Date readTime = null;
        if(dateStr.length()==7) {//月
            readTime=DateUtil.stringToDate(dateStr, "yyyy-MM");
        }else {
            readTime=content.getDate("time");
        }
        //String source = msgBody.getString("source");
        MdmVeeTaskData taskData = new MdmVeeTaskData();
        taskData.setDataType(2);
        taskData.setId(recordCode);
        taskData.setTaskCode(taskCode); 
        taskData.setCycleTimes(1);
        taskData.setReadTime(readTime);
        taskData.setOrgId(device==null?"-":device.getOrgId());
        taskData.setDeviceCode(device==null?"-":device.getId());
        taskData.setFieldId(content.getString("cmd")==null?"-":content.getString("cmd"));
        taskData.setDeviceNum(device==null?"-":device.getDeviceNum());
        taskData.setVeeDataType(type);
        taskData.setVeeType(sort);
        taskData.setRealValue(((content.getString("data")==null)||(content.getString("data").equals("")))?"-":content.getString("data"));
        taskData.setStatus("1");
        
        String res= result.toJSONString();
        taskData.setVeeResult(handleResult(res));
        taskData.setVeeResultDetails(res);  
        if (taskData.getVeeResultDetails().equals("[]")){
            taskData.setVeeResult("2");
        }   
        if ( (("09".equals(type)||"10".equals(type))&&((sort.equals("0"))||((sort.equals("1")&&(true))) ))||
                (("0".equals(msgType)&& (("4".equals(dataType))||("5".equals(dataType))||("6".equals(dataType))||("7".equals(dataType))) )) ) {  //09 10类型的校验/预估回0112消息给dal工作站
                content.put("result", taskData.getVeeResultDetails());
                if(taskData.getVeeResult().equals("0")) {
                    if (sort.equals("0"))
                      content.put("veeresult", "S");
                    else
                      content.put("veeresult", "0");    
                }
                else
                {
                    if (sort.equals("0"))
                      content.put("veeresult", "F");
                    else
                      content.put("veeresult", "2");    
                }       
                content.remove("cmdDesc");
                content.remove("refCode");
                msg.put("source", serviceName);
                msg.remove("cmdDesc");          
                msg.remove("rCode");
                String function;
                if("09".equals(type)||"10".equals(type)) {
                    function = "2";//功能 0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
                }else {
                    function = "1";
                }
                commonService.send0112Save(function, msg);
                logger.info("send0112Save :"+ msg);
        }
        if (sort.equals("1")){
            if (handleResult(res).equals("0"))//如果预估成功则取预估值
              taskData.setEstimateValue(content.getString("estValue"));//预估值
        }
        taskData.setCdate(new Date());
        mdmVeeTaskDataDao.insertSelective(taskData);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private String handleResult(String result) {
        String res = "0";//成功
        if(result.indexOf("\"result\":1")!=-1) {
            res = "1";
        }else if(result.indexOf("\"result\":2")!=-1) {
            res = "2";
        }
        return res;
    }
    
    private Boolean execRule(MdmAmDevice device,JSONObject rule,JSONObject msg,JSONArray result) {//验证未通过，是否继续下一个验证
        String id = rule.getString("id");
        Boolean pass = true;
        JSONObject msgBody = msg.getJSONObject("content");
        String reqData = msgBody.getString("data");
        if(reqData!=null&&reqData.split(";").length>1) {
            msgBody.put("data", reqData.split(";")[0]);
        }
        msgBody.put( "refCode",msg.getString("rCode"));
        msgBody.put( "cmdDesc",msg.getString("cmdDesc"));
        
        switch (id) {
            case "R001"://本次值<上次值
                pass = r001.check(device,rule,msgBody,result);
                break;
            case "R002"://本次值=上次值
                pass = r002.check(device,rule,msgBody,result);
                break;
            case "R003"://表码连续缺失，连续N次无数据
                pass = r003.check(device,rule,msgBody,result);
                break;
            case "R004"://获取数据超过预设数据范围
                pass = r004.check(device,rule,msgBody,result);
                break;
            case "R005"://本次值为0
                pass = r005.check(device,rule,msgBody,result);
                break;
            case "R006"://用电量远远低于平均水平(平均值)
                pass = r006.check(device,rule,msgBody,result);
                break;
            case "R007"://用电量远远低于平均水平(常量)
                pass = r007.check(device,rule,msgBody,result);
                break;
            case "R008"://用电量远远高于平均水平(平均值)
                pass = r008.check(device,rule,msgBody,result);
                break;
            case "R009"://用电量远远高于平均水平(常量)
                pass = r009.check(device,rule,msgBody,result);
                break;
            case "R010"://连续N次为0
                pass = r010.check(device,rule,msgBody,result);
                break;
            case "R011"://值为负数
                pass = r011.check(device,rule,msgBody,result);
                break;
            case "R012"://总和检查(Sum check)：分时合计与总和做比较
                pass = r012.check(device,rule,msgBody,result);
                break;
            case "R013"://总和检查(Sum check)：日用电合计与月用电做比较
                pass = r013.check(device,rule,msgBody,result);
                break;
            case "R014"://用电量与上个月比较
                pass = r014.check(device,rule,msgBody,result);
                break;
            case "R015"://用电量与去年同个月比较
                pass = r015.check(device,rule,msgBody,result);
                break;
            case "R016"://最大需量与上个月比较
                pass = r016.check(device,rule,msgBody,result);
                break;
            case "R017"://最大需量与去年同个月比较
                pass = r017.check(device,rule,msgBody,result);
                break;
            case "E001"://最大需量与去年同个月比较
                pass = e001.check(device,rule,msgBody,result);
                break;
            default:
                System.out.println("INVALID RULE CODE");
            }
         return pass;
    }
    
}
