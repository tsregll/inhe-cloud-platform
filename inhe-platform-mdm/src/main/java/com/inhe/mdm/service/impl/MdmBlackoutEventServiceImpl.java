package com.inhe.mdm.service.impl;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.admin.model.SysDepartment;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaPowerOffDetailDao;
import com.inhe.mdm.model.VtMdmAaPowerOffDetail;
import com.inhe.mdm.service.IMdmBlackoutEventService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;
import com.inhe.utils.DateUtil;

@Service
public class MdmBlackoutEventServiceImpl implements IMdmBlackoutEventService {
	@Autowired
	MdmAaPowerOffDetailDao powerOffDetailDao;
	
	@Autowired
	MdmUtil mdmUtil;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmPowerCompositionServiceImpl.class);

	@Override
	public PageBean<VtMdmAaPowerOffDetail> queryList(VtMdmAaPowerOffDetail param, int pageNo, int pageSize){
		List<String> deviceIds = powerOffDetailDao.selectDeviceNumCount(param.getTimesFlag());
		int totalCount = powerOffDetailDao.selectCount(deviceIds,param);
		List<VtMdmAaPowerOffDetail> rows = powerOffDetailDao.selectList(deviceIds,param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaPowerOffDetail> pageBean = new PageBean<VtMdmAaPowerOffDetail>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public boolean exportBlackoutEventData(VtMdmAaPowerOffDetail param, String isoCode, HttpServletResponse response) throws Exception {
		List<String> deviceIds = powerOffDetailDao.selectDeviceNumCount(param.getTimesFlag());
		List<VtMdmAaPowerOffDetail> list = powerOffDetailDao.selectList(deviceIds,param, 0, 9999);
		JSONObject functionJson = mdmUtil.getLang(isoCode, "function");// 获取多语言
		JSONObject powerdownJson = mdmUtil.getLang(isoCode, "powerdown");
		JSONObject meterJson = mdmUtil.getLang(isoCode, "meter");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		JSONObject dataeditJson = mdmUtil.getLang(isoCode, "dataedit");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = {
							dataeditJson.getString("device_num"),
							meterJson.getString("index2"),
							powerdownJson.getString("power_type"),
							transformerJson.getString("info2"),
							meterJson.getString("index4"),
							powerdownJson.getString("power_start_time"),
							powerdownJson.getString("power_end_time"),
							powerdownJson.getString("power_hour"),
							powerdownJson.getString("power_tm"),
							transformerJson.getString("info1")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, functionJson.getString("_43"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		String offType = null;
		String important = null;
		String meterSort = null;
		for (VtMdmAaPowerOffDetail mdmAaPowerOffDetail : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			if("1".equals(mdmAaPowerOffDetail.getOffType())){
				offType = powerdownJson.getString("power_type1");
			}else{
				offType = powerdownJson.getString("power_type2");
			}
			if("Y".equals(mdmAaPowerOffDetail.getImportant())){
				important = powerdownJson.getString("impFlag_Y");
			}else{
				important = powerdownJson.getString("impFlag_N");
			}
			Set<String> keys = dctJson.keySet();
			for (String key : keys) {
				if (key.startsWith("METER_SORT") && key.endsWith(mdmAaPowerOffDetail.getMeterSort())) {
					meterSort = dctJson.getString(key);
				}
			}
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(mdmAaPowerOffDetail.getDeptId());
			sysDept.setOrgId(mdmAaPowerOffDetail.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					mdmAaPowerOffDetail.getDeviceNum(),
					meterSort,
					offType,
					deptName,
					important,
					DateUtil.dateToString(mdmAaPowerOffDetail.getStartTime(), DateUtil.DATE_TIME_WITH_LINE),
					DateUtil.dateToString(mdmAaPowerOffDetail.getEndTime(), DateUtil.DATE_TIME_WITH_LINE),
					mdmAaPowerOffDetail.getDuration().toString(),
					mdmAaPowerOffDetail.getTmName(),
					mdmAaPowerOffDetail.getLineName()};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, functionJson.getString("_43"));
		return true;
	}

}
