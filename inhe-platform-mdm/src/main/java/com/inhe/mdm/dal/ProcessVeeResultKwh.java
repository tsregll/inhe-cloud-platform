package com.inhe.mdm.dal;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.utils.DateUtil;

/**
 * @description 数据校验消息订阅  更新kwhDetail
 * @author
 * @date 2020/01/19
 */
@Service
public class ProcessVeeResultKwh {
    
	private static final Logger logger = LoggerFactory.getLogger(ProcessVeeResultKwh.class);
	
	@Autowired
    MdmAaKwhDetailDao mdmAaKwhDetailDao;
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
	
	public synchronized void execute(JSONObject jobj) throws Exception{
        logger.info("receive msg:"+jobj);
        try {
            String iSort = jobj.getString("sort");
            if (iSort.equals("0")) {
                veeVerify(jobj);
            }
            else if (iSort.equals("1")){
                veeEstimate(jobj);
            }
            else if (iSort.equals("2")){
                veeEdit(jobj);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }
	
	/**
     * Vee校验
     */
    public String veeVerify(JSONObject msgBody) {
        JSONObject content = msgBody.getJSONObject("content");
        JSONObject newcontent1 = new JSONObject();
        newcontent1.put("result",content.getString("veeresult") );
        String cmd = content.getString("cmd");
        JSONArray result = content.getJSONArray("result");
        newcontent1.put(cmd, result);
        JSONArray json = new JSONArray();
        json.add(newcontent1);
        MdmAaKwhDetail aaKwhDetail = new MdmAaKwhDetail();
        String deviceId = content.getString("device_id");
        JSONObject rootJson = new JSONObject();
        String type = content.getString("data_type");
        String timeType = null;
        if("09".equals(type)) {   //日用电量
            timeType = "0";
        }else if("10".equals(type)) {  //月用电量
            timeType = "1";
        }
        aaKwhDetail.setDeviceId(deviceId);
        aaKwhDetail.setTimeType(timeType);
        aaKwhDetail.setNowTime(content.getString("time"));
        String veerdetail = loadVeeResult("0",aaKwhDetail);
        if((veerdetail != null)&&(!veerdetail.equals(""))){
            JSONObject veer = JSONObject.parseObject(veerdetail);
            JSONArray veerdata = veer.getJSONArray("data");
            for(int i = 0; i < veerdata.size(); i++){         
                JSONObject object = veerdata.getJSONObject(i);
                Set<String> keys = object.keySet();
                if (!keys.contains(content.getString("cmd")) && (keys.size() >= 2)) {
                    json.add(object);   
                }
            }
        }
        rootJson.put("data",json);
        String verifyDetail=rootJson.toJSONString();
        String estimateDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("1",aaKwhDetail));
        String editDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("2",aaKwhDetail));

        aaKwhDetail.setVeeResultDetail(verifyDetail);
        aaKwhDetail.setVeeEstmtDetail(estimateDetail);
        aaKwhDetail.setVeeEditDetail(editDetail);
        mdmAaKwhDetailDao.updateResultByPrimaryKey(aaKwhDetail);
        logger.info("receive vee verify data and update result successfully");
        return "0";
    }
    
