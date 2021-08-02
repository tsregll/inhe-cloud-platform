/**
 * @author pfr 2020-12-14
 */
package com.inhe.mdm.vee;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.utils.DateUtil;

@Service
public class VeeR017Impl implements IRuleProcess{//最大需量与去年同个月比较

	@Autowired
	InheRedisTemplate redisTemplate;
	@Autowired
	MdmVeeConfigDao veeConfigMapper;
	//@Autowired
	//SendCmd0901 sendCmd0901;
	@Autowired
	MdmAmDeviceDao amDeviceMapper;
	@Autowired
	MdmVeeComServiceImpl commonService;

	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule,JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		try {
			String type = msgBody.getString("type");//校验数据类型  //数据类型： 1：普通数据， 2：负荷曲线， 3：其他曲线（负荷曲线以外的曲线） 4：日冻结 5：日使用 6：月冻结 7：月用电量
			String dataType = msgBody.getString("data_type");
			String cmd = msgBody.getString("cmd");
			type = commonService.parseType(dataType,cmd);
			Date time = msgBody.getDate("time");
			String data = msgBody.getString("data");
			String pass = rule.getString("pass");
			String alarmFlag = rule.getString("alarm_flag");
			String refCode = msgBody.getString("refCode");
			String cmdDesc = msgBody.getString("cmdDesc");
			Double currentValue = commonService.getVeeData(data);
			JSONArray params = rule.getJSONArray("params");
			if(params==null||params.size()!=2) {
				throw new Exception("R017 params invalid");
			}
			Double min = params.getJSONObject(0).getDouble("value");
			String minFlag = params.getJSONObject(0).getString("ignore");
			Double max = params.getJSONObject(1).getDouble("value");
			String maxFlag = params.getJSONObject(1).getString("ignore");
			if("05".equals(type)&&"04".equals(cmd.substring(0, 2))&&("6".equals(cmd.substring(cmd.length()-1,cmd.length()))||"9".equals(cmd.substring(cmd.length()-1,cmd.length())))) {//账单数据
				if(device==null) {
					throw new Exception("device not found");
				}
				SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date before = DateUtil.rollYear(time, -1);
				Date sTime=null,eTime=null;
				eTime = DateUtil.rollSecond(simf.parse(DateUtil.dateToString(DateUtil.rollByMonth(before, +1), DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1);
				sTime = simf.parse(DateUtil.dateToString(before, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00");
				Double lastValue =commonService.getReqReadLast(device.getId(),dataType,cmd,sTime,eTime);//上次值
				passFlag = valid(passFlag, time,refCode,cmdDesc,  data, pass, alarmFlag, currentValue, device,min,minFlag,max,maxFlag, lastValue,result);
			}
			else {
				MdmVeeRuleResult res = new MdmVeeRuleResult("R017", 2, "Fail,This rule is not suitable");
				result.add(res);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}

	private Boolean valid(Boolean passFlag, Date time,String refCode, String cmd, String data, String pass, String alarmFlag, Double currentValue,
			MdmAmDevice device,Double min, String minFlag, Double max,String maxFlag, Double lastValue,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R017", 0, "Success,value in range compared with last year");//,最大需量较去年符合预设范围
		if(lastValue.compareTo(-1d)!=0) {//有值
			Double compareMax = lastValue*max;
			Double compareMin = lastValue*min;
			if("N".equals(minFlag)&&"N".equals(maxFlag)) {
				if(Double.compare(commonService.getVeeData(data), compareMax)>0 || Double.compare(commonService.getVeeData(data), compareMin)<0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value not in range("+compareMin+"-"+compareMax+")");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000932",device, time, data, cmd, refCode);
					}
				}
			}else if("N".equals(minFlag)&&"Y".equals(maxFlag)){
				if(Double.compare(commonService.getVeeData(data), compareMin)<0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value < minValue("+compareMin+")");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000932",device, time, data, cmd, refCode);
					}
				}
			}else if("Y".equals(minFlag)&&"N".equals(maxFlag)){
				if(Double.compare(commonService.getVeeData(data), compareMax)>0) {
					passFlag = false;
					res.setResult(1);
					res.setRemarks("Fail,this value > maxValue("+compareMax+")");
					if("Y".equals(alarmFlag)) {
						commonService.sendMsg("-000932",device, time, data, cmd, refCode);
					}
				}
			}else {
				res.setResult(0);
				res.setRemarks("Success,Configuration ignored");
			}
		}else {
			res.setResult(2);
			res.setRemarks("Fail,Unable to verify (missing data)");
		}
		result.add(res);
		return passFlag;
	}
}
