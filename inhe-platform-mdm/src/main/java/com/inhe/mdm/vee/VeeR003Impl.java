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
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.utils.DateUtil;

@Service
public class VeeR003Impl implements IRuleProcess{//表码连续缺失，连续N次无数据?待验
	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeConfigDao configMapper;
	@Autowired
	MdmPeriod mdmPeriod;
	
	
	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		String type = msgBody.getString("type");//校验数据类型
		String dataType = msgBody.getString("data_type");
		String cmd = msgBody.getString("cmd");
		type = commonService.parseType( dataType,cmd);
		String data = msgBody.getString("data");
		String pass = rule.getString("pass");
		String alarmFlag = rule.getString("alarm_flag");
		String refCode = msgBody.getString("refCode");
		String cmdDesc = msgBody.getString("cmdDesc");
		try {
			Date time = msgBody.getDate("time");
			JSONArray params = rule.getJSONArray("params");
			if(params==null||params.size()!=1) {
				throw new Exception("R003 params invalid");
			}
			int lostTimes = params.getJSONObject(0).getIntValue("value");
			String lostTimesFlag = params.getJSONObject(0).getString("ignore");
			if(device==null) {
				throw new Exception("device not found");
			}
			List<MdmDeviceRead> list = null;
			String timeFormat = "";
			if("06".equals(type)) {//曲线数据
				Integer period = Integer.parseInt(mdmPeriod.toString()) ;//configMapper.getCperiod(model, cmd);//采集周期(分钟)
				//根据当前冻结值,查上N分钟周期曲线记录
				MdmDeviceRead qry = new MdmDeviceRead();
				qry.setDeviceId(device.getId());
				qry.setCdate(DateUtil.rollSecond(time, -1));
				qry.setDataType(dataType);				
				Date startTime = DateUtil.rollMinute(time, period*lostTimes*-1); 
				qry.setReadTime(startTime);					
				qry.setFieldId(cmd);
				list =  configMapper.selectZeroValue(qry);
				timeFormat = "yyyy-MM-dd HH:mm:ss";
				passFlag = valid(passFlag, time, timeFormat,refCode,cmdDesc, data, pass, alarmFlag, device, list,lostTimes,lostTimesFlag,result);	
				
				
			}else if("05".equals(type)) {//账单数据 
				MdmDeviceRead qry = new MdmDeviceRead();
				qry.setDeviceId(device.getId());
				qry.setCdate(DateUtil.rollSecond(time, -1));
				qry.setDataType(dataType);
				SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");					
				String lastDay1F1 = "";
				Date startTime = new Date();
				if("4".equals(dataType)) {//日冻结
					qry.setCdate(simf.parse(DateUtil.dateToString(DateUtil.rollByDay(time, -1), DateUtil.DATE_WITH_LINE)+" 23:59:59"));
					startTime = DateUtil.rollByDay(time, (lostTimes)*-1);
					lastDay1F1 = DateUtil.dateToString(startTime, DateUtil.DATE_WITH_LINE);
					qry.setReadTime(simf.parse(lastDay1F1+" 00:00:00"));
					timeFormat = "yyyy-MM-dd";
				}else {//月冻结
					qry.setCdate(DateUtil.rollSecond(simf.parse(DateUtil.dateToString(time, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1));
					startTime = DateUtil.rollByMonth(time, (lostTimes)*-1);
					lastDay1F1 = DateUtil.dateToString(startTime, DateUtil.DATE_MONTH_LINE);
					qry.setReadTime(simf.parse(lastDay1F1+"-01 00:00:00"));
					timeFormat = "yyyy-MM";
				}
				qry.setFieldId(cmd);
				list =  configMapper.selectZeroValue(qry);
				passFlag = valid(passFlag, time, timeFormat,refCode,cmdDesc,data, pass, alarmFlag, device, list,lostTimes,lostTimesFlag,result);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(Boolean passFlag, Date time,String timeFormat,String refCode,String cmd, String data, String pass, String alarmFlag, 
			MdmAmDevice device,List<MdmDeviceRead> list,int lostTimes,String lostTimesFlag,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R003", 0, "success,Data not missing "+lostTimes+" times");
		
		
		if(list==null) {
			res.setResult(1);
			res.setRemarks(lostTimes+" times missing");
		}else {
			if(list.size()==0) {//表码连续N次命中 inum<lostTimes inum ==0
				passFlag = false;
				res.setResult(1);
				res.setRemarks("Fail,"+lostTimes+" times missing data");
				if("Y".equals(alarmFlag)) {
					commonService.sendMsg("-000913",device, time, data,cmd,refCode);
				}
			}
		}
		
		
		result.add(res);
		return passFlag;
	}	
	
}
