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
public class VeeR009Impl implements IRuleProcess{//用电量远远高于平均水平(常量)
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
			if("09".equals(type) || "10".equals(type)) {//用电量
				JSONArray params = rule.getJSONArray("params");
				if(params==null||params.size()!=2) {
					throw new Exception("R009 params invalid");
				}
				int val = params.getJSONObject(0).getIntValue("value");
				String minFlag = params.getJSONObject(0).getString("ignore");
				Double quot = params.getJSONObject(1).getDouble("value");
				String maxFlag = params.getJSONObject(1).getString("ignore");
				if("N".equals(minFlag)&&"N".equals(maxFlag)) {
					String dateStr = msgBody.getString("time");
					Date readTime2 = null;
					if(dateStr.length()==7) {//月
						readTime2=DateUtil.stringToDate(dateStr, "yyyy-MM");
					}else {
						readTime2=msgBody.getDate("time");
					}
					
					Double currentValue = commonService.getVeeData(data);
					passFlag = valid(device,passFlag, readTime2, refCode, cmdDesc,  data, pass, alarmFlag, currentValue,val,quot,result);
				}
			}
			else
			{
				MdmVeeRuleResult res = new MdmVeeRuleResult("R009", 2, "Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(MdmAmDevice device,Boolean passFlag, Date time,String refCode, String cmd, String data, String pass, String alarmFlag, Double currentValue,
			int val, Double quot,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R009", 0, "Success,in range");
		if(Double.compare(currentValue,val*quot)>0) {
			passFlag = false;
			res.setResult(1);
			res.setRemarks("Fail,much higher than constant");//失败,用电量远远高于平均水平(常量)
			if("Y".equals(alarmFlag)) {
				commonService.sendMsg("-000919",device, time, data, cmd,refCode);
			}
		}
		result.add(res);
		return passFlag;
	}
}
