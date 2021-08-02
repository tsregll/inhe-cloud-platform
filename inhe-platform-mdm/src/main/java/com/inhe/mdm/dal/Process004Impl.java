package com.inhe.mdm.dal;

import java.util.Date;
import java.util.Set;

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
import com.inhe.mdm.dao.MdmDayFreezingDao;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmAmDeviceChange;
import com.inhe.mdm.model.MdmDayFreezing;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.utils.DateUtil;

/**
 * @description 日用电分析
 * @author guhf
 * @date 2021/01/21
 */
@Service
public class Process004Impl {
    
    private static final Logger logger = LoggerFactory.getLogger(Process004Impl.class);
	
	@Autowired
	SqlSessionTemplate template;
	
	@Autowired
	MdmAmDeviceDao mdmAmDeviceDao;
	
	@Autowired
	MdmAaKwhDetailDao mdmAaKwhDetailDao;
	
	@Autowired
	MdmDayFreezingDao mdmDayFreezingDao;
	
	@Autowired
	MdmAmDeviceChangeDao mdmAmDeviceChangeDao;
	
	@Autowired
	SendCmdProxy sendCmdProxy;
	
	public void execute(JSONObject jobj) throws Exception{
	    Date startDate = DateUtil.rollByDay(jobj.getDate("data_time"), -1);
		String lastDay1F1 = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
		String lastDay1F2 = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_NOTHING);
		String lastDay2F2 = DateUtil.dateToString(DateUtil.rollByDay(startDate, -1), DateUtil.DATE_WITH_NOTHING);
		logger.info("Process004-start-"+lastDay1F1);
		
