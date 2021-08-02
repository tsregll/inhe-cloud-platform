/**
 * @author pfr 2020-12-14
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
public class VeeR012Impl implements IRuleProcess{//总和检查(Sum check)：分时合计与总和做比较

	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeConfigDao veeConfigMapper;
	
	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		String type = msgBody.getString("type");//校验数据类型
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
				String dateStr = msgBody.getString("time");
				Date readTime2 = null;
				if(dateStr.length()==7) {//月
					readTime2=DateUtil.stringToDate(dateStr, "yyyy-MM");
				}else {
					readTime2=msgBody.getDate("time");
				}				
				String deviceCode = device.getId(); 
				MdmAaKwhDetail aaPram = new MdmAaKwhDetail();
				aaPram.setDeviceId(deviceCode);
				aaPram.setNowTime(dateStr);
				MdmAaKwhDetail avg = veeConfigMapper.calcSum(aaPram);
				passFlag = valid(device,passFlag, readTime2,refCode,cmdDesc,  data, pass, alarmFlag,avg,result);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(MdmAmDevice device,Boolean passFlag, Date time, String refCode, String cmd, String data, String pass, String alarmFlag, MdmAaKwhDetail avg,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R012", 0, "Success,sum=sum(T1~T8)");
		if(avg!=null &&avg.getKwhT1()!=null&& Double.compare(avg.getTotalKwh(), avg.getKwhT1())!=0){
			passFlag = false;
			res.setResult(1);
			res.setRemarks("Fail,sum!=sum(T1~T8)");
			if("Y".equals(alarmFlag)) {
				commonService.sendMsg("-000927",device, time, data, cmd, refCode);
			}
		}else {
			res.setResult(2);
			res.setRemarks("Fail,Unable to verify (missing data)");
		}
		result.add(res);
		return passFlag;
	}
}
