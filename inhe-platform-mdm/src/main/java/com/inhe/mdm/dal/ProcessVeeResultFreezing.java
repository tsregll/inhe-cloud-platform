package com.inhe.mdm.dal;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmDayFreezingDao;
import com.inhe.mdm.dao.MdmMonthFreezingDao;
import com.inhe.mdm.model.MdmDayFreezing;
import com.inhe.mdm.model.MdmMonthFreezing;
import com.inhe.utils.DateUtil;

/**
 * @description 数据校验消息订阅  更新freezing
 * @author
 * @date 2020/01/15
 */
@Service
public class ProcessVeeResultFreezing {
    
	private static final Logger logger = LoggerFactory.getLogger(ProcessVeeResultFreezing.class);
	
	@Autowired
    MdmDayFreezingDao mdmDayFreezingDao;
    
    @Autowired
    MdmMonthFreezingDao mdmMonthFreezingDao;
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    
	public synchronized void execute(JSONObject jobj) throws Exception{
        logger.info("$0112 receive: "+jobj.toJSONString());
        String iSort = jobj.getString("sort");
        //type定义：(4，日冻结电量)   (5，日使用电量)   (6，月冻结电量)  (7，月使用电量)
        String type = jobj.getJSONObject("content").getString("data_type");
        String timeType = null;//0日，1月
        String dataType = null;//1冻结，2使用
        if("4".equals(type)) {   //日冻结电量
            timeType = "0";
            dataType = "1";
        }else if("5".equals(type)) {  //日使用电量
            timeType = "0";
            dataType = "2";
        }else if("6".equals(type)) {  //月冻结电量
            timeType = "1";
            dataType = "1";
        }else if("7".equals(type)) {  //月使用电量
            timeType = "1";
            dataType = "2";
        }
        else {
            logger.info("dataType can't used");
            return;
        }
        String time = jobj.getJSONObject("content").getString("time");
        time = filstDateToStr(timeType,time,-1);//传入日期的前一天，格式为yyyyMMdd
        if (iSort.equals("0")) {
            veeVerify(jobj,timeType,dataType,time);
        }
        else if (iSort.equals("1")){
            veeEstimate(jobj,timeType,dataType,time);
        }
        else if (iSort.equals("2")) {
            veeEdit(jobj,timeType,dataType,time);
        }
    }
	    
