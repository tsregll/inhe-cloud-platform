package com.inhe.mdm.dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.admin.model.SysDepartment;
import com.inhe.admin.model.SysOrg;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaPowerOffDao;
import com.inhe.mdm.dao.MdmAaPowerOffDetailDao;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.dao.MdmAmLineDao;
import com.inhe.mdm.dao.MdmAmTmDao;
import com.inhe.mdm.model.MdmAaPowerOff;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.VtMdmAaPowerOffDetail;
import com.inhe.mdm.model.VtMdmAaPowerOffSummary;
import com.inhe.mdm.model.VtMdmDeviceSummary;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;
import com.inhe.node.service.impl.SysOrgServiceImpl;
import com.inhe.utils.DateUtil;

/**   
* Description : 月停电统计 
* @author : xd
* @Date : 2021年3月24日  
*/ 
@Service
public class Process003Impl {
    
    private static final Logger logger = LoggerFactory.getLogger(Process003Impl.class);
	
	
	@Autowired
	MdmAmLineDao amLineDao;
	
	@Autowired
	MdmAmTmDao amTmDao;
	
	@Autowired
	MdmAmDeviceDao amDeviceDao;
	
	@Autowired
	MdmAaPowerOffDetailDao aaPowerOffDetailDao;
	
	@Autowired
	MdmAaPowerOffDao aaPowerOffDao;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	@Autowired
	SysOrgServiceImpl sysOrgServiceImpl;
	
	String[] offTypes = {"0", "1", "2"};

	public void execute(JSONObject jobj) throws Exception{
		Date startDate = DateUtil.rollByMonth(jobj.getDate("data_time"), -1);
		String month = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
		logger.info("Process003-start-"+month);
		statisticByBranch(month);
		statisticByLine(month);
		statisticByStation(month);
		logger.info("Process003-end");
	}
	
