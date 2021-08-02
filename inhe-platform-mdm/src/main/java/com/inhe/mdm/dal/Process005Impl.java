package com.inhe.mdm.dal;

import java.util.Date;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.dao.MdmAmDeviceChangeDao;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmMonthFreezingDao;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmAmDeviceChange;
import com.inhe.mdm.model.MdmMonthFreezing;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.utils.DateUtil;

/**
 * @description 月用电分析
 * @author guhf
 * @date 2021/01/21
 */
@Service
public class Process005Impl {
    
    private static final Logger logger = LoggerFactory.getLogger(Process005Impl.class);
	
	@Autowired
	SqlSessionTemplate template;
	
	@Autowired
	MdmAmDeviceDao mdmAmDeviceDao;
	
	@Autowired
	MdmAaKwhDetailDao mdmAaKwhDetailDao;
	
	@Autowired
	MdmMonthFreezingDao mdmMonthFreezingDao;
	
	@Autowired
	MdmAmDeviceChangeDao mdmAmDeviceChangeDao;
	
	@Autowired
	Process004Impl process004Impl;
	
	@Autowired
	SendCmdProxy sendCmdProxy;
	
	public void execute(JSONObject jobj) throws Exception{
	    Date startDate = DateUtil.rollByMonth(jobj.getDate("data_time"), -1);
		String lastMonth1F1 = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
		String lastMonth1F2 = DateUtil.dateToString(startDate, DateUtil.DATE_WITHOUT_DAY);
		String lastMonth2F2 = DateUtil.dateToString(DateUtil.rollByMonth(startDate, -1), DateUtil.DATE_WITHOUT_DAY);
		logger.info("Process005-start-"+lastMonth1F1);
		
		template.select("com.inhe.mdm.dao.MdmAmDeviceDao.selectByType", "0", new ResultHandler<Object>(){
			@Override
			public void handleResult(ResultContext<?> arg0) {
			    MdmAaKwhDetail mdmAaKwhDetail = new MdmAaKwhDetail();
		        mdmAaKwhDetail.setTimeType("1");
		        mdmAaKwhDetail.setNowTime(lastMonth1F1);
				mdmAaKwhDetail.setTotalKwhStart(0.0);
				mdmAaKwhDetail.setTotalKwhEnd(0.0);
				mdmAaKwhDetail.setTotalKwh(0.00);
				MdmAmDevice mdmAmDevice = (MdmAmDevice) arg0.getResultObject();
				try {
					mdmAaKwhDetail.setDeviceId(mdmAmDevice.getId());
					mdmAaKwhDetail.setRefCode(mdmAmDevice.getRefId());
					mdmAaKwhDetail.setOrgId(mdmAmDevice.getOrgId());
                    mdmAaKwhDetail.setDeptId(mdmAmDevice.getDeptId());
                    
                    mdmAaKwhDetail.setLineId(mdmAmDevice.getLineId()==null||"".equals(mdmAmDevice.getLineId())?"-":mdmAmDevice.getLineId());
                    mdmAaKwhDetail.setTmId(mdmAmDevice.getTmId()==null||"".equals(mdmAmDevice.getTmId())?"-":mdmAmDevice.getTmId());
                    mdmAaKwhDetail.setDeviceType(mdmAmDevice.getType()==null||"".equals(mdmAmDevice.getType())?"-":mdmAmDevice.getType());
                    mdmAaKwhDetail.setCstId(mdmAmDevice.getOrgId());
                    mdmAaKwhDetail.setCstName(mdmAmDevice.getDescription());
                    mdmAaKwhDetail.setMeasType(mdmAmDevice.getMeterType()==null||"".equals(mdmAmDevice.getMeterType())?"-":mdmAmDevice.getMeterType());
                    mdmAaKwhDetail.setDeviceNum(mdmAmDevice.getDeviceNum());
                    mdmAaKwhDetail.setMeterSort(mdmAmDevice.getMeterSort());
					
					MdmMonthFreezing mdmMonthFreezing1 = new MdmMonthFreezing();
					mdmMonthFreezing1.setDataType(2);
                    mdmMonthFreezing1.setDataPeriod(lastMonth1F2);
                    mdmMonthFreezing1.setDeviceId(mdmAmDevice.getId());
                    mdmMonthFreezing1 = mdmMonthFreezingDao.selectByPrimaryKey(mdmMonthFreezing1);
                    if(mdmMonthFreezing1 == null) {
                        mdmAaKwhDetail.setActive("N");
                        throw new InheExceptionBase(-100000, "can not find end kwh.");
                    }
                    
                    MdmMonthFreezing mdmMonthFreezing2 = new MdmMonthFreezing();
                    mdmMonthFreezing2.setDataType(2);
                    mdmMonthFreezing2.setDataPeriod(lastMonth2F2);
                    mdmMonthFreezing2.setDeviceId(mdmAmDevice.getId());
                    mdmMonthFreezing2 = mdmMonthFreezingDao.selectByPrimaryKey(mdmMonthFreezing2);
                    if(mdmMonthFreezing2 == null || !"1".equals(mdmMonthFreezing2.getFzDataType())) {
                        mdmAaKwhDetail.setActive("N");
                        throw new InheExceptionBase(-100000, "can not find start kwh.");
                    }
                    
                    //检查有无换表
                    MdmAmDeviceChange paramChange0 = new MdmAmDeviceChange();
                    paramChange0.setNewDevicenum(mdmAmDevice.getDeviceNum());
                    paramChange0.setMode("0"); //0：更换       1：拆除
                    MdmAmDeviceChange newDeviceChange = mdmAmDeviceChangeDao.selectByDeviceNum(paramChange0);
                    //检查有无拆表
                    MdmAmDeviceChange paramChange1 = new MdmAmDeviceChange();
                    paramChange1.setOldDevicenum(mdmAmDevice.getDeviceNum());
                    paramChange1.setMode("1"); //0：更换       1：拆除
                    MdmAmDeviceChange oldDeviceChange = mdmAmDeviceChangeDao.selectByDeviceNum(paramChange1);
                    
                    if(newDeviceChange != null) { //有换表
                        MdmMonthFreezing oldMdmMonthFreezing2 = new MdmMonthFreezing();
                        oldMdmMonthFreezing2.setDataPeriod(lastMonth2F2);
                        oldMdmMonthFreezing2.setDeviceNum(newDeviceChange.getOldDevicenum());
                        oldMdmMonthFreezing2 = mdmMonthFreezingDao.selectByDeviceNum(oldMdmMonthFreezing2);
                        if(oldMdmMonthFreezing2 == null || !"1".equals(oldMdmMonthFreezing2.getFzDataType())) {
                            mdmAaKwhDetail.setActive("N");
                            throw new InheExceptionBase(-100000, "can not find start kwh.");
                        }
                        if("1".equals(mdmMonthFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                        	mdmAaKwhDetail.setKwhPaPtctStart(mdmMonthFreezing1.getKwhPaPtct());
                        	mdmAaKwhDetail.setKwhPaPtctEnd(mdmMonthFreezing2.getKwhPaPtct());
                        	mdmAaKwhDetail.setKwhPrPtctStart(mdmMonthFreezing1.getKwhPrPtct());
                        	mdmAaKwhDetail.setKwhPrPtctEnd(mdmMonthFreezing2.getKwhPrPtct());
                        	mdmAaKwhDetail.setKwhRaPtctStart(mdmMonthFreezing1.getKwhRaPtct());
                        	mdmAaKwhDetail.setKwhRaPtctEnd(mdmMonthFreezing2.getKwhRaPtct());
                        	mdmAaKwhDetail.setKwhRrPtctStart(mdmMonthFreezing1.getKwhRrPtct());
                        	mdmAaKwhDetail.setKwhRrPtctEnd(mdmMonthFreezing2.getKwhRrPtct());
                            mdmAaKwhDetail.setTotalKwhEnd(mdmMonthFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwhStart(mdmMonthFreezing2.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwh(
                                    newDeviceChange.getOldDevicenumKwhEnd()-oldMdmMonthFreezing2.getPtctValueFreezing()
                                    +mdmMonthFreezing1.getPtctValueFreezing()-newDeviceChange.getNewDevicenumKwhStart()
                                    );
                        }
                        else
                        if("2".equals(mdmMonthFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwhStart(0.0);
                            mdmAaKwhDetail.setTotalKwhEnd(0.0);
                            mdmAaKwhDetail.setTotalKwh(
                                    newDeviceChange.getOldDevicenumKwhEnd()-oldMdmMonthFreezing2.getPtctValueFreezing()
                                    +mdmMonthFreezing1.getPtctValueFreezing()
                                    );
                        }
                    }
                    else 
                    if(oldDeviceChange != null) { //有拆表
                        if("1".equals(mdmMonthFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                            mdmAaKwhDetail.setTotalKwhEnd(oldDeviceChange.getOldDevicenumKwhEnd());
                            mdmAaKwhDetail.setTotalKwhStart(mdmMonthFreezing2.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwh(oldDeviceChange.getOldDevicenumKwhEnd()-mdmMonthFreezing2.getPtctValueFreezing());
                        }
                        else
                        if("2".equals(mdmMonthFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwhStart(0.0);
                            mdmAaKwhDetail.setTotalKwhEnd(0.0);
                            mdmAaKwhDetail.setTotalKwh(mdmMonthFreezing1.getPtctValueFreezing());
                        }
                    }
                    else { //既无换表，也无拆表
                        if("1".equals(mdmMonthFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                        	mdmAaKwhDetail.setKwhPaPtctStart(mdmMonthFreezing2.getKwhPaPtct());//03030002_S
                        	mdmAaKwhDetail.setKwhPaPtctEnd(mdmMonthFreezing1.getKwhPaPtct());  //03030002_E
                        	mdmAaKwhDetail.setKwhPrPtctStart(mdmMonthFreezing2.getKwhPrPtct());//03050002_S
                        	mdmAaKwhDetail.setKwhPrPtctEnd(mdmMonthFreezing1.getKwhPrPtct());  //03050002_E
                        	mdmAaKwhDetail.setKwhRaPtctStart(mdmMonthFreezing2.getKwhRaPtct());//03040002_S
                        	mdmAaKwhDetail.setKwhRaPtctEnd(mdmMonthFreezing1.getKwhRaPtct());  //03040002_E
                        	mdmAaKwhDetail.setKwhRrPtctStart(mdmMonthFreezing2.getKwhRrPtct());//03060002_S
                        	mdmAaKwhDetail.setKwhRrPtctEnd(mdmMonthFreezing1.getKwhRrPtct());  //03060002_E
                        	
                            mdmAaKwhDetail.setTotalKwhEnd(mdmMonthFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwhStart(mdmMonthFreezing2.getPtctValueFreezing());
                            if(mdmMonthFreezing1.getPtctValueFreezing()!=null && mdmMonthFreezing2.getPtctValueFreezing()!=null) {
                                mdmAaKwhDetail.setTotalKwh(mdmMonthFreezing1.getPtctValueFreezing()-mdmMonthFreezing2.getPtctValueFreezing());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT1()!=null && mdmMonthFreezing2.getPtctValueFreezingT1()!=null) {
                                mdmAaKwhDetail.setKwhT1(mdmMonthFreezing1.getPtctValueFreezingT1()-mdmMonthFreezing2.getPtctValueFreezingT1());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT2()!=null && mdmMonthFreezing2.getPtctValueFreezingT2()!=null) {
                                mdmAaKwhDetail.setKwhT2(mdmMonthFreezing1.getPtctValueFreezingT2()-mdmMonthFreezing2.getPtctValueFreezingT2());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT3()!=null && mdmMonthFreezing2.getPtctValueFreezingT3()!=null) {
                                mdmAaKwhDetail.setKwhT3(mdmMonthFreezing1.getPtctValueFreezingT3()-mdmMonthFreezing2.getPtctValueFreezingT3());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT4()!=null && mdmMonthFreezing2.getPtctValueFreezingT4()!=null) {
                                mdmAaKwhDetail.setKwhT4(mdmMonthFreezing1.getPtctValueFreezingT4()-mdmMonthFreezing2.getPtctValueFreezingT4());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT5()!=null && mdmMonthFreezing2.getPtctValueFreezingT5()!=null) {
                                mdmAaKwhDetail.setKwhT5(mdmMonthFreezing1.getPtctValueFreezingT5()-mdmMonthFreezing2.getPtctValueFreezingT5());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT6()!=null && mdmMonthFreezing2.getPtctValueFreezingT6()!=null) {
                                mdmAaKwhDetail.setKwhT6(mdmMonthFreezing1.getPtctValueFreezingT6()-mdmMonthFreezing2.getPtctValueFreezingT6());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT7()!=null && mdmMonthFreezing2.getPtctValueFreezingT7()!=null) {
                                mdmAaKwhDetail.setKwhT7(mdmMonthFreezing1.getPtctValueFreezingT7()-mdmMonthFreezing2.getPtctValueFreezingT7());
                            }
                            if(mdmMonthFreezing1.getPtctValueFreezingT8()!=null && mdmMonthFreezing2.getPtctValueFreezingT8()!=null) {
                                mdmAaKwhDetail.setKwhT8(mdmMonthFreezing1.getPtctValueFreezingT8()-mdmMonthFreezing2.getPtctValueFreezingT8());
                            }

                            if(mdmMonthFreezing1.getPtctValueFreezing()!=null && mdmMonthFreezing2.getPtctValueFreezing()!=null) {
                                mdmAaKwhDetail.setTotalKwh(mdmMonthFreezing1.getPtctValueFreezing()-mdmMonthFreezing2.getPtctValueFreezing());
                            }
                            if(mdmMonthFreezing1.getKwhPa()!=null && mdmMonthFreezing2.getKwhPa()!=null) {
                                mdmAaKwhDetail.setKwhPa(mdmMonthFreezing1.getKwhPa()-mdmMonthFreezing2.getKwhPa());
                            }
                            if(mdmMonthFreezing1.getKwhPaPtct()!=null && mdmMonthFreezing2.getKwhPaPtct()!=null) {
                                mdmAaKwhDetail.setKwhPaPtct(mdmMonthFreezing1.getKwhPaPtct()-mdmMonthFreezing2.getKwhPaPtct());
                            }
                            if(mdmMonthFreezing1.getKwhPr()!=null && mdmMonthFreezing2.getKwhPr()!=null) {
                                mdmAaKwhDetail.setKwhPr(mdmMonthFreezing1.getKwhPr()-mdmMonthFreezing2.getKwhPr());
                            }
                            if(mdmMonthFreezing1.getKwhPrPtct()!=null && mdmMonthFreezing2.getKwhPrPtct()!=null) {
                                mdmAaKwhDetail.setKwhPrPtct(mdmMonthFreezing1.getKwhPrPtct()-mdmMonthFreezing2.getKwhPrPtct());
                            }
                            if(mdmMonthFreezing1.getKwhRa()!=null && mdmMonthFreezing2.getKwhRa()!=null) {
                                mdmAaKwhDetail.setKwhRa(mdmMonthFreezing1.getKwhRa()-mdmMonthFreezing2.getKwhRa());
                            }
                            if(mdmMonthFreezing1.getKwhRaPtct()!=null && mdmMonthFreezing2.getKwhRaPtct()!=null) {
                                mdmAaKwhDetail.setKwhRaPtct(mdmMonthFreezing1.getKwhRaPtct()-mdmMonthFreezing2.getKwhRaPtct());
                            }
                            if(mdmMonthFreezing1.getKwhRr()!=null && mdmMonthFreezing2.getKwhRr()!=null) {
                                mdmAaKwhDetail.setKwhRr(mdmMonthFreezing1.getKwhRr()-mdmMonthFreezing2.getKwhRr());
                            }
                            if(mdmMonthFreezing1.getKwhRrPtct()!=null && mdmMonthFreezing2.getKwhRrPtct()!=null) {
                                mdmAaKwhDetail.setKwhRrPtct(mdmMonthFreezing1.getKwhRrPtct()-mdmMonthFreezing2.getKwhRrPtct());
                            }
                        }
                        else
                        if("2".equals(mdmMonthFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwhStart(0.0);
                            mdmAaKwhDetail.setTotalKwhEnd(0.0);
                            mdmAaKwhDetail.setTotalKwh(mdmMonthFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setKwhPa(mdmMonthFreezing1.getKwhPa());
                            mdmAaKwhDetail.setKwhPaPtct(mdmMonthFreezing1.getKwhPaPtct());
                            mdmAaKwhDetail.setKwhPr(mdmMonthFreezing1.getKwhPr());
                            mdmAaKwhDetail.setKwhPrPtct(mdmMonthFreezing1.getKwhPrPtct());
                            mdmAaKwhDetail.setKwhRa(mdmMonthFreezing1.getKwhRa());
                            mdmAaKwhDetail.setKwhRaPtct(mdmMonthFreezing1.getKwhRaPtct());
                            mdmAaKwhDetail.setKwhRr(mdmMonthFreezing1.getKwhRr());
                            mdmAaKwhDetail.setKwhRrPtct(mdmMonthFreezing1.getKwhRrPtct());
                        }
                    }
                    
//                    mdmAaKwhDetail.setKwhPaPtct(mdmAaKwhDetail.getTotalKwh());
                    mdmAaKwhDetail.setActive("Y");
					UpdateVeeField(mdmMonthFreezing1,mdmMonthFreezing2,mdmAaKwhDetail);
                    //VEE校验 月用电量
                    String deviceId = mdmAmDevice.getId();
                    String time = mdmAaKwhDetail.getNowTime();
                    String sourceCode = jobj.getString("plan");
                    String dataType = "10"; //09:日用电量  10:月用电量
                    String sort = "0"; //0：VEE校验    1：VEE预估
                    sendVee(deviceId,dataType,time,sourceCode,sort,mdmAaKwhDetail);
                    logger.info("send vee validate message successfully");
                    
				} 
				catch (Exception e) {
					mdmAaKwhDetail.setActive("N");
					logger.error("", e);
					
					//VEE预估 月用电量
					String deviceId = mdmAmDevice.getId();
                    String time = mdmAaKwhDetail.getNowTime();
                    String sourceCode = jobj.getString("plan");
                    String dataType = "10"; //09:日用电量  10:月用电量
                    String sort = "1"; //0：VEE校验    1：VEE预估
                    sendVee(deviceId,dataType,time,sourceCode,sort,mdmAaKwhDetail);
                    logger.info("send vee estimate message successfully");
				}
				finally {
					mdmAaKwhDetail.setCdate(new Date());
					mdmAaKwhDetailDao.insertSelective(mdmAaKwhDetail);
				}
			}
		});
	}
	
	public void UpdateVeeField(MdmMonthFreezing mdmMonthFreezing1,MdmMonthFreezing mdmMonthFreezing2,MdmAaKwhDetail mdmAaKwhDetail) {
		//更新根据Freezing1和Freezing2的内容更新VEE_RESULT_DETAIL,VEE_ESTMT_DETAIL内容
		//03030102(正向有功)，03040102(反向有功)，03050102(正向无功)，03060102(反向无功)
		/*
		 * 流程：
		 * 1，检查指定指令是否在Freezing1的结构中,如果在，则取回
		 * */
        //--------
        String verifyDetailStart = mdmMonthFreezing2.getVeeResultDetail();
        if (verifyDetailStart.equals("")) {
        	verifyDetailStart = "{\"data\":[]}";
        }
        JSONObject veeVerifyStart = JSONObject.parseObject(verifyDetailStart);

        String verifyDetailEnd = mdmMonthFreezing1.getVeeResultDetail();
        if (verifyDetailEnd.equals("")) {
        	verifyDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeVerifyEnd = JSONObject.parseObject(verifyDetailEnd);
		//-------
        String estimateDetailStart = mdmMonthFreezing2.getVeeEstmtDetail();
        if (estimateDetailStart.equals("")) {
        	estimateDetailStart = "{\"data\":[]}";
        }
        JSONObject veeEstimateStart = JSONObject.parseObject(estimateDetailStart);
        
        String estimateDetailEnd = mdmMonthFreezing1.getVeeEstmtDetail();
        if (estimateDetailEnd.equals("")) {
        	estimateDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeEstimateEnd = JSONObject.parseObject(estimateDetailEnd);
        //--------
        String editDetailStart = mdmMonthFreezing2.getVeeEditDetail();
        if (editDetailStart.equals("")) {
        	editDetailStart = "{\"data\":[]}";
        }
        JSONObject veeEditStart = JSONObject.parseObject(editDetailStart);
        
        String editDetailEnd = mdmMonthFreezing1.getVeeEditDetail();
        if (editDetailEnd.equals("")) {
        	editDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeEditEnd = JSONObject.parseObject(editDetailEnd);
        //--------
        JSONArray verifyArray = new JSONArray();
        JSONArray estimateArray = new JSONArray();
        JSONArray editArray = new JSONArray();
        JSONObject vJarArray;
        vJarArray = getValue("03030102","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03030102","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03030102","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03030102","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03030102","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03030102","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //---------
        vJarArray = getValue("03040102","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03040102","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03040102","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03040102","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03040102","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03040102","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //-------
        vJarArray = getValue("03050102","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03050102","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03050102","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03050102","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03050102","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03050102","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //----------------
        vJarArray = getValue("03060102","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03060102","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03060102","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03060102","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03060102","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03060102","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        
        JSONObject resultRoot = new JSONObject();
        JSONObject estimateRoot = new JSONObject();
        JSONObject editRoot = new JSONObject();
        resultRoot.put("data",verifyArray);
        estimateRoot.put("data",estimateArray);
        editRoot.put("data",editArray);
        if (verifyArray.size() > 0) {
			mdmAaKwhDetail.setVeeResultDetail(resultRoot.toJSONString());
        }
        if (estimateArray.size() > 0) {
			mdmAaKwhDetail.setVeeEstmtDetail(estimateRoot.toJSONString());
        }
        if (editArray.size() > 0) {
    		mdmAaKwhDetail.setVeeEditDetail(editRoot.toJSONString());
        }
	}
	
	private JSONObject getValue(String cmd,String suffix,JSONObject root) {
		return process004Impl.getValue(cmd, suffix, root);
	}
	
	private void sendVee(String deviceId, String dataType, String time, String sourceCode,
            String sort, MdmAaKwhDetail mdmAaKwhDetail) {
        //VEE 月用电量
        String type = "2";  //数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
        String function = "0";
        String totalKwh = String.format("%.4f", mdmAaKwhDetail.getTotalKwh());
        String kwhPaPtct = String.format("%.4f", mdmAaKwhDetail.getKwhPaPtct());
        String kwhRaPtct = String.format("%.4f", mdmAaKwhDetail.getKwhRaPtct());
        String kwhPrPtct = String.format("%.4f", mdmAaKwhDetail.getKwhPrPtct());
        String kwhRrPtct = String.format("%.4f", mdmAaKwhDetail.getTotalKwh());
        sendCmdProxy.send0112(function,deviceId,dataType,time,totalKwh,sourceCode,sort,type,"VEE00012");
        sendCmdProxy.send0112(function,deviceId,dataType,time,kwhPaPtct,sourceCode,sort,type,"VEE30012");
        sendCmdProxy.send0112(function,deviceId,dataType,time,kwhRaPtct,sourceCode,sort,type,"VEE40012");
        sendCmdProxy.send0112(function,deviceId,dataType,time,kwhPrPtct,sourceCode,sort,type,"VEE50012");
        sendCmdProxy.send0112(function,deviceId,dataType,time,kwhRrPtct,sourceCode,sort,type,"VEE60012");
    }
}