    private String filstDateToStr(String timeType,String beginDate, int dayNum) throws Exception {
        Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
        if (beginDate.length() > 13) {
            cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_TIME_WITH_LINE));//yyyy-MM-dd hh:mm:ss
        }
        else if (beginDate.length() == 10){
            cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_WITH_LINE));    //yyyy-MM-dd
        }
        // 通过格式化输出日期
        java.text.SimpleDateFormat format;
        if (timeType.equals("0")) {
            cal.add(Calendar.DATE, dayNum);// 取当前日期的前一天
            format = new java.text.SimpleDateFormat(DateUtil.DATE_WITH_NOTHING);
        }
        else {
            cal.add(Calendar.MONTH, dayNum);// 取当前月份的前一月
            format = new java.text.SimpleDateFormat(DateUtil.DATE_WITHOUT_DAY);
        }
        return format.format(cal.getTime());
    }
    
    /**
     * Vee校验
     */
    private String veeVerify(JSONObject msgBody,String timeType,String dataType,String time) {
        JSONObject content = msgBody.getJSONObject("content");
        JSONObject newcontent1 = new JSONObject();
        newcontent1.put("result",content.getString("veeresult") );
        String cmd = content.getString("cmd");
        JSONArray result = content.getJSONArray("result");
        newcontent1.put(cmd, result);
        JSONArray json = new JSONArray();
        json.add(newcontent1);
        String deviceId = content.getString("device_id");
        JSONObject rootJson = new JSONObject();

        String veerdetail = loadVeeResult(timeType,dataType,"0",deviceId,time);
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
        String estimateDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"1",deviceId,time);
        String editDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"2",deviceId,time);
        
        updateFreezingData(timeType,dataType,deviceId,time,"","0",verifyDetail,estimateDetail,editDetail);
        logger.info("receive vee verify data and update result successfully");
        return "0";
    }
    
    /**
     * Vee预估
     */
    public String veeEstimate(JSONObject msgBody,String timeType,String dataType,String time) {
        JSONObject content = msgBody.getJSONObject("content");
        String deviceId = content.getString("device_id");
        String veerdetail = loadVeeResult(timeType,dataType,"1",deviceId,time);
        String cmd=content.getString("cmd");
        String value=content.getString("veeresult");
        if ("0".equals(value)) {
            value = content.getString("estValue");
        }
        else {
            cmd = "FFFFFFFF";//如果预估失败，则不更新具体数据项
        }
        JSONObject rootJson = analyEstimateValue(content,veerdetail);//处理json
        String verifyDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"0",deviceId,time);
        String estimateDetail = rootJson.toJSONString();
        String editDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"2",deviceId,time);
        updateFreezingData(timeType,dataType,deviceId,time,cmd,value,verifyDetail,estimateDetail,editDetail);
        logger.info("receive vee estimate data and update result successfully");
        return "0";
    }
    
    /**
     * Vee编辑
     */
    public String veeEdit(JSONObject msgBody,String timeType,String dataType,String time) {
        JSONObject content = msgBody.getJSONObject("content");
        String cmd = content.getString("cmd");
        String deviceId = content.getString("device_id");
        String veerdetail = loadVeeResult(timeType,dataType,"2",deviceId,time);
        JSONObject rootJson = analyEditValue(content,veerdetail);//处理json
        String verifyDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"0",deviceId,time);
        String estimateDetail = removeCmdFromVeeDetail(cmd,timeType,dataType,"1",deviceId,time);
        String editDetail = rootJson.toJSONString();

        String value = content.getString("result");
        updateFreezingData(timeType,dataType,deviceId,time,cmd,value,verifyDetail,estimateDetail,editDetail);
        logger.info("receive vee edit data and update result successfully");
        return "0";
    }
    
    /**
     * @description 从数据库中获取vee数据
     * @param timeType：0,日冻结数据，1,月冻结数据
     * @param veeType：0,数据校验，1,数据预估，2,数据编辑
     * @param deviceId：设备编号
     * @param time：时间，yyyyMMdd 或者 yyyyMM
     * @return
     */
    private String loadVeeResult(String timeType,String dataType,String veeType,String deviceId,String time) {
        String veeDetail="";
        if (timeType.equals("0")) {
            MdmDayFreezing record = new MdmDayFreezing();
            record.setDataType(2);
            record.setDeviceId(deviceId);
            record.setDataPeriod(time);
            record.setFzDataType(dataType);//1冻结电量，2使用电量
            record = mdmDayFreezingDao.selectByPrimaryKey(record);
            if (record == null) {
                return null;
            }
            if (veeType.equals("0")) {
                veeDetail = record.getVeeResultDetail();
            }
            else if (veeType.equals("1")) {
                veeDetail = record.getVeeEstmtDetail();
            }
            else if (veeType.equals("2")) {
                veeDetail = record.getVeeEditDetail();
            }
        }
        else if (timeType.equals("1")) {
            MdmMonthFreezing record = new MdmMonthFreezing();
            record.setDeviceId(deviceId);
            record.setDataPeriod(time);
            record.setFzDataType(dataType);//1冻结电量，2使用电量
            record = mdmMonthFreezingDao.selectByPrimaryKey(record);
            if (record == null) {
                return null;
            }
            if (veeType.equals("0")) {
                veeDetail = record.getVeeResultDetail();
            }
            else if (veeType.equals("1")) {
                veeDetail = record.getVeeEstmtDetail();
            }
            else if (veeType.equals("2")) {
                veeDetail = record.getVeeEditDetail();
            }
        }
        return veeDetail;
    }

    /**
     * @description 根据接收的vee预估数据，更新json
     * @param content，传入的json的content值 
     * @param veerdetail，数据库中的veeResultDetail的值
     * @return 返回更新后的，要保存的veeResultDetail
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
     * @param veerdetail，数据库中的veeResultDetail的值
     * @return 返回更新后的，要保存的veeResultDetail
     */
    private JSONObject analyEditValue(JSONObject content,String estimateDetail) {
        //数据库中保存的格式：{"data":[{"VEE00012":"0.20"},{"VEE60112":"5.20"}]}
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

    private String removeCmdFromVeeDetail(String cmd,String timeType,String dataType,String veeType,String deviceId,String time) {
        String veeDetail=loadVeeResult(timeType,dataType,veeType,deviceId,time);
        JSONObject rootJson = new JSONObject();
        JSONArray json = new JSONArray();
        if ((veeDetail == null) || (veeDetail.equals(""))) {
            return "";
        }
        JSONObject veer = JSONObject.parseObject(veeDetail);
        JSONArray veerdata = veer.getJSONArray("data");
        for(int i = 0; i < veerdata.size(); i++){
            JSONObject object = veerdata.getJSONObject(i);
            Set<String> keys = object.keySet();
            if (!keys.contains(cmd) && (keys.size() >= 1)){
                json.add(object);   
            }
        }
        if (json.size() > 0) {
            rootJson.put("data",json);
            return rootJson.toJSONString();
        }
        else {
            return "";
        }
    }
    
    private void updateFreezingData(String timeType,String dataType,String deviceId,String time,
            String cmd,String value,String verifyDetail,String estimateDetail,String editDetail) {
        Double ptctValue = Double.parseDouble(value);
        if (timeType.equals("0")) {
            MdmDayFreezing record = new MdmDayFreezing();
            record.setDeviceId(deviceId);
            record.setDataPeriod(time);
            record.setFzDataType(dataType);
            record.setVeeResultDetail(verifyDetail);
            record.setVeeEstmtDetail(estimateDetail);
            record.setVeeEditDetail(editDetail);
            switch (cmd) {
                case "03000002"://总有功电量
                    record.setValueFreezing(ptctValue);//VALUE_FREEZING，
                    record.setPtctValueFreezing(ptctValue);//PTCT_VALUE_FREEZING，
                    break;          
                case "03001002"://总有功电量-T1
                    record.setValueFreezingT1(ptctValue);//VALUE_FREEZING_T1
                    record.setPtctValueFreezingT1(ptctValue);//PTCT_VALUE_FREEZING_T1
                    break;          
                case "03002002"://总有功电量-T2
                    record.setValueFreezingT2(ptctValue);//VALUE_FREEZING_T2
                    record.setPtctValueFreezingT2(ptctValue);//PTCT_VALUE_FREEZING_T2
                    break;          
                case "03003002"://总有功电量-T3
                    record.setValueFreezingT3(ptctValue);//VALUE_FREEZING_T3
                    record.setPtctValueFreezingT3(ptctValue);//PTCT_VALUE_FREEZING_T3
                    break;          
                case "03004002"://总有功电量-T4
                    record.setValueFreezingT4(ptctValue);//VALUE_FREEZING_T4
                    record.setPtctValueFreezingT4(ptctValue);//PTCT_VALUE_FREEZING_T4
                    break;          
                case "03005002"://总有功电量-T5
                    record.setValueFreezingT5(ptctValue);//VALUE_FREEZING_T5
                    record.setPtctValueFreezingT5(ptctValue);//PTCT_VALUE_FREEZING_T5
                    break;          
                case "03006002"://总有功电量-T6
                    record.setValueFreezingT6(ptctValue);//VALUE_FREEZING_T6
                    record.setPtctValueFreezingT6(ptctValue);//PTCT_VALUE_FREEZING_T6
                    break;          
                case "03007002"://总有功电量-T7
                    record.setValueFreezingT7(ptctValue);//VALUE_FREEZING_T7
                    record.setPtctValueFreezingT7(ptctValue);//PTCT_VALUE_FREEZING_T7
                    break;          
                case "03008002"://总有功电量-T8
                    record.setValueFreezingT8(ptctValue);//VALUE_FREEZING_T8
                    record.setPtctValueFreezingT8(ptctValue);//PTCT_VALUE_FREEZING_T8
                    break;          
                case "03030002"://正向总有功电量
                case "03030012"://正向有功使用电量
                    record.setKwhPa(ptctValue);//KWH_PA  PA=正向有功
                    record.setKwhPaPtct(ptctValue);//KWH_PA_PTCT  PA=正向有功
                    break;          
                case "03031002"://正向有功电量-T1
                case "03031012"://正向有功使用电量-T1
                    record.setKwhPaT1(ptctValue);//KWH_PA_T1
                    record.setKwhPaPtctT1(ptctValue);//KWH_PA_PTCT_T1
                    break;          
                case "03032002"://正向有功电量-T2
                case "03032012"://正向有功使用电量-T2
                    record.setKwhPaT2(ptctValue);//KWH_PA_T1
                    record.setKwhPaPtctT2(ptctValue);//KWH_PA_PTCT_T2
                    break;          
                case "03033002"://正向有功电量-T3
                case "03033012"://正向有功使用电量-T3
                    record.setKwhPaT3(ptctValue);//KWH_PA_T1
                    record.setKwhPaPtctT3(ptctValue);//KWH_PA_PTCT_T3
                    break;          
                case "03034002"://正向有功电量-T4
                case "03034012"://正向有功使用电量-T4
                    record.setKwhPaT4(ptctValue);//KWH_PA_T1
                    record.setKwhPaPtctT4(ptctValue);//KWH_PA_PTCT_T4
                    break;          
                case "03040002"://反向总有功电量
                case "03040012"://反向有功使用电量
                    record.setKwhRa(ptctValue);//KWH_RA  RA=反向有功
                    record.setKwhRaPtct(ptctValue);//KWH_RA_PTCT  RA=反向有功
                    break;          
                case "03041002"://反向总有功电量-T1
                case "03041012"://反向有功使用电量-T1
                    record.setKwhRaT1(ptctValue);//KWH_RA_T1
                    record.setKwhRaPtctT1(ptctValue);//KWH_RA_PTCT_T1
                    break;          
                case "03042002"://反向总有功电量-T2
                case "03042012"://反向有功使用电量-T2
                    record.setKwhRaT2(ptctValue);//KWH_RA_T1
                    record.setKwhRaPtctT2(ptctValue);//KWH_RA_PTCT_T2
                    break;          
                case "03043002"://反向总有功电量-T3
                case "03043012"://反向有功使用电量-T3
                    record.setKwhRaT3(ptctValue);//KWH_RA_T1
                    record.setKwhRaPtctT3(ptctValue);//KWH_RA_PTCT_T3
                    break;          
                case "03044002"://反向总有功电量-T4
                case "03044012"://反向有功使用电量-T4
                    record.setKwhRaT4(ptctValue);//KWH_RA_T1
                    record.setKwhRaPtctT4(ptctValue);//KWH_RA_PTCT_T4
                    break;          
                case "03050002"://正向总无功冻结电量
                case "03050012"://正向无功使用电量
                    record.setKwhPr(ptctValue);//KWH_PR  PR=正向无功
                    record.setKwhPrPtct(ptctValue);//KWH_PR_PTCT  PR=正向无功
                    break;          
                case "03051002"://正向总无功冻结电量-T1
                case "03051012"://正向无功使用电量-T1
                    record.setKwhPrT1(ptctValue);//KWH_PR_T1
                    record.setKwhPrPtctT1(ptctValue);//KWH_PR_PTCT_T1
                    break;          
                case "03052002"://正向总无功冻结电量-T2
                case "03052012"://正向无功使用电量-T2
                    record.setKwhPrT2(ptctValue);//KWH_PR_T1
                    record.setKwhPrPtctT2(ptctValue);//KWH_Pr_PTCT_T2
                    break;          
                case "03053002"://正赂总无功冻结电量-T3
                case "03053012"://正向无功使用电量-T3
                    record.setKwhPrT3(ptctValue);//KWH_PR_T3
                    record.setKwhPrPtctT3(ptctValue);//KWH_PR_PTCT_T3
                    break;          
                case "03054002"://正向总无功冻结电量-T4
                case "03054012"://正向无功使用电量-T4
                    record.setKwhPrT4(ptctValue);//KWH_PR_T4
                    record.setKwhPrPtctT4(ptctValue);//KWH_PR_PTCT_T4
                    break;          
                case "03060002"://反向总无功电量
                case "03060012"://反向无功使用电量
                    record.setKwhRr(ptctValue);//KWH_RR  RR=反向无功
                    record.setKwhRrPtct(ptctValue);//KWH_RR_PTCT  RR=反向无功
                    break;          
                case "03061002"://反向总无功电量-T1
                case "03061012"://反向无功使用电量-T1
                    record.setKwhRrT1(ptctValue);//KWH_RR_T1
                    record.setKwhRrPtctT1(ptctValue);//KWH_RR_PTCT_T1
                    break;          
                case "03062002"://反向总无功电量-T2
                case "03062012"://反向无功使用电量-T2
                    record.setKwhRrT2(ptctValue);//KWH_RR_T1
                    record.setKwhRrPtctT2(ptctValue);//KWH_RR_PTCT_T2
                    break;          
                case "03063002"://反向总无功电量-T3
                case "03063012"://反向无功使用电量-T3
                    record.setKwhRrT3(ptctValue);//KWH_RR_T1
                    record.setKwhRrPtctT3(ptctValue);//KWH_RR_PTCT_T3
                    break;          
                case "03064002"://反向总无功电量-T4
                case "03064012"://反向无功使用电量-T4
                    record.setKwhRrT4(ptctValue);//KWH_RR_T1
                    record.setKwhRrPtctT4(ptctValue);//KWH_RR_PTCT_T4
                    break;          
            }   
            record.setCdate(new Date());
            if (mdmDayFreezingDao.updateVeeByPrimaryKeySelective(record) < 1) {
                mdmDayFreezingDao.insertSelective(record);
            }
        }
        else if (timeType.equals("1")) {
            MdmMonthFreezing record = new MdmMonthFreezing();
            record.setDeviceId(deviceId);
            record.setDataPeriod(time);
            record.setFzDataType(dataType);
            record.setVeeResultDetail(verifyDetail);
            record.setVeeEstmtDetail(estimateDetail);
            record.setVeeEditDetail(editDetail);
            switch (cmd) {
            case "03000102"://上月总有功电量
                record.setValueFreezing(ptctValue);//VALUE_FREEZING，
                record.setPtctValueFreezing(ptctValue);//PTCT_VALUE_FREEZING，
                break;          
            case "03001102"://上月总有功电量-T1
                record.setValueFreezingT1(ptctValue);//VALUE_FREEZING_T1
                record.setPtctValueFreezingT1(ptctValue);//PTCT_VALUE_FREEZING_T1
                break;          
            case "03002102"://上月总有功电量-T2
                record.setValueFreezingT2(ptctValue);//VALUE_FREEZING_T2
                record.setPtctValueFreezingT2(ptctValue);//PTCT_VALUE_FREEZING_T2
                break;          
            case "03003102"://上月总有功电量-T3
                record.setValueFreezingT3(ptctValue);//VALUE_FREEZING_T3
                record.setPtctValueFreezingT3(ptctValue);//PTCT_VALUE_FREEZING_T3
                break;          
            case "03004102"://上月总有功电量-T4
                record.setValueFreezingT4(ptctValue);//VALUE_FREEZING_T4
                record.setPtctValueFreezingT4(ptctValue);//PTCT_VALUE_FREEZING_T4
                break;          
            case "03005102"://上月总有功电量-T5
                record.setValueFreezingT5(ptctValue);//VALUE_FREEZING_T5
                record.setPtctValueFreezingT5(ptctValue);//PTCT_VALUE_FREEZING_T5
                break;          
            case "03006102"://上月总有功电量-T6
                record.setValueFreezingT6(ptctValue);//VALUE_FREEZING_T6
                record.setPtctValueFreezingT6(ptctValue);//PTCT_VALUE_FREEZING_T6
                break;          
            case "03007102"://上月总有功电量-T7
                record.setValueFreezingT7(ptctValue);//VALUE_FREEZING_T7
                record.setPtctValueFreezingT7(ptctValue);//PTCT_VALUE_FREEZING_T7
                break;          
            case "03008102"://上月总有功电量-T8
                record.setValueFreezingT8(ptctValue);//VALUE_FREEZING_T8
                record.setPtctValueFreezingT8(ptctValue);//PTCT_VALUE_FREEZING_T8
                break;          
            case "03030102"://上月正向有功电量
            case "03030112"://上月正向有功使用电量
                record.setKwhPa(ptctValue);//KWH_PA
                record.setKwhPaPtct(ptctValue);//KWH_PA_PTCT
                break;          
            case "03031102"://上月正向有功电量-T1
            case "03031112"://上月正向有功使用电量-T1
                record.setKwhPaT1(ptctValue);//KWH_PA_T1
                record.setKwhPaPtctT1(ptctValue);//KWH_PA_PTCT_T1
                break;          
            case "03032102"://上月正向有功电量-T2
            case "03032112"://上月正向有功使用电量-T2
                record.setKwhPaT2(ptctValue);//KWH_PA_T2
                record.setKwhPaPtctT2(ptctValue);//KWH_PA_PTCT_T2
                break;          
            case "03033102"://上月正向有功电量-T3
            case "03033112"://上月正向有功使用电量-T3
                record.setKwhPaT3(ptctValue);//KWH_PA_T3
                record.setKwhPaPtctT3(ptctValue);//KWH_PA_PTCT_T3
                break;          
            case "03034102"://上月正向有功电量-T4
            case "03034112"://上月正向有功使用电量-T4
                record.setKwhPaT4(ptctValue);//KWH_PA_T4
                record.setKwhPaPtctT4(ptctValue);//KWH_PA_PTCT_T4
                break;          
            case "03040102"://上月反向总有功电量
            case "03040112"://上月反向有功使用电量
                record.setKwhRa(ptctValue);//KWH_RA
                record.setKwhRaPtct(ptctValue);//KWH_RA_PTCT
                break;          
            case "03041102"://上月反向总有功电量-T1
            case "03041112"://上月反向有功使用电量-T1
                record.setKwhRaT1(ptctValue);//KWH_RA_T1
                record.setKwhRaPtctT1(ptctValue);//KWH_RA_PTCT_T1
                break;          
            case "03042102"://上月反向总有功电量-T2
            case "03042112"://上月反向有功使用电量-T2
                record.setKwhRaT2(ptctValue);//KWH_RA_T2
                record.setKwhRaPtctT2(ptctValue);//KWH_RA_PTCT_T2
                break;          
            case "03043102"://上月反向总有功电量-T3
            case "03043112"://上月反向有功使用电量-T3
                record.setKwhRaT3(ptctValue);//KWH_RA_T3
                record.setKwhRaPtctT3(ptctValue);//KWH_RA_PTCT_T3
                break;          
            case "03044102"://上月反向总有功电量-T4
            case "03044112"://上月反向有功使用电量-T4
                record.setKwhRaT4(ptctValue);//KWH_RA_T4
                record.setKwhRaPtctT4(ptctValue);//KWH_RA_PTCT_T4
                break;          
            case "03050102"://上月正向总无功冻结电量
            case "03050112"://上月正向无功使用电量
                record.setKwhPr(ptctValue);//KWH_PR
                record.setKwhPrPtct(ptctValue);//KWH_PR_PTCT
                break;          
            case "03051102"://上月正向总无功冻结电量-T1
            case "03051112"://上月正向无功使用电量-T1
                record.setKwhPrT1(ptctValue);//KWH_PR_T1
                record.setKwhPrPtctT1(ptctValue);//KWH_PR_PTCT_T1
                break;          
            case "03052102"://上月正向总无功冻结电量-T2
            case "03052112"://上月正向无功使用电量-T2
                record.setKwhPrT2(ptctValue);//KWH_PR_T2
                record.setKwhPrPtctT2(ptctValue);//KWH_PR_PTCT_T2
                break;          
            case "03053102"://上月正赂总无功冻结电量-T3
            case "03053112"://上月正向无功使用电量-T3
                record.setKwhPrT3(ptctValue);//KWH_PR_T3
                record.setKwhPrPtctT3(ptctValue);//KWH_PR_PTCT_T3
                break;          
            case "03054102"://上月正向总无功冻结电量-T4
            case "03054112"://上月正向无功使用电量-T4
                record.setKwhPrT4(ptctValue);//KWH_PR_T4
                record.setKwhPrPtctT4(ptctValue);//KWH_PR_PTCT_T4
                break;          
            case "03060102"://上月反向总无功电量
            case "03060112"://上月反向无功使用电量
                record.setKwhRr(ptctValue);//KWH_RR
                record.setKwhRrPtct(ptctValue);//KWH_RR_PTCT
                break;          
            case "03061102"://上月反向总无功电量-T1
            case "03061112"://上月反向无功使用电量-T1
                record.setKwhRrT1(ptctValue);//KWH_RR_T1
                record.setKwhRrPtctT1(ptctValue);//KWH_RR_PTCT_T1
                break;          
            case "03062102"://上月反向总无功电量-T2
            case "03062112"://上月反向无功使用电量-T2
                record.setKwhRrT2(ptctValue);//KWH_RR_T2
                record.setKwhRrPtctT2(ptctValue);//KWH_RR_PTCT_T2
                break;          
            case "03063102"://上月反向总无功电量-T3
            case "03063112"://上月反向无功使用电量-T3
                record.setKwhRrT3(ptctValue);//KWH_RR_T3
                record.setKwhRrPtctT3(ptctValue);//KWH_RR_PTCT_T3
                break;          
            case "03064102"://上月反向总无功电量-T4
            case "03064112"://上月反向无功使用电量-T4
                record.setKwhRrT4(ptctValue);//KWH_RR_T4
                record.setKwhRrPtctT4(ptctValue);//KWH_RR_PTCT_T4
                break;          
            }   
            record.setCdate(new Date());
            if (mdmMonthFreezingDao.updateVeeByPrimaryKeySelective(record) < 1) {
                mdmMonthFreezingDao.insertSelective(record);
            }
        }
    }
    
}
