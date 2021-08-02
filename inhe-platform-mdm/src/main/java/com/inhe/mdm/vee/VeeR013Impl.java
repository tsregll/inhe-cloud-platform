/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.vee;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmVeeReadQry;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.utils.DateUtil;

@Service
public class VeeR013Impl implements IRuleProcess{//总和检查(Sum check)：日用电合计与月用电做比较

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
		String alarmFlag = rule.getString("alarm_flag");
		String refCode = msgBody.getString("refCode");
		String cmdDesc = msgBody.getString("cmdDesc");
		try {
			MdmVeeRuleResult res = new MdmVeeRuleResult("R013", 0, "成功");
			if("10".equals(type)) {//用电量
				String dateStr = msgBody.getString("time");				
				Date date = DateUtil.stringToDate(dateStr, "yyyy-MM");//本月1号
				MdmVeeReadQry qry = new MdmVeeReadQry();
				qry.setDataType("0");
				qry.setStimeStr(dateStr);
				qry.setDeviceId(device.getId());
				
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(date);
		        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		        List<Double> dkwhList = veeConfigMapper.selectKwhValue(qry);//当月的日用电量总计 
			    Double currentValue = commonService.getVeeData(data);//当前值
			    
			    if (dkwhList.size()<days || currentValue==null) {
			    	res.setResult(2);
					res.setRemarks("Fail,Unable to verify (missing data)");
			    }
			    else {
					Double daySum = 0.0d;
					for (Double kwh : dkwhList) {
							daySum = daySum + kwh;	
					}
					if(daySum!=null&&currentValue!=null&&Double.compare(daySum, currentValue)!=0){
						passFlag = false;
						res.setResult(1);
						if(Double.compare(daySum, currentValue)>0)
						  res.setRemarks("Success,sum(day)>month");
						if(Double.compare(daySum, currentValue)<0)
						  res.setRemarks("Success,sum(day)<month");
						if("Y".equals(alarmFlag)) {
							commonService.sendMsg("-000928",device, DateUtil.stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss"), data, cmdDesc,refCode);
						}
					}else if (daySum!=null&&currentValue!=null&&Double.compare(daySum, currentValue)==0){
						res.setResult(0);
						res.setRemarks("Success,sum(day)=month");
					}else {
						res.setResult(2);
						res.setRemarks("Fail,Unable to verify (missing some day data)");
					} 
			    	
			    }
				
			}
			else
			{
				res.setResult(2);
				res.setRemarks("Fail,This rule is not suitable");
			}
			result.add(res);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
}
