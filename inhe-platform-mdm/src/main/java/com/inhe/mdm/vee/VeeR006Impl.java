/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.vee;

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
public class VeeR006Impl implements IRuleProcess{//用电量远远低于平均水平(平均值)

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
		MdmVeeReadQry qry = new MdmVeeReadQry();
		try {
			if("09".equals(type) || "10".equals(type)) {//用电量
				JSONArray params = rule.getJSONArray("params");
				if(params==null||params.size()!=2) {
					throw new Exception("R006 params invalid");
				}
				int times = params.getJSONObject(0).getIntValue("value");
				String minFlag = params.getJSONObject(0).getString("ignore");
				Double quot = params.getJSONObject(1).getDouble("value");
				String maxFlag = params.getJSONObject(1).getString("ignore");
				if("N".equals(minFlag)&&"N".equals(maxFlag)) {
					Date readTime2 = null;
   					if("10".equals(type)) {//月
   						readTime2=DateUtil.stringToDate(msgBody.getString("time"), "yyyy-MM");
   					}else {
   						readTime2=msgBody.getDate("time");
   					}
					Date readTime = readTime2;
					if("10".equals(type)) {//月
						qry.setDataType("1");
						readTime2=DateUtil.rollByMonth(readTime2, -1);
						qry.setEtimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_MONTH_LINE));
						qry.setStimeStr(qry.getEtimeStr());
						if ((times - 1)>0)
						{
							readTime2=DateUtil.rollByMonth(readTime2, (times-1)*-1);
							qry.setStimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_MONTH_LINE));	
						}
					}else {
						qry.setDataType("0");
						readTime2=DateUtil.rollByDay(readTime2, -1);
						qry.setEtimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_WITH_LINE));
						qry.setStimeStr(qry.getEtimeStr());
						if ((times - 1)>0)
						{
							readTime2=DateUtil.rollByDay(readTime2, (times-1)*-1);
							qry.setStimeStr(DateUtil.dateToString(readTime2, DateUtil.DATE_WITH_LINE));	
						}
					}
					Double avg = 0.0d ; 
					qry.setDeviceId(device.getId());
					List<Double> KwhList = veeConfigMapper.selectAvgValue(qry);//平均值次数,N,取最近N天的数据
					if (KwhList==null||KwhList.size()<times) {
						MdmVeeRuleResult res = new MdmVeeRuleResult("R006", 2, "Fail,Unable to verify (missing data)");
						result.add(res);
					}
					else {
						Double iNum = 0.0d;
						Double iSum = 0.0d;
						for (Double kwh : KwhList) {
							try {
								iNum = iNum + 1;
								iSum = iSum + kwh;
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}
						if (iSum != 0.0d)
							avg = iSum / iNum;
						Double currentValue = commonService.getVeeData(data);
						passFlag = valid(device,passFlag, readTime,refCode, cmdDesc,data, pass, alarmFlag, currentValue,avg,quot,result);
					}
					
				}
			}else
			{
				MdmVeeRuleResult res = new MdmVeeRuleResult("R006", 2, "Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(MdmAmDevice device,Boolean passFlag, Date time,String refCode,String cmd, String data, String pass, String alarmFlag, Double currentValue,
			Double avg, Double quot,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R006", 0, "Success,in range");
		if(avg!=null && Double.compare(currentValue,avg*quot)<0) {
			passFlag = false;
			res.setResult(1);
			res.setRemarks("Fail,far below average");
			if("Y".equals(alarmFlag)) {
				commonService.sendMsg("-000916",device, time, data, cmd,refCode);
			}
		}
		result.add(res);
		return passFlag;
	}
}