    /**
     * Vee预估
     */
    public String veeEstimate(JSONObject msgBody) {
        JSONObject content = msgBody.getJSONObject("content");
        String deviceId = content.getString("device_id");
        String type = content.getString("data_type");
        String timeType = null;
        if("09".equals(type)) {   //日用电量
            timeType = "0";
        }else if("10".equals(type)) {  //月用电量
            timeType = "1";
        }
        MdmAaKwhDetail aaKwhDetail = new MdmAaKwhDetail();
        aaKwhDetail.setDeviceId(deviceId);
        aaKwhDetail.setTimeType(timeType);
        aaKwhDetail.setNowTime(content.getString("time"));
        String estimateDetail = loadVeeResult("1",aaKwhDetail);
        JSONObject json = analyEstimateValue(content,estimateDetail);//处理json
        if (!content.getString("veeresult").equals("0")) {
            content.put("estValue",0);
        }
        String cmd = content.getString("cmd");
        switch (cmd) {
            case "VEE00012"://日用电量(总有功)
                aaKwhDetail.setTotalKwh(content.getDouble("estValue"));
                break;
            case "VEE30012"://日用电量(正向有功)
                aaKwhDetail.setKwhPaPtct(content.getDouble("estValue"));//KWH_PA_PTCT
                break;          
            case "VEE40012"://日用电量(反向有功)
                aaKwhDetail.setKwhRaPtct(content.getDouble("estValue"));//KWH_RA_PTCT
                break;          
            case "VEE50012"://日用电量(正向无功)
                aaKwhDetail.setKwhPrPtct(content.getDouble("estValue"));//KWH_PR_PTCT
                break;          
            case "VEE60012"://日用电量(反向无功)
                aaKwhDetail.setKwhRrPtct(content.getDouble("estValue"));//KWH_RR_PTCT
                break;      

            case "VEE00112"://月用电量(总有功)
                aaKwhDetail.setTotalKwh(content.getDouble("estValue"));
                break;          
            case "VEE30112"://月用电量(正向有功)
                aaKwhDetail.setKwhPaPtct(content.getDouble("estValue"));
                break;          
            case "VEE40112"://月用电量(反向有功)
                aaKwhDetail.setKwhRaPtct(content.getDouble("estValue"));
                break;          
            case "VEE50112"://月用电量(正向无功)
                aaKwhDetail.setKwhPrPtct(content.getDouble("estValue"));
                break;          
            case "VEE60112"://月用电量(反向无功)
                aaKwhDetail.setKwhRrPtct(content.getDouble("estValue"));
                break;          
        }   

        String verifyDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("0",aaKwhDetail));
        String editDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("2",aaKwhDetail));
        aaKwhDetail.setVeeResultDetail(verifyDetail);
        aaKwhDetail.setVeeEstmtDetail(json.toJSONString());//更新字段
        aaKwhDetail.setVeeEditDetail(editDetail);
        mdmAaKwhDetailDao.updateResultByPrimaryKey(aaKwhDetail);
        logger.info("receive vee estimate data and update result successfully");
        return "0";
    }
    
    /**
     * Vee编辑
     */
    public String veeEdit(JSONObject msgBody) throws Exception {
        JSONObject content = msgBody.getJSONObject("content");
        String deviceId = content.getString("device_id");
        String type = content.getString("data_type");
        String timeType = null;
        if("09".equals(type)) {   //日用电量
            timeType = "0";
        }else if("10".equals(type)) {  //月用电量
            timeType = "1";
        }
        String cmd = content.getString("cmd");
        MdmAaKwhDetail aaKwhDetail = new MdmAaKwhDetail();
        aaKwhDetail.setDeviceId(deviceId);
        aaKwhDetail.setTimeType(timeType);
        aaKwhDetail.setNowTime(filstDateToStr(timeType,content.getString("time"),-1));
        String editDetail = loadVeeResult("2",aaKwhDetail);
        switch (cmd) {
            case "VEE00012"://日用电量(总有功)
                aaKwhDetail.setTotalKwh(content.getDouble("result"));
                break;
            case "VEE30012"://日用电量(正向有功)
                aaKwhDetail.setKwhPaPtct(content.getDouble("result"));//KWH_PA_PTCT
                break;          
            case "VEE40012"://日用电量(反向有功)
                aaKwhDetail.setKwhRaPtct(content.getDouble("result"));//KWH_RA_PTCT
                break;          
            case "VEE50012"://日用电量(正向无功)
                aaKwhDetail.setKwhPrPtct(content.getDouble("result"));//KWH_PR_PTCT
                break;          
            case "VEE60012"://日用电量(反向无功)
                aaKwhDetail.setKwhRrPtct(content.getDouble("result"));//KWH_RR_PTCT
                break;      

            case "VEE00112"://月用电量(总有功)
                aaKwhDetail.setTotalKwh(content.getDouble("result"));
                break;          
            case "VEE30112"://月用电量(正向有功)
                aaKwhDetail.setKwhPaPtct(content.getDouble("result"));
                break;          
            case "VEE40112"://月用电量(反向有功)
                aaKwhDetail.setKwhRaPtct(content.getDouble("result"));
                break;          
            case "VEE50112"://月用电量(正向无功)
                aaKwhDetail.setKwhPrPtct(content.getDouble("result"));
                break;          
            case "VEE60112"://月用电量(反向无功)
                aaKwhDetail.setKwhRrPtct(content.getDouble("result"));
                break;          
        }   
        JSONObject json = analyEditValue(content,editDetail);//处理json
        String verifyDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("0",aaKwhDetail));
        String estimateDetail = removeCmdFromVeeDetail(cmd,loadVeeResult("1",aaKwhDetail));
        aaKwhDetail.setVeeResultDetail(verifyDetail);
        aaKwhDetail.setVeeEstmtDetail(estimateDetail);//更新字段
        aaKwhDetail.setVeeEditDetail(json.toJSONString());
        mdmAaKwhDetailDao.updateResultByPrimaryKey(aaKwhDetail);
        
        logger.info("receive vee edit data and update result successfully");
        return "0";
    }
    
    private String filstDateToStr(String timeType,String beginDate, int dayNum) throws Exception {
        Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        if (beginDate.length() > 13) {
            cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_TIME_WITH_LINE));//yyyy-MM-dd hh:mm:ss
        }
        else if (beginDate.length() == 10){
            cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_WITH_LINE));    //yyyy-MM-dd
        }
        // 通过格式化输出日期
        java.text.SimpleDateFormat format;
        if (timeType.equals("0")) {
            cal.add(Calendar.DATE, dayNum);// 取当前日期的前一天.
            format = new java.text.SimpleDateFormat(DateUtil.DATE_WITH_LINE);
        }
        else {
            cal.add(Calendar.MONTH, dayNum);// 取当前月份的前一月.
            format = new java.text.SimpleDateFormat(DateUtil.DATE_MONTH_LINE);
        }
        return format.format(cal.getTime());
    }
    
    /**
     * @description 获取aaKwhDetail数据库里面已经存入VeeResultDetail的数据
     * @param aaKwhDetail
     * @return
     */
    private String loadVeeResult(String type,MdmAaKwhDetail aaKwhDetail) {
        String veedetail = null;
        aaKwhDetail = mdmAaKwhDetailDao.selectByPrimaryKey(aaKwhDetail);
        if (aaKwhDetail == null) {
            return null;
        }
        if (type.equals("0")) {
            veedetail = aaKwhDetail.getVeeResultDetail();
        }
        else if (type.equals("1")) {
            veedetail = aaKwhDetail.getVeeEstmtDetail();
        }
        else if (type.equals("2")) {
            veedetail = aaKwhDetail.getVeeEditDetail();
        }
        return veedetail;
    }
    
    /**
     * @description 根据接收的vee预估数据，更新json
     * @param content，传入的json的content值 
     * @param veerdetail，数据库中的getVeeResultDetail的值
     * @return 返回更新后的，要保存的getVeeResultDetail
     */
    private JSONObject analyEstimateValue(JSONObject content,String estimateDetail) {
        JSONObject rootJson = new JSONObject();
        JSONArray json = new JSONArray();
        JSONObject content1 = new JSONObject();     
        if ("0".equals(content.getString("veeresult"))) {
            content1.put("result", "S");
        }
        else {
            content1.put("result", "F");
        }
        JSONArray detail = new JSONArray();
        JSONArray result = content.getJSONArray("result");
        for (Object obj : result) {
            JSONObject se = new JSONObject(new LinkedHashMap<>());
            JSONObject re = (JSONObject)obj;
            se.put("id", re.getString("id"));
            se.put("remarks", re.getString("remarks"));
            se.put("result", re.getString("result"));
            se.put("value", content.getString("estValue"));
            detail.add(se);
        }
        content1.put(content.getString("cmd"), detail);
        json.add(content1); 
        if ((estimateDetail != null) && (!estimateDetail.equals(""))) {
            JSONObject veer = JSONObject.parseObject(estimateDetail);
            JSONArray veerdata = veer.getJSONArray("data");
            for(int i = 0; i < veerdata.size(); i++){ 
                JSONObject object = veerdata.getJSONObject(i);
                Set<String> keys = object.keySet();//result,VEEXXXX
                if (!keys.contains(content.getString("cmd")) && (keys.size() >= 2)) {
                    json.add(object);   
                }
            }
        }
        rootJson.put("data",json);
        return rootJson;
    }
    
    /**
     * @description 根据接收的vee编辑数据，更新json
     * @param content，传入的json的content值 
     * @param veerdetail，数据库中的getVeeResultDetail的值
     * @return 返回更新后的，要保存的getVeeResultDetail
     */
    private JSONObject analyEditValue(JSONObject content,String estimateDetail) {
        //db数据库中保存的格式：{"data":[{"VEE00012":"0.20"},{"VEE60112":"5.20"}]}
        JSONObject rootJson = new JSONObject();
        JSONArray json = new JSONArray();
        JSONObject content1 = new JSONObject();     
        content1.put(content.getString("cmd"), content.getString("result"));
        json.add(content1); 
        if ((estimateDetail != null) && (!estimateDetail.equals(""))) {
            JSONObject veer = JSONObject.parseObject(estimateDetail);
            JSONArray veerdata = veer.getJSONArray("data");
            for(int i = 0; i < veerdata.size(); i++){        
                JSONObject object = veerdata.getJSONObject(i);
                Set<String> keys = object.keySet();//result,VEEXXXX
                if (!keys.contains(content.getString("cmd")) && (keys.size() > 0)) {
                    json.add(object);   
                }
            }
        }
        rootJson.put("data",json);
        return rootJson;
    }
    
    private String removeCmdFromVeeDetail(String cmd,String veeDetail) {
        JSONObject rootJson = new JSONObject();
        JSONArray json = new JSONArray();
        if ((veeDetail == null) || (veeDetail.equals(""))) {
            return null;
        }
        JSONObject veer = JSONObject.parseObject(veeDetail);
        JSONArray veerdata = veer.getJSONArray("data");
        for(int i = 0; i < veerdata.size(); i++){
            JSONObject object = veerdata.getJSONObject(i);
            Set<String> keys = object.keySet();
            if (!keys.contains(cmd)){
                json.add(object);   
            }
        }
        if (json.size() > 0) {
            rootJson.put("data",json);
            return rootJson.toJSONString();
        }
        else {
            return null;
        }
    }

}
