/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.vee;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.utils.DateUtil;

@Service
public class VeeR004Impl implements IRuleProcess{//获取数据超过预设数据范围

	@Autowired
	MdmVeeComServiceImpl commonService;
	
	
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
			double min = params.getJSONObject(0).getDoubleValue("value");
			String minFlag = params.getJSONObject(0).getString("ignore");
			double max = params.getJSONObject(1).getDoubleValue("value");
			String maxFlag = params.getJSONObject(1).getString("ignore");
			String dateStr = msgBody.getString("time");
			Date readTime2 = null;
			if(dateStr.length()==7) {//月
				readTime2=DateUtil.stringToDate(dateStr, "yyyy-MM");
			}else {
				readTime2=msgBody.getDate("time");
			}
			
			Double currentValue = commonService.getVeeData(data);
			if ("05".equals(type)||"09".equals(type)||"10".equals(type)) {//账单数据  日月用电量 日月成功率  ||"11".equals(type)||"12".equals(type)
				if(device==null) {
					throw new Exception("device not found");
				}
				passFlag = valid(passFlag, readTime2,refCode, cmdDesc, data, pass, alarmFlag, currentValue, device, min,minFlag,max,maxFlag,result);
			}
			else
			{
				MdmVeeRuleResult res = new MdmVeeRuleResult("R004", 2, "Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(Boolean passFlag, Date time,String refCode,String cmd, String data, String pass, String alarmFlag, Double currentValue,
			MdmAmDevice device, double min, String minFlag, double max, String maxFlag,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R004", 0, "Success,Data in range");
		if("N".equals(minFlag)&&"N".equals(maxFlag)) {
			if(Double.compare(currentValue, max)>0 || Double.compare(currentValue, min)<0) {
				passFlag = false;
				res.setResult(1);
				res.setRemarks("Fail,Data > range");//失败,数据超过预设数据范围
				if("Y".equals(alarmFlag)) {
					commonService.sendMsg("-000914",device, time, data, cmd,refCode);
				}
			}
		}else if("N".equals(minFlag)&&"Y".equals(maxFlag)){
			if(Double.compare(currentValue, min)<0) {
				passFlag = false;
				res.setResult(1);
				res.setRemarks("Fail,<Minvalue");
				if("Y".equals(alarmFlag)) {
					commonService.sendMsg("-000914",device, time, data, cmd,refCode);
				}
			}
		}else if("Y".equals(minFlag)&&"N".equals(maxFlag)){
			if(Double.compare(currentValue, max)>0) {
				passFlag = false;
				res.setResult(1);
				res.setRemarks("Fail,> MaxValue");
				if("Y".equals(alarmFlag)) {
					commonService.sendMsg("-000914",device, time, data, cmd,refCode);
					
				}
			}
		}else {
			res.setResult(0);
			res.setRemarks("Success,Configuration ignored");
		}
		result.add(res);
		return passFlag;
	}

}
