package com.inhe.mdm.service.impl;

import java.util.List;
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
import com.inhe.mdm.dao.MdmAaPowerOffDao;
import com.inhe.mdm.model.MdmAaPowerOff;
import com.inhe.mdm.model.VtMdmAaPowerOff;
import com.inhe.mdm.service.IMdmBlackoutStatisticsService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;

@Service
public class MdmBlackoutStatisticsServiceImpl implements IMdmBlackoutStatisticsService {
	@Autowired
	MdmAaPowerOffDao powerOffDao;
	
	@Autowired
	MdmUtil mdmUtil;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmBlackoutStatisticsServiceImpl.class);

	@Override
	public PageBean<VtMdmAaPowerOff> queryList(MdmAaPowerOff param, int pageNo, int pageSize){
		int totalCount = powerOffDao.selectCount(param);
		List<VtMdmAaPowerOff> rows = null;
		if("0".equals(param.getWay())){
			rows = powerOffDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		}else if("1".equals(param.getWay())){
			rows = powerOffDao.selectListByLine(param, (pageNo - 1) * pageSize, pageSize);
		}else {
			rows = powerOffDao.selectListByTm(param, (pageNo - 1) * pageSize, pageSize);
		}
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaPowerOff> pageBean = new PageBean<VtMdmAaPowerOff>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public boolean exportBlackoutStatisticsDataByDept(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception {
		List<VtMdmAaPowerOff> list = powerOffDao.selectList(param, 0, 9999);
		JSONObject powerdownJson = mdmUtil.getLang(isoCode, "powerdown");
		String[] headers = {
				powerdownJson.getString("power_dept"),
				powerdownJson.getString("power_total"),
				powerdownJson.getString("power_devnum"),
				powerdownJson.getString("power_times"),
				powerdownJson.getString("power_time_hour"),
				powerdownJson.getString("power_total_times"),
				powerdownJson.getString("power_ave_time"),
				powerdownJson.getString("power_ave_times")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, powerdownJson.getString("showtype1"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaPowerOff mdmAaPowerOff : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(mdmAaPowerOff.getRefId());
			sysDept.setOrgId(mdmAaPowerOff.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					deptName,
					mdmAaPowerOff.getUserCount().toString(),
					mdmAaPowerOff.getOffUserCount().toString(),
					mdmAaPowerOff.getOffThreeUserCount().toString(),
					mdmAaPowerOff.getTotalOffDuration().toString(),
					mdmAaPowerOff.getTotalOffTimes().toString(),
					mdmAaPowerOff.getAveOffDuration().toString(),
					mdmAaPowerOff.getAveOffTimes().toString()};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, powerdownJson.getString("showtype1"));
		return true;
	}
	
	@Override
	public boolean exportBlackoutStatisticsDataByLine(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception {
		List<VtMdmAaPowerOff> list = powerOffDao.selectListByLine(param, 0, 9999);
		JSONObject powerdownJson = mdmUtil.getLang(isoCode, "powerdown");
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");
		JSONObject deptJson = mdmUtil.getLang(isoCode, "dept");
		JSONObject analysisJson = mdmUtil.getLang(isoCode, "analysis");
		String[] headers = {
				lineJson.getString("name"),
				deptJson.getString("index"),
				powerdownJson.getString("power_total"),
				powerdownJson.getString("power_devnum"),
				powerdownJson.getString("power_times"),
				powerdownJson.getString("power_time_hour"),
				powerdownJson.getString("power_total_times"),
				powerdownJson.getString("power_ave_time"),
				powerdownJson.getString("power_ave_times")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, analysisJson.getString("type"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaPowerOff mdmAaPowerOff : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(mdmAaPowerOff.getDeptId());
			sysDept.setOrgId(mdmAaPowerOff.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					mdmAaPowerOff.getLineName(),
					deptName,
					mdmAaPowerOff.getUserCount().toString(),
					mdmAaPowerOff.getOffUserCount().toString(),
					mdmAaPowerOff.getOffThreeUserCount().toString(),
					mdmAaPowerOff.getTotalOffDuration().toString(),
					mdmAaPowerOff.getTotalOffTimes().toString(),
					mdmAaPowerOff.getAveOffDuration().toString(),
					mdmAaPowerOff.getAveOffTimes().toString()};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, analysisJson.getString("type"));
		return true;
	}
	
	@Override
	public boolean exportBlackoutStatisticsDataByTm(MdmAaPowerOff param, String isoCode, HttpServletResponse response) throws Exception {
		List<VtMdmAaPowerOff> list = powerOffDao.selectListByTm(param, 0, 9999);
		JSONObject powerdownJson = mdmUtil.getLang(isoCode, "powerdown");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = {
				dctJson.getString("METER_SORT2"),
				transformerJson.getString("info1"),
				transformerJson.getString("info2"),
				powerdownJson.getString("power_total"),
				powerdownJson.getString("power_devnum"),
				powerdownJson.getString("power_times"),
				powerdownJson.getString("power_time_hour"),
				powerdownJson.getString("power_total_times"),
				powerdownJson.getString("power_ave_time"),
				powerdownJson.getString("power_ave_times")
				};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, powerdownJson.getString("showtype1"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaPowerOff mdmAaPowerOff : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(mdmAaPowerOff.getDeptId());
			sysDept.setOrgId(mdmAaPowerOff.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = {
					mdmAaPowerOff.getTmName(),
					mdmAaPowerOff.getLineName(),
					deptName,
					mdmAaPowerOff.getUserCount().toString(),
					mdmAaPowerOff.getOffUserCount().toString(),
					mdmAaPowerOff.getOffThreeUserCount().toString(),
					mdmAaPowerOff.getTotalOffDuration().toString(),
					mdmAaPowerOff.getTotalOffTimes().toString(),
					mdmAaPowerOff.getAveOffDuration().toString(),
					mdmAaPowerOff.getAveOffTimes().toString()};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, powerdownJson.getString("showtype1"));
		return true;
	}
}
