/**
 * @author liw 2020-07-24
 */
package com.inhe.mdm.vee;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.utils.DateUtil;

@Service
public class VeeR014Impl implements IRuleProcess{//用电量与上个月比较
	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeConfigDao veeConfigMapper;
	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		String type = msgBody.getString("type");
		String dataType = msgBody.getString("data_type");
		String cmd = msgBody.getString("cmd");
		type = commonService.parseType(dataType,cmd);
		String data = msgBody.getString("data");
		String pass = rule.getString("pass");
		String alarmFlag = rule.getString("alarm_flag");
		String refCode = msgBody.getString("refCode");
		String cmdDesc = msgBody.getString("cmdDesc");
		try {
			JSONArray params = rule.getJSONArray("params");
			if(params==null||params.size()!=2) {
				throw new Exception("R004 params invalid");
			}
			Double min = params.getJSONObject(0).getDouble("value");
			String minFlag = params.getJSONObject(0).getString("ignore");
			Double max = params.getJSONObject(1).getDouble("value");
			String maxFlag = params.getJSONObject(1).getString("ignore");
			if("10".equals(type)) {//用电量
				String dateStr = msgBody.getString("time");
				Date readTime2 = null;
				if(dateStr.length()==7) {//月
					readTime2=DateUtil.stringToDate(dateStr, "yyyy-MM");
				}else {
					readTime2=msgBody.getDate("time");
				}
				
				String qry = null;
				if(device==null) {
					throw new Exception("device not found");
				}
				//取上个月用电量	
				qry = DateUtil.dateToString(DateUtil.rollByMonth(readTime2, -1), "yyyy-MM");
				String deviceCode = device.getId();
				MdmAaKwhDetail aaPram = new MdmAaKwhDetail();
				aaPram.setDeviceId(deviceCode);
				aaPram.setNowTime(qry);
				Double lastMonth = veeConfigMapper.calcSumCheck(aaPram);
				passFlag = valid(passFlag, readTime2,refCode,cmdDesc,  data, pass, alarmFlag,lastMonth,device,min,minFlag,max,maxFlag,result);
			}
			else
			{
				MdmVeeRuleResult res = new MdmVeeRuleResult("R014", 2, "Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(Boolean passFlag, Date time,String refCode, String cmd, String data, String pass, String alarmFlag, Double lastMonth,
			MdmAmDevice device,Double min, String minFlag, Double max, String maxFlag, JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R014", 0, "Success,data in range");
		if (lastMonth!=null) {
			Double compareMax = lastMonth*max;
			Double compareMin = lastMonth*min;
			Double current = Double.parseDouble(data);
			
			if("N".equals(minFlag)&&"N".equals(maxFlag)) {
				if(Double.compare(current, compareMax)>0 || Double.compare(current, compareMin)<0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value not in range");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000929",device, time, data, cmd,refCode);
					}
				}
			}else if("N".equals(minFlag)&&"Y".equals(maxFlag)){
				if(Double.compare(current, compareMin)<0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value < MinValue");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000929",device, time, data, cmd,refCode);
					}
				}
			}else if("Y".equals(minFlag)&&"N".equals(maxFlag)){
				if(Double.compare(current, compareMax)>0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value > MaxValue");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000929",device, time, data, cmd,refCode);
					}
				}
			}else {
				res.setResult(0);
				res.setRemarks("Success,Configuration ignored");
			}	
		}else
		{
			res.setResult(2);
			res.setRemarks("Fail,Unable to verify (missing data)");
		}
		
		result.add(res);
		return passFlag;
		
	}

}