		template.select("com.inhe.mdm.dao.MdmAmDeviceDao.selectByType", "0", new ResultHandler<Object>(){
			@Override
			public void handleResult(ResultContext<?> arg0) {
			    MdmAaKwhDetail mdmAaKwhDetail = new MdmAaKwhDetail();
		        mdmAaKwhDetail.setTimeType("0");
		        mdmAaKwhDetail.setNowTime(lastDay1F1);
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
					
					MdmDayFreezing mdmDayFreezing1 = new MdmDayFreezing();//当天的日冻结数据
					mdmDayFreezing1.setDataType(2);
	                mdmDayFreezing1.setDataPeriod(lastDay1F2);
					mdmDayFreezing1.setDeviceId(mdmAmDevice.getId());
					mdmDayFreezing1 = mdmDayFreezingDao.selectByPrimaryKey(mdmDayFreezing1);
					if(mdmDayFreezing1 == null) {
						mdmAaKwhDetail.setActive("N");
						throw new InheExceptionBase(-100000, mdmAmDevice.getDeviceNum()+" can not find end kwh "+lastDay1F2);
					}
					
					MdmDayFreezing mdmDayFreezing2 = new MdmDayFreezing();//上一天的日冻结数据
					mdmDayFreezing2.setDataType(2);
                    mdmDayFreezing2.setDataPeriod(lastDay2F2);
                    mdmDayFreezing2.setDeviceId(mdmAmDevice.getId());
                    mdmDayFreezing2 = mdmDayFreezingDao.selectByPrimaryKey(mdmDayFreezing2);
                    if(mdmDayFreezing2 == null || !"1".equals(mdmDayFreezing2.getFzDataType())) {
                        mdmAaKwhDetail.setActive("N");
                        throw new InheExceptionBase(-100000, mdmAmDevice.getDeviceNum()+" can not find start kwh "+lastDay2F2);
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
                        MdmDayFreezing oldMdmDayFreezing2 = new MdmDayFreezing();
                        oldMdmDayFreezing2.setDataPeriod(lastDay2F2);
                        oldMdmDayFreezing2.setDeviceNum(newDeviceChange.getOldDevicenum());
                        oldMdmDayFreezing2 = mdmDayFreezingDao.selectByDeviceNum(oldMdmDayFreezing2);
                        if(oldMdmDayFreezing2 == null || !"1".equals(oldMdmDayFreezing2.getFzDataType())) {
                            mdmAaKwhDetail.setActive("N");
                            throw new InheExceptionBase(-100000, oldMdmDayFreezing2.getDeviceNum()+" can not find start kwh "+lastDay2F2);
                        }
                        if("1".equals(mdmDayFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                        	mdmAaKwhDetail.setKwhPaPtctStart(mdmDayFreezing1.getKwhPaPtct());
                        	mdmAaKwhDetail.setKwhPaPtctEnd(mdmDayFreezing2.getKwhPaPtct());
                        	mdmAaKwhDetail.setKwhPrPtctStart(mdmDayFreezing1.getKwhPrPtct());
                        	mdmAaKwhDetail.setKwhPrPtctEnd(mdmDayFreezing2.getKwhPrPtct());
                        	mdmAaKwhDetail.setKwhRaPtctStart(mdmDayFreezing1.getKwhRaPtct());
                        	mdmAaKwhDetail.setKwhRaPtctEnd(mdmDayFreezing2.getKwhRaPtct());
                        	mdmAaKwhDetail.setKwhRrPtctStart(mdmDayFreezing1.getKwhRrPtct());
                        	mdmAaKwhDetail.setKwhRrPtctEnd(mdmDayFreezing2.getKwhRrPtct());
                            mdmAaKwhDetail.setTotalKwhEnd(mdmDayFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwhStart(mdmDayFreezing2.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwh(
                                    newDeviceChange.getOldDevicenumKwhEnd()-oldMdmDayFreezing2.getPtctValueFreezing()
                                    +mdmDayFreezing1.getPtctValueFreezing()-newDeviceChange.getNewDevicenumKwhStart()
                                    );
                        }
                        else
                        if("2".equals(mdmDayFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwh(
                                    newDeviceChange.getOldDevicenumKwhEnd()-oldMdmDayFreezing2.getPtctValueFreezing()
                                    +mdmDayFreezing1.getPtctValueFreezing()
                                    );
                        }
                    }
                    else 
                    if(oldDeviceChange != null) { //有拆表
                        if("1".equals(mdmDayFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                            mdmAaKwhDetail.setTotalKwhEnd(oldDeviceChange.getOldDevicenumKwhEnd());
                            mdmAaKwhDetail.setTotalKwhStart(mdmDayFreezing2.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwh(oldDeviceChange.getOldDevicenumKwhEnd()-mdmDayFreezing2.getPtctValueFreezing());
                        }
                        else
                        if("2".equals(mdmDayFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwh(mdmDayFreezing1.getPtctValueFreezing());
                        }
                    }
                    else { //既无换表，也无拆表
                        if("1".equals(mdmDayFreezing1.getFzDataType())) {     //如果是冻结电量,获取上二天的数据
                       	
                        	mdmAaKwhDetail.setKwhPaPtctStart(mdmDayFreezing2.getKwhPaPtct());//03030002_S
                        	mdmAaKwhDetail.setKwhPaPtctEnd(mdmDayFreezing1.getKwhPaPtct());  //03030002_E
                        	mdmAaKwhDetail.setKwhPrPtctStart(mdmDayFreezing2.getKwhPrPtct());//03050002_S
                        	mdmAaKwhDetail.setKwhPrPtctEnd(mdmDayFreezing1.getKwhPrPtct());  //03050002_E
                        	mdmAaKwhDetail.setKwhRaPtctStart(mdmDayFreezing2.getKwhRaPtct());//03040002_S
                        	mdmAaKwhDetail.setKwhRaPtctEnd(mdmDayFreezing1.getKwhRaPtct());  //03040002_E
                        	mdmAaKwhDetail.setKwhRrPtctStart(mdmDayFreezing2.getKwhRrPtct());//03060002_S
                        	mdmAaKwhDetail.setKwhRrPtctEnd(mdmDayFreezing1.getKwhRrPtct());  //03060002_E
                        	
                            mdmAaKwhDetail.setTotalKwhEnd(mdmDayFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setTotalKwhStart(mdmDayFreezing2.getPtctValueFreezing());
                            if(mdmDayFreezing1.getPtctValueFreezing()!=null && mdmDayFreezing2.getPtctValueFreezing()!=null) {
                                mdmAaKwhDetail.setTotalKwh(mdmDayFreezing1.getPtctValueFreezing()-mdmDayFreezing2.getPtctValueFreezing());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT1()!=null && mdmDayFreezing2.getPtctValueFreezingT1()!=null) {
                                mdmAaKwhDetail.setKwhT1(mdmDayFreezing1.getPtctValueFreezingT1()-mdmDayFreezing2.getPtctValueFreezingT1());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT2()!=null && mdmDayFreezing2.getPtctValueFreezingT2()!=null) {
                                mdmAaKwhDetail.setKwhT2(mdmDayFreezing1.getPtctValueFreezingT2()-mdmDayFreezing2.getPtctValueFreezingT2());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT3()!=null && mdmDayFreezing2.getPtctValueFreezingT3()!=null) {
                                mdmAaKwhDetail.setKwhT3(mdmDayFreezing1.getPtctValueFreezingT3()-mdmDayFreezing2.getPtctValueFreezingT3());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT4()!=null && mdmDayFreezing2.getPtctValueFreezingT4()!=null) {
                                mdmAaKwhDetail.setKwhT4(mdmDayFreezing1.getPtctValueFreezingT4()-mdmDayFreezing2.getPtctValueFreezingT4());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT5()!=null && mdmDayFreezing2.getPtctValueFreezingT5()!=null) {
                                mdmAaKwhDetail.setKwhT5(mdmDayFreezing1.getPtctValueFreezingT5()-mdmDayFreezing2.getPtctValueFreezingT5());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT6()!=null && mdmDayFreezing2.getPtctValueFreezingT6()!=null) {
                                mdmAaKwhDetail.setKwhT6(mdmDayFreezing1.getPtctValueFreezingT6()-mdmDayFreezing2.getPtctValueFreezingT6());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT7()!=null && mdmDayFreezing2.getPtctValueFreezingT7()!=null) {
                                mdmAaKwhDetail.setKwhT7(mdmDayFreezing1.getPtctValueFreezingT7()-mdmDayFreezing2.getPtctValueFreezingT7());
                            }
                            if(mdmDayFreezing1.getPtctValueFreezingT8()!=null && mdmDayFreezing2.getPtctValueFreezingT8()!=null) {
                                mdmAaKwhDetail.setKwhT8(mdmDayFreezing1.getPtctValueFreezingT8()-mdmDayFreezing2.getPtctValueFreezingT8());
                            }
                            
                            if(mdmDayFreezing1.getKwhPa()!=null && mdmDayFreezing2.getKwhPa()!=null) {
                                mdmAaKwhDetail.setKwhPa(mdmDayFreezing1.getKwhPa()-mdmDayFreezing2.getKwhPa());
                            }
                            if(mdmDayFreezing1.getKwhPaPtct()!=null && mdmDayFreezing2.getKwhPaPtct()!=null) {
                                mdmAaKwhDetail.setKwhPaPtct(mdmDayFreezing1.getKwhPaPtct()-mdmDayFreezing2.getKwhPaPtct());
                            }
                            if(mdmDayFreezing1.getKwhPr()!=null && mdmDayFreezing2.getKwhPr()!=null) {
                                mdmAaKwhDetail.setKwhPr(mdmDayFreezing1.getKwhPr()-mdmDayFreezing2.getKwhPr());
                            }
                            if(mdmDayFreezing1.getKwhPrPtct()!=null && mdmDayFreezing2.getKwhPrPtct()!=null) {
                                mdmAaKwhDetail.setKwhPrPtct(mdmDayFreezing1.getKwhPrPtct()-mdmDayFreezing2.getKwhPrPtct());
                            }
                            if(mdmDayFreezing1.getKwhRa()!=null && mdmDayFreezing2.getKwhRa()!=null) {
                                mdmAaKwhDetail.setKwhRa(mdmDayFreezing1.getKwhRa()-mdmDayFreezing2.getKwhRa());
                            }
                            if(mdmDayFreezing1.getKwhRaPtct()!=null && mdmDayFreezing2.getKwhRaPtct()!=null) {
                                mdmAaKwhDetail.setKwhRaPtct(mdmDayFreezing1.getKwhRaPtct()-mdmDayFreezing2.getKwhRaPtct());
                            }
                            if(mdmDayFreezing1.getKwhRr()!=null && mdmDayFreezing2.getKwhRr()!=null) {
                                mdmAaKwhDetail.setKwhRr(mdmDayFreezing1.getKwhRr()-mdmDayFreezing2.getKwhRr());
                            }
                            if(mdmDayFreezing1.getKwhRrPtct()!=null && mdmDayFreezing2.getKwhRrPtct()!=null) {
                                mdmAaKwhDetail.setKwhRrPtct(mdmDayFreezing1.getKwhRrPtct()-mdmDayFreezing2.getKwhRrPtct());
                            }
                        }
                        else
                        if("2".equals(mdmDayFreezing1.getFzDataType())){      //如果是使用电量
                            mdmAaKwhDetail.setTotalKwh(mdmDayFreezing1.getPtctValueFreezing());
                            mdmAaKwhDetail.setKwhPa(mdmDayFreezing1.getKwhPa());
                            mdmAaKwhDetail.setKwhPaPtct(mdmDayFreezing1.getKwhPaPtct());
                            mdmAaKwhDetail.setKwhPr(mdmDayFreezing1.getKwhPr());
                            mdmAaKwhDetail.setKwhPrPtct(mdmDayFreezing1.getKwhPrPtct());
                            mdmAaKwhDetail.setKwhRa(mdmDayFreezing1.getKwhRa());
                            mdmAaKwhDetail.setKwhRaPtct(mdmDayFreezing1.getKwhRaPtct());
                            mdmAaKwhDetail.setKwhRr(mdmDayFreezing1.getKwhRr());
                            mdmAaKwhDetail.setKwhRrPtct(mdmDayFreezing1.getKwhRrPtct());
                        }
                    }
//					mdmAaKwhDetail.setKwhPaPtct(mdmAaKwhDetail.getTotalKwh());
					mdmAaKwhDetail.setActive("Y");
					UpdateVeeField(mdmDayFreezing1,mdmDayFreezing2,mdmAaKwhDetail);
					//VEE校验 日用电量
					String deviceId = mdmAmDevice.getId();
                    String time = mdmAaKwhDetail.getNowTime();
                    String sourceCode = jobj.getString("plan");
                    String dataType = "09"; //09:日用电量  10:月用电量
                    String sort = "0"; //0：VEE校验    1：VEE预估
                    sendVee(deviceId,dataType,time,sourceCode,sort,mdmAaKwhDetail);
                    logger.info("send vee validate message successfully");
				} 
				catch (Exception e) {
					mdmAaKwhDetail.setActive("N");
					logger.error("", e);
					
					//VEE预估 日用电量
					String deviceId = mdmAmDevice.getId();
                    String time = mdmAaKwhDetail.getNowTime();
                    String sourceCode = jobj.getString("plan");
                    String dataType = "09"; //09:日用电量  10:月用电量
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
	
	private void UpdateVeeField(MdmDayFreezing mdmDayFreezing1,MdmDayFreezing mdmDayFreezing2,MdmAaKwhDetail mdmAaKwhDetail) {
		//更新根据Freezing1和Freezing2的内容更新VEE_RESULT_DETAIL,VEE_ESTMT_DETAIL内容
		//03030002(正向有功)，03040002(反向有功)，03050002(正向无功)，03060002(反向无功)
		/*
		 * 流程：
		 * 1，检查指定指令是否在Freezing1的结构中,如果在，则取回
		 * */
        //--------
        String verifyDetailStart = mdmDayFreezing2.getVeeResultDetail();
        if ((verifyDetailStart==null) || (verifyDetailStart.equals(""))) {
        	verifyDetailStart = "{\"data\":[]}";
        }
        JSONObject veeVerifyStart = JSONObject.parseObject(verifyDetailStart);

        String verifyDetailEnd = mdmDayFreezing1.getVeeResultDetail();
        if ((verifyDetailEnd==null)||(verifyDetailEnd.equals(""))) {
        	verifyDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeVerifyEnd = JSONObject.parseObject(verifyDetailEnd);
		//-------
        String estimateDetailStart = mdmDayFreezing2.getVeeEstmtDetail();
        if ((estimateDetailStart == null)||(estimateDetailStart.equals(""))) {
        	estimateDetailStart = "{\"data\":[]}";
        }
        JSONObject veeEstimateStart = JSONObject.parseObject(estimateDetailStart);
        
        String estimateDetailEnd = mdmDayFreezing1.getVeeEstmtDetail();
        if ((estimateDetailEnd==null)||(estimateDetailEnd.equals(""))) {
        	estimateDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeEstimateEnd = JSONObject.parseObject(estimateDetailEnd);
        //--------
        String editDetailStart = mdmDayFreezing2.getVeeEditDetail();
        if ((editDetailStart==null)||(editDetailStart.equals(""))) {
        	editDetailStart = "{\"data\":[]}";
        }
        JSONObject veeEditStart = JSONObject.parseObject(editDetailStart);
        
        String editDetailEnd = mdmDayFreezing1.getVeeEditDetail();
        if ((editDetailEnd==null)||(editDetailEnd.equals(""))) {
        	editDetailEnd = "{\"data\":[]}";
        }
        JSONObject veeEditEnd = JSONObject.parseObject(editDetailEnd);
        //--------
        JSONArray verifyArray = new JSONArray();
        JSONArray estimateArray = new JSONArray();
        JSONArray editArray = new JSONArray();
        JSONObject vJarArray;
        vJarArray = getValue("03030002","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03030002","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03030002","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03030002","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03030002","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03030002","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //---------
        vJarArray = getValue("03040002","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03040002","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03040002","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03040002","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03040002","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03040002","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //-------
        vJarArray = getValue("03050002","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03050002","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03050002","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03050002","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03050002","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03050002","_E",veeEditEnd);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        //----------------
        vJarArray = getValue("03060002","_S",veeVerifyStart);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03060002","_E",veeVerifyEnd);
        if (vJarArray != null) {
        	verifyArray.add(vJarArray);
        }
        vJarArray = getValue("03060002","_S",veeEstimateStart);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03060002","_E",veeEstimateEnd);
        if (vJarArray != null) {
        	estimateArray.add(vJarArray);
        }
        vJarArray = getValue("03060002","_S",veeEditStart);
        if (vJarArray != null) {
        	editArray.add(vJarArray);
        }
        vJarArray = getValue("03060002","_E",veeEditEnd);
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
	
	//检查指定的指令是否在root字段中，如果存在，则取回数据
	public JSONObject getValue(String cmd,String suffix,JSONObject root) {
		JSONArray veedata = root.getJSONArray("data");
	    JSONArray jarray = new JSONArray();
		JSONObject content2 = new JSONObject();           
	    for(int i = 0; i < veedata.size(); i++){
    		JSONObject object = veedata.getJSONObject(i);
    		Set<String> keys = object.keySet();//result,VEEXXXX
    		if (keys.contains(cmd)) {//检查是否存指指令的cmd
    			if (keys.size() == 1) {
    				content2.put(cmd+suffix,object.getString(cmd));
    			}
    			else {
	        		for(String key:keys){
	        			if(key.equals("result")){
	        				content2.put(key, object.getString(key));
	        			}
	        			if(key.equals(cmd)){
	        				content2.put(key+suffix,object.getJSONArray(key));
	        			}
	        		}
    			}
    			jarray.add(content2);
    			break;
    		}
	    }
	    if (jarray.size() > 0) {
			return content2;
	    }
	    else {
	    	return null;
	    }
	}
	
	private void sendVee(String deviceId, String dataType, String time, String sourceCode,
	        String sort, MdmAaKwhDetail mdmAaKwhDetail) {
	    //VEE 日用电量
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
