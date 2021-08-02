package com.inhe.mdm.dal;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.dao.MdmAaKwhDao;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.dao.MdmAmLineDao;
import com.inhe.mdm.model.MdmAaKwh;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.VtMdmAaKwhSummary;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.utils.DateUtil;

/**
 * @descroption 日用电统计
 * @author guhf
 * @date 2021/01/22
 */
@Service
public class Process006Impl {
    private static final Logger logger = LoggerFactory.getLogger(Process006Impl.class);
	
	@Autowired
	MdmAmLineDao mdmAmLineDao;
	
	@Autowired
	MdmAaKwhDao mdmAaKwhDao;
	
	@Autowired
	MdmAaKwhDetailDao mdmAaKwhDetailDao;
	
	public void execute(JSONObject jobj) throws Exception{
	    Date startDate = DateUtil.rollByDay(jobj.getDate("data_time"), -1);
		String day = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
		logger.info("Process006-start-"+day);
		statisticByLine(day);
	}
	
	
	/**
	 * 按线路统计
	 * @param day
	 */
	private void statisticByLine(String day){
		MdmAaKwh mdmAaKwh = new MdmAaKwh();
		mdmAaKwh.setWay("1");		  		//按线路
		mdmAaKwh.setDataType("0");   		//日数据
		mdmAaKwh.setNowTime(day);
		//删除旧记录
		mdmAaKwhDao.deleteByOld(mdmAaKwh);
		
		MdmAaKwhDetail mdmAaKwhDetail = new MdmAaKwhDetail();
		mdmAaKwhDetail.setNowTime(day);
		mdmAaKwhDetail.setTimeType("0");	//统计日数据
		
		List<MdmAmLine> lineList = mdmAmLineDao.selectByAll();
		if(lineList == null){
			return;
		}
		for (MdmAmLine mdmAmLine : lineList) {
			try {
				mdmAaKwh.setRefId(mdmAmLine.getId());
				mdmAaKwh.setRefContent(mdmAmLine.getDescription());
				
				mdmAaKwhDetail.setLineId(mdmAmLine.getId());
				List<VtMdmAaKwhSummary> aaKwhSummaryList = mdmAaKwhDetailDao.selectSummary(mdmAaKwhDetail);
				if(aaKwhSummaryList == null){
					continue;
				}
				for (VtMdmAaKwhSummary sum : aaKwhSummaryList) {
					mdmAaKwh.setId(MdmCodeUtil.getAaKwhCode());
					mdmAaKwh.setOrgId(sum.getOrgId());
					mdmAaKwh.setDeptId(sum.getDeptId());
					mdmAaKwh.setTotalKwhStart(sum.getTotalKwhStart());
					mdmAaKwh.setTotalKwhEnd(sum.getTotalKwhEnd());
					mdmAaKwh.setTotalKwh(sum.getTotalKwh());
					//线路下如果有专变变压器,则取线路下的表，没有则取0
					if (sum.getYearBasis()>=1) {
						mdmAaKwh.setPriLinePriTmKwh(sum.getPriLinePriTmKwh());//专线专变
					}
					else {
						mdmAaKwh.setPriLinePriTmKwh(Double.parseDouble("0"));//专线专变
					}
					mdmAaKwh.setPubLinePriTmKwh(sum.getPubLinePriTmKwh());//公线专变
					mdmAaKwh.setPubLinePubTmKwh(sum.getPubLinePubTmKwh());//公线公变
					mdmAaKwh.setCdate(new Date());
					mdmAaKwhDao.insertSelective(mdmAaKwh);
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
}
