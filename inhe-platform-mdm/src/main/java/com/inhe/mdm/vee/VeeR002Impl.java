/**
 * @author pfr 2020-12-11
 */
package com.inhe.mdm.vee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.utils.DateUtil;
import com.inhe.mdm.dao.MdmVeeReadQryDao;
import com.inhe.mdm.model.MdmVeeReadQry;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.vee.IRuleProcess;


@Service
public class VeeR002Impl implements IRuleProcess{//本次值=上次值
	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeReadQryDao qryMapper;
	
	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule, JSONObject msgBody,JSONArray result) {
		Boolean passFlag = true;
		//log.info("规则{} - 校验数据{}",rule,msgBody);
		String type = msgBody.getString("type");//校验数据类型
		String dataType = msgBody.getString("data_type");
		String cmd = msgBody.getString("cmd");
		String cmdDesc = msgBody.getString("cmdDesc");
		type = commonService.parseType( dataType,cmd);
		String data = msgBody.getString("data");
		String pass = rule.getString("pass");
		String alarmFlag = rule.getString("alarm_flag");
		String refCode = msgBody.getString("refCode");
		try {
			Date time = msgBody.getDate("time");
			Double currentValue = commonService.getVeeData(data);
			if("05".equals(type)) {//账单数据 
				if(device==null) {
					throw new Exception("device not found");
				}
				String readTime = null;
				MdmVeeReadQry qry = new MdmVeeReadQry();
				qry.setDataType(dataType);
				qry.setDeviceId(device.getId());
				qry.setFieldId(cmd);
				
				Double lastValue = -1d;
				SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
				if(dataType.equals("4")) {//日冻结 没有日使用
					readTime = DateUtil.dateToString(DateUtil.rollByDay(time, -1), "yyyy-MM-dd");
					qry.setStartTime(simf.parse(readTime+" 00:00:00"));
					qry.setEndTime(simf.parse(readTime+" 23:59:59"));
					List<MdmDeviceRead> list = qryMapper.queryLast(qry);
					if(list!=null&&list.size()>0) {
						String realValue = list.get(0).getRealValue();
						lastValue =commonService.getVeeData(realValue);
					}
				}else {//月冻结|需量
					readTime = DateUtil.dateToString(DateUtil.rollByMonth(time, -1), "yyyy-MM");
					qry.setStartTime(simf.parse(readTime+"-01 00:00:00"));
					qry.setEndTime(DateUtil.rollSecond(simf.parse(DateUtil.dateToString(time, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1));
					List<MdmDeviceRead> list = qryMapper.queryLast(qry);
					if(list!=null&&list.size()>0) {
						String realValue = list.get(0).getRealValue();
						lastValue =commonService.getVeeData(realValue);
					}
				}
				passFlag = valid(passFlag, time,refCode, cmdDesc, data, pass, alarmFlag, currentValue, device, lastValue,result);
			}
			else
			{
				MdmVeeRuleResult res = new MdmVeeRuleResult("R002", 2, "Fail,This rule is not suitable");
				result.add(res);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}
	
	private Boolean valid(Boolean passFlag, Date time,String refCode,String cmd, String data, String pass, String alarmFlag, Double currentValue,
			MdmAmDevice device, Double lastValue,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R002", 0, "Success,this value!=last value");//成功,本次值不等于上次值
		if(lastValue.compareTo(-1d)!=0) {//有值
			if(Double.compare(currentValue, lastValue)==0 && "N".equals(pass)) {//验证不通过是否继续
				passFlag = false;
			}
			if(Double.compare(currentValue, lastValue)==0 && "Y".equals(alarmFlag)) {//报警模式是否打开
				commonService.sendMsg("-000912",device, time, data ,cmd ,refCode);
				res.setResult(1);
				res.setRemarks("Fail,this value=last value");
			}
		}else {
			res.setResult(2);
			res.setRemarks("Unable to verify (missing data)");
		}
		result.add(res);
		return passFlag;
	}

}
