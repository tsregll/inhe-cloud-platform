package com.inhe.mdm.service;

import java.math.BigInteger;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.rocketmq.ProducerNode;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmDayFreezingDao;
import com.inhe.mdm.dao.MdmMonthFreezingDao;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDayFreezing;
import com.inhe.mdm.model.MdmMonthFreezing;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.utils.DateUtil;

/**
 * @Description : 存储日月冻结数据服务
 * @author : guhf
 * @Date : 2020/12/30
 */
@Component
public class MdmSaveFreezingService {
    
    private static final Logger logger = LoggerFactory.getLogger(MdmSaveFreezingService.class);
    
    @Autowired
    ProducerNode producerNode;
    
    @Autowired
    SendCmdProxy sendCmdProxy;
    
    @Autowired
    MdmDayFreezingDao mdmDayFreezingDao;
    
    @Autowired
    MdmMonthFreezingDao mdmMonthFreezingDao;
    
    @Autowired
    MdmAmDeviceDao mdmAmDeviceDao;
    
    public synchronized void processCmd(JSONObject content){
        String cmdCode = content.getString("cmdCode");
        String fieldId = content.getString("fieldId");
        String deviceId = content.getString("devId");
        MdmAmDevice device = mdmAmDeviceDao.selectByPrimaryKey(deviceId);
        if(device == null) {
            return;
        }
        Date readTime = null;
        String period = "";
        String kwhType = MdmUtil.checkKwhType(fieldId);
        String readType = "-";
        if(MdmUtil.contains(MdmUtil.METER_DAY_TOTAL_STAND, fieldId) || MdmUtil.contains(MdmUtil.METER_DAY_USE_STAND, fieldId)){
            try {
                Integer lastDays = Integer.parseInt(StringUtils.right(fieldId, 2)) - 1;
                readTime =  new Date(content.getLong("dataTime"));
                period = DateUtil.dateToString(DateUtil.rollByDay(readTime, -lastDays), DateUtil.DATE_WITH_NOTHING);
                Double kwh = Double.parseDouble(content.getString("value"));
                MdmDayFreezing mdmDayFreezing = getMdmDayFreezingModel(device,period,kwhType,readType,cmdCode,readTime);
                this.setDayForwardAndReverse(fieldId,kwh,mdmDayFreezing);
                this.updateDayFreezing(mdmDayFreezing);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        else
        if(MdmUtil.isDayForwardAndReverse(fieldId)){
            try {
                readTime =  new Date(content.getLong("dataTime"));
                period = DateUtil.dateToString(DateUtil.rollByDay(readTime, -1), DateUtil.DATE_WITH_NOTHING);
                Double kwh = Double.parseDouble(content.getString("value"));
                MdmDayFreezing mdmDayFreezing = getMdmDayFreezingModel(device,period,kwhType,readType,cmdCode,readTime);
                this.setDayForwardAndReverse(fieldId,kwh,mdmDayFreezing);
                this.updateDayFreezing(mdmDayFreezing);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        else 
        if(MdmUtil.isMonthForwardAndReverse(fieldId)){
            try {
                String key = StringUtils.mid(fieldId, 5, 1);
                Integer lastMonths = Integer.parseInt(new BigInteger(key, 16).toString(10));
                readTime =  new Date(content.getLong("dataTime"));
                period = DateUtil.dateToString(DateUtil.rollByMonth(readTime, -lastMonths), DateUtil.DATE_WITHOUT_DAY);
                Double kwh = Double.parseDouble(content.getString("value"));
                MdmMonthFreezing mdmMonthFreezing = getMdmMonthFreezingModel(device,period,kwhType,readType,cmdCode,readTime);
                this.setMonthForwardAndReverse(fieldId,kwh,mdmMonthFreezing);
                this.updateMonthFreezing(mdmMonthFreezing);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        else 
        if(MdmUtil.isMonthField(fieldId)){
            try {
                readTime =  new Date(content.getLong("dataTime"));
                period = DateUtil.dateToString(DateUtil.rollByMonth(readTime, -1), DateUtil.DATE_WITHOUT_DAY);
                Double kwh = Double.parseDouble(content.getString("value"));
                MdmMonthFreezing mdmMonthFreezing = getMdmMonthFreezingModel(device,period,kwhType,readType,cmdCode,readTime);
                this.setMonthForwardAndReverse(fieldId,kwh,mdmMonthFreezing);
                this.updateMonthFreezing(mdmMonthFreezing);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        
        if(MdmUtil.isDayField(fieldId) || MdmUtil.isMonthField(fieldId))
        {
            String dataType = MdmUtil.checkDataType(fieldId);
            sendCmdProxy.send0112( 
                      "0", //0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
                      content.getString("devId"),
                      dataType,//amr_read的DATA_TYPE 数据类型： 1：普通数据  4：日冻结 6：月冻结   2：曲线数据
                      content.getString("dataTime"),
                      content.getString("value"),
                      content.getString("cmdCode"),
                      "0", //0：VEE校验    1：VEE预估
                      "0",  //数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
                      fieldId
                      );
        }
        
    }
    
    private MdmDayFreezing getMdmDayFreezingModel(MdmAmDevice device, String period, String kwhType, 
            String readType, String cmdCode, Date readTime) {
        MdmDayFreezing mdmDayFreezing = new MdmDayFreezing();
        mdmDayFreezing.setDataType(2);
        mdmDayFreezing.setDeviceId(device.getId());
        mdmDayFreezing.setDataPeriod(period);
        mdmDayFreezing.setFzDataType(kwhType);
        mdmDayFreezing.setDeviceNum(device.getDeviceNum());
        mdmDayFreezing.setOrgId(device.getOrgId());
        mdmDayFreezing.setDeptId(device.getDeptId());
        mdmDayFreezing.setReadType(readType);
        mdmDayFreezing.setReadStatus("");
        mdmDayFreezing.setScTimes((short)0);
        mdmDayFreezing.setCmdCode(cmdCode);
        mdmDayFreezing.setOpStatus("2");
        mdmDayFreezing.setReadTime(readTime);
        return mdmDayFreezing;
    }
    
    private MdmMonthFreezing getMdmMonthFreezingModel(MdmAmDevice device, String period, String kwhType, 
            String readType, String cmdCode, Date readTime) {
        MdmMonthFreezing mdmMonthFreezing = new MdmMonthFreezing();
        mdmMonthFreezing.setDataType(2);
        mdmMonthFreezing.setDeviceId(device.getId());
        mdmMonthFreezing.setDataPeriod(period);
        mdmMonthFreezing.setFzDataType(kwhType);
        mdmMonthFreezing.setDeviceNum(device.getDeviceNum());
        mdmMonthFreezing.setOrgId(device.getOrgId());
        mdmMonthFreezing.setDeptId(device.getDeptId());
        mdmMonthFreezing.setReadType(readType);
        mdmMonthFreezing.setReadStatus("");
        mdmMonthFreezing.setScTimes((short)0);
        mdmMonthFreezing.setCmdCode(cmdCode);
        mdmMonthFreezing.setOpStatus("2");
        mdmMonthFreezing.setReadTime(readTime);
        return mdmMonthFreezing;
    }
    
    /**
     * @description 更新日数据
     * @param paramFreezing
     */
    private void updateDayFreezing(MdmDayFreezing paramFreezing){
        MdmDayFreezing mdmDayFreezing = mdmDayFreezingDao.selectByPrimaryKey(paramFreezing);
        if(mdmDayFreezing == null){
            mdmDayFreezingDao.insertSelective(paramFreezing);
        }else {
            mdmDayFreezingDao.updateByPrimaryKeySelective(paramFreezing);
        }
    }
    
    /**
     * @description 更新月数据
     * @param paramFreezing
     */
    private void updateMonthFreezing(MdmMonthFreezing paramFreezing){
        MdmMonthFreezing mdmMonthFreezing = mdmMonthFreezingDao.selectByPrimaryKey(paramFreezing);
        if(mdmMonthFreezing == null){
            mdmMonthFreezingDao.insertSelective(paramFreezing);
        }else {
            mdmMonthFreezingDao.updateByPrimaryKeySelective(paramFreezing);
        }
    }
    
    /**
     * @description 日冻结设置正反向有功、无功电量
     * @param fieldId
     * @param kwh
     * @param mdmDayFreezing
     */
    private void setDayForwardAndReverse(String fieldId, Double kwh, MdmDayFreezing mdmDayFreezing) {
        if("03000002".equals(fieldId) || "CTDC0001".equals(fieldId)){//总有功
            mdmDayFreezing.setValueFreezing(kwh);
            mdmDayFreezing.setPtctValueFreezing(kwh);
        }else if("03001002".equals(fieldId) || "CTDC1001".equals(fieldId)){//总有功电量-T1
            mdmDayFreezing.setValueFreezingT1(kwh);
            mdmDayFreezing.setPtctValueFreezingT1(kwh);
        }else if("03002002".equals(fieldId) || "CTDC2001".equals(fieldId)){//总有功电量-T2
            mdmDayFreezing.setValueFreezingT2(kwh);
            mdmDayFreezing.setPtctValueFreezingT2(kwh);
        }else if("03003002".equals(fieldId) || "CTDC3001".equals(fieldId)){//总有功电量-T3
            mdmDayFreezing.setValueFreezingT3(kwh);
            mdmDayFreezing.setPtctValueFreezingT3(kwh);
        }else if("03004002".equals(fieldId) || "CTDC4001".equals(fieldId)){//总有功电量-T4
            mdmDayFreezing.setValueFreezingT4(kwh);
            mdmDayFreezing.setPtctValueFreezingT4(kwh);
        }else if("03005002".equals(fieldId)){//总有功电量-T5
            mdmDayFreezing.setValueFreezingT5(kwh);
            mdmDayFreezing.setPtctValueFreezingT5(kwh);
        }else if("03006002".equals(fieldId)){//总有功电量-T6
            mdmDayFreezing.setValueFreezingT6(kwh);
            mdmDayFreezing.setPtctValueFreezingT6(kwh);
        }else if("03007002".equals(fieldId)){//总有功电量-T7
            mdmDayFreezing.setValueFreezingT7(kwh);
            mdmDayFreezing.setPtctValueFreezingT7(kwh);
        }else if("03008002".equals(fieldId)){//总有功电量-T8
            mdmDayFreezing.setValueFreezingT8(kwh);
            mdmDayFreezing.setPtctValueFreezingT8(kwh);
        }else if("03030002".equals(fieldId)){//正向有功
            mdmDayFreezing.setKwhPa(kwh);
            mdmDayFreezing.setKwhPaPtct(kwh);
        }else if("03031002".equals(fieldId)){//正向有功t1
            mdmDayFreezing.setKwhPaT1(kwh);
            mdmDayFreezing.setKwhPaPtctT1(kwh);
        }else if("03032002".equals(fieldId)){//正向有功t2
            mdmDayFreezing.setKwhPaT2(kwh);
            mdmDayFreezing.setKwhPaPtctT2(kwh);
        }else if("03033002".equals(fieldId)){//正向有功t3
            mdmDayFreezing.setKwhPaT3(kwh);
            mdmDayFreezing.setKwhPaPtctT3(kwh);
        }else if("03034002".equals(fieldId)){//正向有功t4
            mdmDayFreezing.setKwhPaT4(kwh);
            mdmDayFreezing.setKwhPaPtctT4(kwh);
        }else if("03040002".equals(fieldId)){//反向有功
            mdmDayFreezing.setKwhRa(kwh);
            mdmDayFreezing.setKwhRaPtct(kwh);
        }else if("03041002".equals(fieldId)){//反向有功t1
            mdmDayFreezing.setKwhRaT1(kwh);
            mdmDayFreezing.setKwhRaPtctT1(kwh);
        }else if("03042002".equals(fieldId)){//反向有功t2
            mdmDayFreezing.setKwhRaT2(kwh);
            mdmDayFreezing.setKwhRaPtctT2(kwh);
        }else if("03043002".equals(fieldId)){//反向有功t3
            mdmDayFreezing.setKwhRaT3(kwh);
            mdmDayFreezing.setKwhRaPtctT3(kwh);
        }else if("03044002".equals(fieldId)){//反向有功t4
            mdmDayFreezing.setKwhRaT4(kwh);
            mdmDayFreezing.setKwhRaPtctT4(kwh);
        }else if("03050002".equals(fieldId)){//正向无功
            mdmDayFreezing.setKwhPr(kwh);
            mdmDayFreezing.setKwhPrPtct(kwh);
        }else if("03051002".equals(fieldId)){//正向无功t1
            mdmDayFreezing.setKwhPrT1(kwh);
            mdmDayFreezing.setKwhPrPtctT1(kwh);
        }else if("03052002".equals(fieldId)){//正向无功t2
            mdmDayFreezing.setKwhPrT2(kwh);
            mdmDayFreezing.setKwhPrPtctT2(kwh);
        }else if("03053002".equals(fieldId)){//正向无功t3
            mdmDayFreezing.setKwhPrT3(kwh);
            mdmDayFreezing.setKwhPrPtctT3(kwh);
        }else if("03054002".equals(fieldId)){//正向无功t4
            mdmDayFreezing.setKwhPrT4(kwh);
            mdmDayFreezing.setKwhPrPtctT4(kwh);
        }else if("03060002".equals(fieldId)){//反向无功
            mdmDayFreezing.setKwhRr(kwh);
            mdmDayFreezing.setKwhRrPtct(kwh);
        }else if("03061002".equals(fieldId)){//反向无功t1
            mdmDayFreezing.setKwhRrT1(kwh);
            mdmDayFreezing.setKwhRrPtctT1(kwh);
        }else if("03062002".equals(fieldId)){//反向无功t2
            mdmDayFreezing.setKwhRrT2(kwh);
            mdmDayFreezing.setKwhRrPtctT2(kwh);
        }else if("03063002".equals(fieldId)){//反向无功t3
            mdmDayFreezing.setKwhRrT3(kwh);
            mdmDayFreezing.setKwhRrPtctT3(kwh);
        }else if("03064002".equals(fieldId)){//反向无功t4
            mdmDayFreezing.setKwhRrT4(kwh);
            mdmDayFreezing.setKwhRrPtctT4(kwh);
        }
    }
    
    /**
     * @description 月冻结设置正反向有功、无功电量
     * @param titles
     * @param row
     * @param mdmMonthFreezing
     */
    private void setMonthForwardAndReverse(String fieldId, Double kwh, MdmMonthFreezing mdmMonthFreezing) {
        if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND, fieldId)){//总有功 03000102
            mdmMonthFreezing.setValueFreezing(kwh);
            mdmMonthFreezing.setPtctValueFreezing(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T1, fieldId)){//总有功电量-T1 03001102
            mdmMonthFreezing.setValueFreezingT1(kwh);
            mdmMonthFreezing.setPtctValueFreezingT1(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T2, fieldId)){//总有功电量-T2 03002102
            mdmMonthFreezing.setValueFreezingT2(kwh);
            mdmMonthFreezing.setPtctValueFreezingT2(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T3, fieldId)){//总有功电量-T3 03003102
            mdmMonthFreezing.setValueFreezingT3(kwh);
            mdmMonthFreezing.setPtctValueFreezingT3(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T4, fieldId)){//总有功电量-T4 03004102
            mdmMonthFreezing.setValueFreezingT4(kwh);
            mdmMonthFreezing.setPtctValueFreezingT4(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T5, fieldId)){//总有功电量-T5 03005102
            mdmMonthFreezing.setValueFreezingT5(kwh);
            mdmMonthFreezing.setPtctValueFreezingT5(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T6, fieldId)){//总有功电量-T6 03006102
            mdmMonthFreezing.setValueFreezingT6(kwh);
            mdmMonthFreezing.setPtctValueFreezingT6(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T7, fieldId)){//总有功电量-T7 03007102
            mdmMonthFreezing.setValueFreezingT7(kwh);
            mdmMonthFreezing.setPtctValueFreezingT7(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_TOTAL_STAND_T8, fieldId)){//总有功电量-T8 03008102
            mdmMonthFreezing.setValueFreezingT8(kwh);
            mdmMonthFreezing.setPtctValueFreezingT8(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, fieldId)){//正向有功  03030102
            mdmMonthFreezing.setKwhPa(kwh);
            mdmMonthFreezing.setKwhPaPtct(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T1, fieldId)){//正向有功t1 03031102
            mdmMonthFreezing.setKwhPaT1(kwh);
            mdmMonthFreezing.setKwhPaPtctT1(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T2, fieldId)){//正向有功t2 03032102
            mdmMonthFreezing.setKwhPaT2(kwh);
            mdmMonthFreezing.setKwhPaPtctT2(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T3, fieldId)){//正向有功t3 03033102
            mdmMonthFreezing.setKwhPaT3(kwh);
            mdmMonthFreezing.setKwhPaPtctT3(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T4, fieldId)){//正向有功t4 03034102
            mdmMonthFreezing.setKwhPaT4(kwh);
            mdmMonthFreezing.setKwhPaPtctT4(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, fieldId)){//反向有功 03040102
            mdmMonthFreezing.setKwhRa(kwh);
            mdmMonthFreezing.setKwhRaPtct(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T1, fieldId)){//反向有功t1 03041102
            mdmMonthFreezing.setKwhRaT1(kwh);
            mdmMonthFreezing.setKwhRaPtctT1(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T2, fieldId)){//反向有功t2 03042102
            mdmMonthFreezing.setKwhRaT2(kwh);
            mdmMonthFreezing.setKwhRaPtctT2(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T3, fieldId)){//反向有功t3 03043102
            mdmMonthFreezing.setKwhRaT3(kwh);
            mdmMonthFreezing.setKwhRaPtctT3(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T4, fieldId)){//反向有功t4 03044102
            mdmMonthFreezing.setKwhRaT4(kwh);
            mdmMonthFreezing.setKwhRaPtctT4(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, fieldId)){//正向无功 03050102
            mdmMonthFreezing.setKwhPr(kwh);
            mdmMonthFreezing.setKwhPrPtct(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T1, fieldId)){//正向无功t1 03051102
            mdmMonthFreezing.setKwhPrT1(kwh);
            mdmMonthFreezing.setKwhPrPtctT1(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T2, fieldId)){//正向无功t2 03052102
            mdmMonthFreezing.setKwhPrT2(kwh);
            mdmMonthFreezing.setKwhPrPtctT2(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T3, fieldId)){//正向无功t3 03053102
            mdmMonthFreezing.setKwhPrT3(kwh);
            mdmMonthFreezing.setKwhPrPtctT3(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T4, fieldId)){//正向无功t4 03054102
            mdmMonthFreezing.setKwhPrT4(kwh);
            mdmMonthFreezing.setKwhPrPtctT4(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, fieldId)){//反向无功 03060102
            mdmMonthFreezing.setKwhRr(kwh);
            mdmMonthFreezing.setKwhRrPtct(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T1, fieldId)){//反向无功t1 03061102
            mdmMonthFreezing.setKwhRrT1(kwh);
            mdmMonthFreezing.setKwhRrPtctT1(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T2, fieldId)){//反向无功t2 03062102
            mdmMonthFreezing.setKwhRrT2(kwh);
            mdmMonthFreezing.setKwhRrPtctT2(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T3, fieldId)){//反向无功t3 03063102
            mdmMonthFreezing.setKwhRrT3(kwh);
            mdmMonthFreezing.setKwhRrPtctT3(kwh);
        }else if(MdmUtil.contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T4, fieldId)){//反向无功t4 03064102
            mdmMonthFreezing.setKwhRrT4(kwh);
            mdmMonthFreezing.setKwhRrPtctT4(kwh);
        }
        
    }
    
}
