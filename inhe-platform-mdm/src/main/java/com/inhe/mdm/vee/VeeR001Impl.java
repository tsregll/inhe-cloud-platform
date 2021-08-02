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
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.mdm.dao.MdmVeeConfigDao;
import com.inhe.mdm.dao.MdmVeeReadQryDao;
import com.inhe.mdm.model.MdmVeeReadQry;
import com.inhe.mdm.model.MdmVeeRuleResult;
import com.inhe.mdm.service.impl.MdmVeeComServiceImpl;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.utils.DateUtil;
//import com.inhe.vee.send.SendCmd0901;

@Service
public class VeeR001Impl implements IRuleProcess{//本次值<上次值

	@Autowired
	InheRedisTemplate redisTemplate;
	@Autowired
	MdmVeeConfigDao veeConfigMapper;
    //@Autowired
    //SendCmd0901 sendCmd0901;
	@Autowired
	MdmVeeComServiceImpl commonService;
	@Autowired
	MdmVeeReadQryDao qryMapper;

	@Override
	public Boolean check(MdmAmDevice device,JSONObject rule,JSONObject msgBody,JSONArray result) {
 		Boolean passFlag = true;
		try {
			String type = msgBody.getString("type");//校验数据类型//数据类型： 1：普通数据， 2：负荷曲线， 3：其他曲线（负荷曲线以外的曲线） 4：日冻结 5：日使用 6：月冻结 7：月用电量
			String dataType = msgBody.getString("data_type");
			String cmd = msgBody.getString("cmd");
			type = commonService.parseType(dataType,cmd);
			Date time = msgBody.getDate("time");
			String data = msgBody.getString("data");
			String pass = rule.getString("pass");
			String alarmFlag = rule.getString("alarm_flag");
			Double currentValue = commonService.getVeeData(data);
			String refCode = msgBody.getString("refCode");
			String cmdDesc = msgBody.getString("cmdDesc");
			if(device==null) {
				throw new Exception("device not found");
			}
			MdmVeeReadQry qry = new MdmVeeReadQry();
			qry.setDataType(dataType);
			qry.setDeviceId(device.getId());
			qry.setFieldId(cmd);
			Double lastValue = -1d;
			SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if("05".equals(type)) {//账单数据 
				String readTime = null;
				if(dataType.equals("4")) {//日冻结
					readTime = DateUtil.dateToString(DateUtil.rollByDay(time, -1), "yyyy-MM-dd");
					qry.setStartTime(simf.parse(readTime+" 00:00:00"));
					qry.setEndTime(simf.parse(readTime+" 23:59:59"));
					List<MdmDeviceRead> list =qryMapper.queryLast(qry);
					if(list!=null&&list.size()>0) {
						String realValue = list.get(0).getRealValue();
						lastValue =commonService.getVeeData(realValue);
					}
				}else {//月冻结|需量
					readTime = DateUtil.dateToString(DateUtil.rollByMonth(time, -1), "yyyy-MM");
					qry.setStartTime(simf.parse(readTime+"-01 00:00:00"));
					qry.setEndTime(DateUtil.rollSecond(simf.parse(DateUtil.dateToString(time, DateUtil.DATE_MONTH_LINE)+"-01 00:00:00"),-1));
					List<MdmDeviceRead> list =qryMapper.queryLast(qry);
					if(list!=null&&list.size()>0) {
						String realValue = list.get(0).getRealValue();
						lastValue = commonService.getVeeData(realValue);
					}
				}
				passFlag = valid(passFlag, time,refCode,cmdDesc, data, pass, alarmFlag, currentValue, device, lastValue,result);
			}
			else {
				MdmVeeRuleResult res = new MdmVeeRuleResult("R001", 2, "Fail,This rule is not suitable");//不使用此规则
				result.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passFlag;
	}

	private Boolean valid(Boolean passFlag, Date time,String refCode,String cmd, String data, String pass, String alarmFlag, Double currentValue,
			MdmAmDevice device, Double lastValue,JSONArray result) throws Exception {
		MdmVeeRuleResult res = new MdmVeeRuleResult("R001", 0, "Success,this value >= last value");//成功,本次值大于等于上次值
		if(lastValue.compareTo(-1d)!=0) {//有值
			if(Double.compare(currentValue, lastValue)<0 && "N".equals(pass)) {//验证不通过是否继续
				passFlag = false;
			}
			if(Double.compare(currentValue, lastValue)<0 && "Y".equals(alarmFlag)) {//报警模式是否打开
				commonService.sendMsg("-000911",device, time, data,cmd,refCode);
				res.setResult(1);
				res.setRemarks("Fail,this value < last value");
			}
		}else {
			res.setResult(2);
			res.setRemarks("Unable to verify (missing data)");
		}
		result.add(res);
		return passFlag;
	}

}
