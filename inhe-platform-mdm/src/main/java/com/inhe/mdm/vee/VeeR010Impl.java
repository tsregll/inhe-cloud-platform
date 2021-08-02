/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.vee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.vee.MdmPeriod;
import com.inhe.mdm.model.MdmVeeReadQry;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.utils.DateUtil;


@Service
public class VeeR010Impl implements IRuleProcess{//连续N次为0
	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeConfigDao configMapper;
	@Autowired
	MdmPeriod mdmPeriod;

	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		String type = msgBody.getString("type");
		String dataType = msgBody.getString("data_type");
		String cmd = msgBody.getString("cmd");
		type = commonService.parseType(dataType,cmd);
		String data = msgBody.getString("data");
		String alarmFlag = rule.getString("alarm_flag");
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		String refCode = msgBody.getString("refCode");
		String cmdDesc = msgBody.getString("cmdDesc");
		try {
			Date dateStr =null;
			if(msgBody.getString("time").length()==7) {//月
				dateStr=DateUtil.stringToDate(msgBody.getString("time"), "yyyy-MM");
			}else {
				dateStr=msgBody.getDate("time");
			}
			JSONArray params = rule.getJSONArray("params");
			if(params==null||params.size()!=1) {
				throw new Exception("R004 params invalid");
			}
			int lostTimes = params.getJSONObject(0).getIntValue("value");
			if(device==null) {
				throw new Exception("device not found");
			}
			Double currentValue = commonService.getVeeData(data);
			if(currentValue.compareTo(0d)!=0) {
				MdmVeeRuleResult res = new MdmVeeRuleResult("R010", 0, "Success,This value != 0");
				result.add(res);
			}
			else
			{
				SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				MdmVeeReadQry qry = new MdmVeeReadQry();
				if("06".equals(type)) {//曲线数据 		
					Integer period = Integer.parseInt(mdmPeriod.toString()) ;//configMapper.getCperiod(model, cmd);//采集周期(分钟)//获取间隔点
					qry.setDeviceId(device.getId());
					qry.setDataType(dataType);
					qry.setStartTime(DateUtil.rollMinute(dateStr, period*(lostTimes-1)*-1));
					qry.setEndTime(DateUtil.rollSecond(dateStr,-1));
					qry.setFieldId(cmd);
					List<MdmDeviceRead> list2 =  configMapper.selectZeroValue2(qry);
					passFlag =valid2(passFlag,list2,qry,dataType,dateStr,refCode,cmdDesc, data,device,alarmFlag,dateFormat,lostTimes,result);
						
					
					
				}else if("05".equals(type)) {//账单数据 				
					qry.setDataType(dataType);
					qry.setDeviceId(device.getId());
					qry.setFieldId(cmd);
					if("4".equals(dataType)) {//日冻结
						dateFormat = "yyyy-MM-dd";
						qry.setStartTime(simf.parse(DateUtil.dateToString(DateUtil.rollByDay(dateStr, (lostTimes-1)*-1), DateUtil.DATE_WITH_LINE)+" 00:00:00"));
						qry.setEndTime(simf.parse(DateUtil.dateToString(DateUtil.rollByDay(dateStr, -1), DateUtil.DATE_WITH_LINE)+" 23:59:59"));
					}else {
						dateFormat = "yyyy-MM";
						qry.setStartTime(simf.parse(DateUtil.dateToString( DateUtil.rollByMonth(dateStr, (lostTimes-1)*-1), DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"));
						qry.setEndTime(DateUtil.rollSecond(simf.parse(DateUtil.dateToString(dateStr, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1));
					}
					List<MdmDeviceRead> list2 =  configMapper.selectZeroValue2(qry);
					passFlag =valid2(passFlag,list2,qry,dataType,dateStr,refCode,cmdDesc, data,device,alarmFlag,dateFormat,lostTimes,result);
				}else if("09".equals(type) ||"10".equals(type) ) {//账单数据 
					MdmVeeReadQry qry1 = new MdmVeeReadQry(); 
					qry1.setDataType(dataType);
					qry1.setDeviceId(device.getId());
					if("09".equals(type)) {//日冻结
						dateFormat = "yyyy-MM-dd";
						qry1.setDataType("0");
						qry1.setStimeStr(DateUtil.dateToString(DateUtil.rollByDay(dateStr, (lostTimes-1)*-1), DateUtil.DATE_WITH_LINE));
						qry1.setEtimeStr(DateUtil.dateToString(DateUtil.rollByDay(dateStr, -1), DateUtil.DATE_WITH_LINE));
					}else {
						dateFormat = "yyyy-MM";
						qry1.setDataType("1");
						qry1.setStimeStr(DateUtil.dateToString( DateUtil.rollByMonth(dateStr, (lostTimes-1)*-1), DateUtil.DATE_MONTH_LINE));
						qry1.setEtimeStr(DateUtil.dateToString(DateUtil.rollByMonth(dateStr, -1), DateUtil.DATE_MONTH_LINE));
					}
					List<Double> list2 =  configMapper.selectAvgValue(qry1);
					MdmVeeRuleResult res = new MdmVeeRuleResult("R010", 1, "Fail,"+lostTimes+" times =0");
					Double iNum = 0.0d;
					if (list2.size()==0)
					{
						res.setResult(2);
						res.setRemarks("Fail,1 data and equal to 0");//失败,只有一次数据且为0
					}
					else
					{
						for (Double kwh : list2) {
							try {
								if(kwh.compareTo(0d)!=0) {
									res.setResult(0);
									res.setRemarks("Success, discontinuity is 0");//成功,不连续为0
									break;	
						          }
								else
								  iNum = iNum + 1;
						    }catch(Exception e) {
								e.printStackTrace();
						   }
					    }
					}
					if (res.getResult()!=0) {
						res.setRemarks("Fail,query data "+iNum+" times = 0");//失败,查到"+iNum+"次数据且为0
						if("Y".equals(alarmFlag)) {
							commonService.sendMsg("-000920",device, dateStr, data, cmd, refCode);
						}
					}
					result.add(res);
				}
				else
				{
					MdmVeeRuleResult res = new MdmVeeRuleResult("R010", 2, "Fail,Non billing curve not verified");//失败，非账单曲线不校验
					result.add(res);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private boolean valid2(boolean passFlag,List<MdmDeviceRead> list,MdmVeeReadQry qry,String dataType, Date time, String refCode,String cmd,String data,MdmAmDevice device, String alarmFlag,String dateFormat,int lostTimes,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R010", 1, "Fail,"+lostTimes+" times = 0");
		Double currentValue=0d;
		boolean isResult = false;
		if(list.size()==0) {
			res.setResult(2);
			res.setRemarks("Fail,1 times data and =0");//失败,只有1次数据且为0
		}else {//当天有数据
			String lastTime = "";
			int inum = 1;//本次值必为0
			SimpleDateFormat simf = new SimpleDateFormat(dateFormat);
			for (MdmDeviceRead amrRead1 : list) {
				try {
					currentValue = commonService.getVeeData(amrRead1.getRealValue());
				    if((!lastTime.equals(simf.format(amrRead1.getReadTime()))) && (currentValue.compareTo(0d)==0)) 
				    	inum = inum + 1 ;
					lastTime=simf.format(amrRead1.getReadTime());
					if(currentValue.compareTo(0d)!=0) {
						if(currentValue.compareTo(0d)!=0) {
							res.setResult(0);
							res.setRemarks("Success,Discontinuity = 0");//成功，不连续为0
							isResult = true;
							break;	
				         }
			          }
			    }catch(Exception e) {
					e.printStackTrace();
			   }
		    }
			if(!isResult) {
				if ((inum>=0)&&(lostTimes>inum)) {
					res.setResult(2);
					res.setRemarks("Fail,query "+inum+" times data = 0");//失败,查到"+inum+"次数据且为0
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000920",device, time, data, cmd, refCode);
				}
			}
		}
	}
	result.add(res);
	return passFlag;
	}
			
}