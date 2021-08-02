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
public class VeeR011Impl implements IRuleProcess{//值为负数

	@Autowired
	MdmVeeComServiceImpl commonService;
	
	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
//		String type = msgBody.getString("type");//校验数据类型
//		String dataType = msgBody.getString("data_type");
//		String cmd = msgBody.getString("cmd");
//		type = commonService.parseType(dataType,cmd);
		String data = msgBody.getString("data");
		String pass = rule.getString("pass");
		String alarmFlag = rule.getString("alarm_flag");
		String refCode = msgBody.getString("refCode");
		String cmdDesc = msgBody.getString("cmdDesc");
		try {
			String dateStr = msgBody.getString("time");
			Date readTime2 = null;
			if(dateStr.length()==7) {//月
				readTime2=DateUtil.stringToDate(dateStr, "yyyy-MM");
			}else {
				readTime2=msgBody.getDate("time");
			}
			
			Double currentValue = commonService.getVeeData(data);
			passFlag = valid(device,passFlag, readTime2,refCode, cmdDesc,  data, pass, alarmFlag, currentValue,result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(MdmAmDevice device,Boolean passFlag, Date time,String refCode, String cmd, String data, String pass, String alarmFlag,
			Double currentValue,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R011", 0, "Success,value>=0");
		if(currentValue.compareTo(0d)<0) {
			passFlag = false;
			res.setResult(1);
			res.setRemarks("Fail,value<0");
			if("Y".equals(alarmFlag)) {
				commonService.sendMsg("-000926",device, time, data, cmd, refCode);
				
			}
		}
		result.add(res);
		return passFlag;
	}
}
