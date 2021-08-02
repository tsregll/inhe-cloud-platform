package com.inhe.mdm.dal;

import java.util.Date;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmAaPowerOffDetailDao;
import com.inhe.mdm.model.MdmAaPowerOffDetail;
import com.inhe.utils.DateUtil;

/**   
* Description : 停电分析 
* @author : xd
* @Date : 2021年3月24日  
*/ 
@Service
public class Process001Impl {
    
    private static final Logger logger = LoggerFactory.getLogger(Process001Impl.class);
	
    @Autowired
	MdmAaPowerOffDetailDao aaPowerOffDetailDao;
	
	@Autowired
	SqlSessionTemplate template;
	
	public void execute(JSONObject jobj) throws Exception{
		
		Date startDate = DateUtil.rollByDay(jobj.getDate("data_time"), -1);
		String day = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
		logger.info("Process001-start-"+day);
		statisticByEvent(day);
		statisticByPlan(day);
		logger.info("Process001-end");
	}
	
	/**
	 * 统计异常停电
	 * @param day
	 */
	private void statisticByEvent(String day) {
		
		template.select("com.inhe.mdm.dao.MdmAaPowerOffDetailDao.selectByEvent", day, new ResultHandler<Object>(){
			@Override
			public void handleResult(ResultContext<?> arg0) {
				try {
					MdmAaPowerOffDetail aaPowerOffDetail = (MdmAaPowerOffDetail) arg0.getResultObject();
					if(aaPowerOffDetail.getTmId()==null){
						aaPowerOffDetail.setTmId("-");
					}
					aaPowerOffDetailDao.insertSelective(aaPowerOffDetail);
					}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 统计计划停电
	 * @param day
	 */
	private void statisticByPlan(String day) {
		aaPowerOffDetailDao.insertByPlan(day);
	}
}