	/**
	 * 按机构统计
	 * @param day
	 */
	private void statisticByBranch(String month) throws Exception{
		
		MdmAaPowerOff aaPowerOff = new MdmAaPowerOff();
		aaPowerOff.setWay("0");		  //按机构
		aaPowerOff.setDataType("1");  //月冻结
		aaPowerOff.setNowTime(month);	
		//删除旧记录
		aaPowerOffDao.deleteByOld(aaPowerOff);
		
		SysDepartment sysDept = new SysDepartment();
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgStatus("0");
		PageBean<SysOrg> sysOrgs = sysOrgServiceImpl.selectTable(sysOrg,1,99);
		List<SysOrg> orgIds = sysOrgs.getData();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(orgIds));
		for(int n = 0;n < array.size();n++){
			sysDept.setOrgId(array.getJSONObject(n).getString("orgId"));
			sysDept.setDeptId("001");
			JSONArray deptArray = sysDeptServiceImpl.selectComboRoot(sysDept, true);
			List<String> deptIdLists = new ArrayList<>();
			for(int i = 0;i < deptArray.size();i++){
				JSONObject rootObject = deptArray.getJSONObject(i);
				String deptRoot = rootObject.getString("value");
				deptIdLists.add(deptRoot);
				JSONArray childrenArray = rootObject.getJSONArray("children");
				for(int j = 0;j < childrenArray.size(); j++){
					JSONObject childrenObject = childrenArray.getJSONObject(j);
					deptIdLists.add(childrenObject.getString("value"));
				}
			}
			for (String deptId : deptIdLists) {
				try {
					MdmAmDevice amDevice = new MdmAmDevice();
					amDevice.setDeptId(deptId);
					amDevice.setOrgId(array.getJSONObject(n).getString("orgId"));
					VtMdmDeviceSummary mdmDeviceSummary = amDeviceDao.selectSummary(amDevice);
					
					aaPowerOff.setRefId(deptId);
					sysDept.setDeptId(deptId);
					String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
					aaPowerOff.setRefContent(deptName);
					aaPowerOff.setUserCount(mdmDeviceSummary.getTotal());
					aaPowerOff.setImportantCount(mdmDeviceSummary.getImpCount());
					
					VtMdmAaPowerOffDetail aaPowerOffDetail = new VtMdmAaPowerOffDetail();
					aaPowerOffDetail.setDeptId(deptId);
					aaPowerOffDetail.setOrgId(array.getJSONObject(n).getString("orgId"));
					aaPowerOffDetail.setDateStr(month);
					
						for(int i=0;i<offTypes.length;i++) {
							aaPowerOffDetail.setOffType(offTypes[i]);
							List<VtMdmAaPowerOffSummary> sumList = aaPowerOffDetailDao.selectSummary(aaPowerOffDetail);
							if(sumList == null){
								continue;
							}
							aaPowerOff.setOffType(offTypes[i]);
							for (VtMdmAaPowerOffSummary sum : sumList) {
								aaPowerOff.setId(MdmCodeUtil.getPowerOffCode());
								aaPowerOff.setDeptId(sum.getDeptId());
								aaPowerOff.setOrgId(sum.getOrgId());
								aaPowerOff.setOffUserCount(sum.getOffUserCount());
								aaPowerOff.setOffThreeUserCount(sum.getOffThreeUserCount());
								aaPowerOff.setImportantOffDuration(sum.getTotalOffDurationImp());
								aaPowerOff.setImportantOffTimes(sum.getTotalOffTimesImp());
								aaPowerOff.setTotalOffDuration(sum.getTotalOffDuration());
								aaPowerOff.setTotalOffTimes(sum.getTotalOffTimes());
								if(mdmDeviceSummary.getTotal() == 0){
									aaPowerOff.setAveOffDuration(0.0);
									aaPowerOff.setAveOffTimes(0.0);
								}
								else{
									aaPowerOff.setAveOffDuration(sum.getTotalOffDuration()/mdmDeviceSummary.getTotal());
									aaPowerOff.setAveOffTimes((double)sum.getTotalOffTimes()/mdmDeviceSummary.getTotal());
								}
								
								aaPowerOff.setCdate(new Date());
								aaPowerOffDao.insertSelective(aaPowerOff);
							}
					}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	  }
	}
	
	/**
	 * 按线路统计
	 * @param day
	 */
	private void statisticByLine(String month){
		MdmAaPowerOff aaPowerOff = new MdmAaPowerOff();
		aaPowerOff.setWay("1");		  //按线路
		aaPowerOff.setDataType("1");  //月冻结
		aaPowerOff.setNowTime(month);
		//删除旧记录
		aaPowerOffDao.deleteByOld(aaPowerOff);
		
		List<MdmAmLine> lineList = amLineDao.selectByAll();
		if(lineList == null){
			return;
		}
		for (MdmAmLine amLine : lineList) {
			try {
				MdmAmDevice amDevice = new MdmAmDevice();
				amDevice.setLineId(amLine.getId());
				VtMdmDeviceSummary mdmDeviceSummary = amDeviceDao.selectSummary(amDevice);
				
				aaPowerOff.setRefId(amLine.getId());
				aaPowerOff.setRefContent(amLine.getDescription());
				aaPowerOff.setUserCount(mdmDeviceSummary.getTotal());
				aaPowerOff.setImportantCount(mdmDeviceSummary.getImpCount());
				
				VtMdmAaPowerOffDetail aaPowerOffDetail = new VtMdmAaPowerOffDetail();
				aaPowerOffDetail.setLineId(amLine.getId());
				aaPowerOffDetail.setDateStr(month);
				
				for(int i=0;i<offTypes.length;i++) {
					aaPowerOffDetail.setOffType(offTypes[i]);
					List<VtMdmAaPowerOffSummary> sumList = aaPowerOffDetailDao.selectSummary(aaPowerOffDetail);
					if(sumList == null){
						continue;
					}
					aaPowerOff.setOffType(offTypes[i]);
					for (VtMdmAaPowerOffSummary sum : sumList) {
						aaPowerOff.setId(MdmCodeUtil.getPowerOffCode());
						aaPowerOff.setDeptId(sum.getDeptId());
						aaPowerOff.setOrgId(sum.getOrgId());
						aaPowerOff.setOffUserCount(sum.getOffUserCount());
						aaPowerOff.setOffThreeUserCount(sum.getOffThreeUserCount());
						aaPowerOff.setImportantOffDuration(sum.getTotalOffDurationImp());
						aaPowerOff.setImportantOffTimes(sum.getTotalOffTimesImp());
						aaPowerOff.setTotalOffDuration(sum.getTotalOffDuration());
						aaPowerOff.setTotalOffTimes(sum.getTotalOffTimes());
						if(mdmDeviceSummary.getTotal() == 0){
							aaPowerOff.setAveOffDuration(0.0);
							aaPowerOff.setAveOffTimes(0.0);
						}
						else{
							aaPowerOff.setAveOffDuration(sum.getTotalOffDuration()/mdmDeviceSummary.getTotal());
							aaPowerOff.setAveOffTimes((double)sum.getTotalOffTimes()/mdmDeviceSummary.getTotal());
						}
						
						aaPowerOff.setCdate(new Date());
						aaPowerOffDao.insertSelective(aaPowerOff);
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 按台区统计
	 * @param day
	 */
	private void statisticByStation(String month){
		MdmAaPowerOff aaPowerOff = new MdmAaPowerOff();
		aaPowerOff.setWay("2");		  //按台区
		aaPowerOff.setDataType("1");  //月冻结
		aaPowerOff.setNowTime(month);
		//删除旧记录
		aaPowerOffDao.deleteByOld(aaPowerOff);
		
		List<MdmAmTm> tmList = amTmDao.selectByAll();
		if(tmList == null){
			return;
		}
		for (MdmAmTm amTm : tmList) {
			try {
				MdmAmDevice amDevice = new MdmAmDevice();
				amDevice.setTmId(amTm.getId());
				VtMdmDeviceSummary mdmDeviceSummary = amDeviceDao.selectSummary(amDevice);
				
				aaPowerOff.setRefId(amTm.getId());
				aaPowerOff.setRefContent(amTm.getDescription());
				aaPowerOff.setUserCount(mdmDeviceSummary.getTotal());
				aaPowerOff.setImportantCount(mdmDeviceSummary.getImpCount());
				
				VtMdmAaPowerOffDetail aaPowerOffDetail = new VtMdmAaPowerOffDetail();
				aaPowerOffDetail.setTmId(amTm.getId());
				aaPowerOffDetail.setDateStr(month);
				
				for(int i=0;i<offTypes.length;i++) {
					aaPowerOffDetail.setOffType(offTypes[i]);
					List<VtMdmAaPowerOffSummary> sumList = aaPowerOffDetailDao.selectSummary(aaPowerOffDetail);
					if(sumList == null){
						continue;
					}
					aaPowerOff.setOffType(offTypes[i]);
					for (VtMdmAaPowerOffSummary sum : sumList) {
						aaPowerOff.setId(MdmCodeUtil.getPowerOffCode());
						aaPowerOff.setDeptId(sum.getDeptId());
						aaPowerOff.setOrgId(sum.getOrgId());
						aaPowerOff.setOffUserCount(sum.getOffUserCount());
						aaPowerOff.setOffThreeUserCount(sum.getOffThreeUserCount());
						aaPowerOff.setImportantOffDuration(sum.getTotalOffDurationImp());
						aaPowerOff.setImportantOffTimes(sum.getTotalOffTimesImp());
						aaPowerOff.setTotalOffDuration(sum.getTotalOffDuration());
						aaPowerOff.setTotalOffTimes(sum.getTotalOffTimes());
						if(mdmDeviceSummary.getTotal() == 0){
							aaPowerOff.setAveOffDuration(0.0);
							aaPowerOff.setAveOffTimes(0.0);
						}
						else{
							aaPowerOff.setAveOffDuration(sum.getTotalOffDuration()/mdmDeviceSummary.getTotal());
							aaPowerOff.setAveOffTimes((double)sum.getTotalOffTimes()/mdmDeviceSummary.getTotal());
						}
						
						aaPowerOff.setCdate(new Date());
						aaPowerOffDao.insertSelective(aaPowerOff);
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
