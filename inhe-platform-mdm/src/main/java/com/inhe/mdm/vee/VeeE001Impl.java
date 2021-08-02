/**
 * @author pfr 2020-12-11
 */
package com.inhe.mdm.vee;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.vee.MdmPeriod;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.utils.DateUtil;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.model.MdmVeeReadQry;

import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAmDevice;

@Service
public class VeeE001Impl implements IRuleProcess{

	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeConfigDao veeConfigDao;
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
		MdmVeeRuleResult res = new MdmVeeRuleResult("E001", 1, "Fail,Unable to estimate");
		try {
			if("09".equals(type) || "10".equals(type)|| "06".equals(type)|| "05".equals(type)) {//0910用电量（aa_kwh_detail）  + 06曲线数据（read） + 05账单数据（read）
				JSONArray params = rule.getJSONArray("params");
				if(params==null) {
					throw new Exception("E001 params invalid");
				}
				int times = params.getJSONObject(0).getIntValue("value");
				String timeFormat = "";
					Date readTime2 = null;
   					if("10".equals(type)) {//月
   						readTime2=DateUtil.stringToDate(msgBody.getString("time"), "yyyy-MM");
   					}else {
   						readTime2=msgBody.getDate("time");
   					}
					if ("09".equals(type) || "10".equals(type)){//N,取最近N天的数据  求平均值（0910类型为使用电量） 
						MdmVeeReadQry qry = new MdmVeeReadQry();
						qry.setCmdType(cmd.substring(0, 4));//VEE00012：日用电量(总有功)  VEE30012：日用电量(正向有功)  VEE40012：日用电量(反向有功)	VEE50012：日用电量(正向无功)  VEE60012：日用电量(反向无功)  都为用电量值 无需计算增量
						if("10".equals(type)) {  //月   VEE00112：月用电量(总有功)  VEE30112：月用电量(正向有功)  VEE40112：月用电量(反向有功)	VEE50112：月用电量(正向无功)  VEE60112：月用电量(反向无功)
							qry.setDataType("1");
							readTime2=DateUtil.rollByMonth(readTime2, -1);
							qry.setEtimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_MONTH_LINE));
							readTime2=DateUtil.rollByDay(readTime2, (times-1)*-1);
							qry.setStimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_MONTH_LINE));
						}else if("09".equals(type))  {
							qry.setDataType("0");
							readTime2=DateUtil.rollByDay(readTime2, -1);
							qry.setEtimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_WITH_LINE));
							readTime2=DateUtil.rollByDay(readTime2, (times-1)*-1);
							qry.setStimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_WITH_LINE));	
						}
						Double avg = 0.0d ; 
						qry.setDeviceId(device.getId());
						List<MdmAaKwhDetail> KwhList = configMapper.selectEkwhValue(qry);
						if (KwhList==null||KwhList.size()<(times)) {
							res.setResult(2);
							res.setRemarks("Fail,Unable to estimate (missing data)");
							result.add(res);
						}
						else {
							Double iNum = 0.0d;
							Double iSum = 0.0;
							for (MdmAaKwhDetail kwh : KwhList) {
								try {
									iNum = iNum + 1;
									if (kwh.getTotalKwh()!=null) {
										iSum = iSum + kwh.getTotalKwh();	
									} 
									else//漏数据
									{
										res.setResult(2);
										res.setRemarks("Fail,Unable to estimate (missing data)");
										result.add(res);
										iSum = -1.0;
										break;
									}											
								} catch (Exception e) {
									e.printStackTrace();
								}	
							}
							if (!iSum.equals(-1.0))
							{
								avg = iSum / iNum;
								BigDecimal bg = new BigDecimal(avg);
								avg = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
								res.setResult(0);
								res.setRemarks("Success,estimate value:"+avg);
								result.add(res);
								msgBody.put("estValue", avg);
							}
						}
					}
					else{
						if("05".equals(type))  {//05账单数据（read） 包含日月需量  需量取平均值 其他取平均增量+最近值（需要多一个周期）
							MdmDeviceRead qry = new MdmDeviceRead();
							qry.setDeviceId(device.getId());
							qry.setCdate(DateUtil.rollSecond(readTime2, -1));
							qry.setDataType(dataType);
							SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");					
							String lastDay1F1 = "";
							String endTime = null;
							Date startTime = new Date();
							String iDc = cmd.substring(0, 2);
							int it = 0;//平均值,无需计算增量
							if (!iDc.equals("04"))
							{
								it = 1;									
							}
							if("4".equals(dataType)) {//日冻结
								qry.setCdate(simf.parse(DateUtil.dateToString(DateUtil.rollByDay(readTime2, -1), DateUtil.DATE_WITH_LINE)+" 23:59:59"));
								startTime = DateUtil.rollByDay(readTime2, (times+it)*-1);
								lastDay1F1 = DateUtil.dateToString(startTime, DateUtil.DATE_WITH_LINE);
								qry.setReadTime(simf.parse(lastDay1F1+" 00:00:00"));
								timeFormat = "yyyy-MM-dd";
								endTime  = simf.format(qry.getCdate());
							}else {//月冻结
								qry.setCdate(DateUtil.rollSecond(simf.parse(DateUtil.dateToString(readTime2, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1));
								startTime = DateUtil.rollByMonth(readTime2, (times+it)*-1);
								lastDay1F1 = DateUtil.dateToString(startTime, DateUtil.DATE_MONTH_LINE);
								qry.setReadTime(simf.parse(lastDay1F1+"-01 00:00:00"));
								timeFormat = "yyyy-MM";
								endTime  = simf.format(qry.getCdate());
							}	
							qry.setFieldId(cmd);
							List<MdmDeviceRead> listRead =  configMapper.selectZeroValue(qry);
							valid(listRead,endTime,timeFormat,times,iDc,msgBody,result,it);

						}else if("06".equals(type))  {//负荷曲线数据（电压、电流、功率）  
							Integer period = Integer.parseInt(mdmPeriod.toString()) ;//configMapper.getCperiod(model, cmd);//采集周期(分钟)
							MdmDeviceRead qry = new MdmDeviceRead();
							qry.setDeviceId(device.getId());
							qry.setCdate(DateUtil.rollSecond(readTime2, -1));
							String endTime =""; 
							qry.setDataType(dataType);				
							Date startTime = DateUtil.rollMinute(readTime2, period*times*-1); 
							qry.setReadTime(startTime);					
							qry.setFieldId(cmd);
							List<MdmDeviceRead> listRead =  configMapper.selectZeroValue(qry);
							timeFormat = "yyyy-MM-dd HH:mm:ss";
							String iDc = "04";//电压电流等求平均值即可
							valid(listRead,endTime,timeFormat,times,iDc,msgBody,result,0);
						}
					}													
			}else
			{
				res.setResult(2);
				res.setRemarks("Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Double valid(List<MdmDeviceRead> list,String endTime, String dateFormat,int Times,String idt,JSONObject msgBody,JSONArray result,int it) {
		MdmVeeRuleResult res = new MdmVeeRuleResult("E001", 1, "Fail,Can not estimate");
		Double currentValue=0d;
		Double currentValue1=0d;
		Double iSum=0d;
		Double LastValue = null;
		Double basic = null;
		Double iVag = null;
		if(list.size()==0) {
			res.setResult(2);
			res.setRemarks("Fail,Can not estimate(Missing data)");
		}else {
			String lastTime = "";
			int inum = 0;
			SimpleDateFormat simf = new SimpleDateFormat(dateFormat);
			for (MdmDeviceRead amrRead1 : list) {
				try {
					currentValue = commonService.getVeeData(amrRead1.getRealValue());
				    if(!lastTime.equals(simf.format(amrRead1.getReadTime()))) 
				    {
				    	if ((LastValue!=null && it==1)||(it==0)) {
					    	inum = inum + 1;
				    	}
				    	lastTime=simf.format(amrRead1.getReadTime());
				    	if (lastTime.substring(0, dateFormat.length()).equals(endTime.substring(0, dateFormat.length()))&& (!idt.equals("04")))//最近一次值
				    		basic = currentValue;
				    	if (LastValue!=null) {
							currentValue1 = currentValue - LastValue;
						}
						if (!idt.equals("04"))
						{
							LastValue = currentValue;
						}
						iSum = iSum + currentValue1;
					}   	
					lastTime=simf.format(amrRead1.getReadTime());
			    }catch(Exception e) {
					e.printStackTrace();
			   }
		    }
			if ((Times)<=inum) {
				if(iSum!=null) {
					iVag = iSum / inum;
					BigDecimal bg = new BigDecimal(iVag);
					iVag = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				  if (basic!=null)
					iVag = basic + iVag;
				}
				if (iVag !=null){
					res.setResult(0);
					BigDecimal bg = new BigDecimal(iVag);
					iVag = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
					res.setRemarks("Success,estimate value:"+iVag);
					msgBody.put("estValue", iVag);
				}
			}
			else {
				res.setResult(2);
				res.setRemarks("Fail,Can not estimate(Missing data)");
			}
		}
		result.add(res);	
		return iVag;		
	}
			
}
