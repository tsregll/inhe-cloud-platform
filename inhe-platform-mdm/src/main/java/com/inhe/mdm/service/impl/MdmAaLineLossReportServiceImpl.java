package com.inhe.mdm.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.inhe.mdm.dao.MdmAaLineLossDao;
import com.inhe.mdm.model.MdmAaLineLoss;
import com.inhe.mdm.model.VtMdmAaLineLoss;
import com.inhe.mdm.service.IMdmAaLineLossReportService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;


/**   
* Description :  线损报表服务
* @author : xd
* @Date : 2021年1月13日  
*/ 
@Service
public class MdmAaLineLossReportServiceImpl implements IMdmAaLineLossReportService {
	
	@Autowired
	MdmAaLineLossDao lineLossDao;
	
	@Autowired
	MdmUtil mdmUtil;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmAaLineLossReportServiceImpl.class);
	
	/**
	 * Description : 报表查询
	 * @param  ： way(0:分区 ,1:分线,2:分台区,3:分压,4:母线不平衡,5:主变  )
	 */
	@Override
	public PageBean<VtMdmAaLineLoss> queryReportList(VtMdmAaLineLoss param, int pageNo, int pageSize){
		if(!("".equals(param.getCode()) || param.getCode() == null)){
		Map<String, String> map = getLineLossMinAndMax(param);
		param.setMin(map.get("min"));
		param.setMax(map.get("max"));}
		int totalCount = lineLossDao.selectReportCount(param);
		List<VtMdmAaLineLoss> rows = null;
		if("0".equals(param.getWay()) || "3".equals(param.getWay())){
			rows = lineLossDao.selectListByDeptOrVol(param, (pageNo - 1) * pageSize, pageSize);
		}
		if("1".equals(param.getWay())){
			param.setWholeYear(param.getNowTime().substring(0,3));
			rows = lineLossDao.selectListByLine(param, (pageNo - 1) * pageSize, pageSize);
		}
		if("2".equals(param.getWay())){
			param.setWholeYear(param.getNowTime().substring(0,3));
			rows = lineLossDao.selectListByTransformerArea(param, (pageNo - 1) * pageSize, pageSize);
		}
		if("4".equals(param.getWay())){
			rows = lineLossDao.selectListByMainLine(param, (pageNo - 1) * pageSize, pageSize);
		}
		if("5".equals(param.getWay())){
			rows = lineLossDao.selectListByMainTransformer(param, (pageNo - 1) * pageSize, pageSize);
		}
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLoss> pageBean = new PageBean<VtMdmAaLineLoss>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	private Map<String, String> getLineLossMinAndMax(VtMdmAaLineLoss param){
		List<VtMdmAaLineLoss> lists = lineLossDao.selectDictionaryByTableType(param);
		String min = "";//负无穷
		String max = "";//正无穷
		String[] lineRangeArray = null;
		Map<String, String> map = new HashMap<>();
		for (VtMdmAaLineLoss list : lists) {
			String lineRangeDescription = list.getDescription();

			if (lineRangeDescription.contains(",")) {
				lineRangeArray = lineRangeDescription.substring(1, lineRangeDescription.length() - 1).split(",");
				List<String> lineRangeList = Arrays.asList(lineRangeArray);
				min = lineRangeList.get(0);
				max = lineRangeList.get(1);
			} else if(lineRangeDescription.startsWith("<")){
				max = lineRangeDescription.substring(1);
			} else{
				min = lineRangeDescription.substring(1);
			}
		}
		map.put("min", min);
		map.put("max", max);
		return map;
	}
	
	@Override
	public PageBean<Map<String, Object>> querySupplyDetailList(VtMdmAaLineLoss param, int pageNo, int pageSize){
		int totalCount = lineLossDao.selectSupplyDetailCount(param);
		List<Map<String, Object>> rows = lineLossDao.selectSupplyDetailList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		for(Map<String, Object> row :rows){
			if(row.get("kwhPaPtct") == null) {
				row.put("kwhPaPtct", 0.0);
			}
			if(row.get("kwhRaPtct") == null) {
				row.put("kwhRaPtct", 0.0);
			}
		}
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public PageBean<MdmAaLineLoss> querylineLossChatList(VtMdmAaLineLoss param, int pageNo, int pageSize){
		int totalCount = lineLossDao.selectlineLossChatCount(param);
		List<MdmAaLineLoss> rows = lineLossDao.selectlineLossChatList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<MdmAaLineLoss> pageBean = new PageBean<MdmAaLineLoss>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public boolean exportDataByDept(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByDeptOrVol(param, 0, 9999);
		JSONObject deptJson = mdmUtil.getLang(isoCode, "dept");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		String[] headers = {
							deptJson.getString("index"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report0"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(lineLoss.getDeptId());
			sysDept.setOrgId(lineLoss.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					 deptName,
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report0"));
		return true;
	}
	
	@Override
	public boolean exportDataByVol(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByDeptOrVol(param, 0, 9999);
		JSONObject deptJson = mdmUtil.getLang(isoCode, "dept");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = {
							deptJson.getString("index"),
							commonJson.getString("vol_level"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report1"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		String volLevelName = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			Set<String> keys = dctJson.keySet();
			for (String key : keys) {
				if (key.startsWith("VOL_LEVEL") && key.endsWith(lineLoss.getRefId())) {
					volLevelName = dctJson.getString(key);
				}
			}
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(lineLoss.getDeptId());
			sysDept.setOrgId(lineLoss.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					 deptName,
					 volLevelName,
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report1"));
		return true;
	}
	
	@Override
	public boolean exportDataByLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByLine(param, 0, 9999);
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		String[] headers = {
							lineJson.getString("name"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report2"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = { 
					 lineLoss.getLineName(),
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report2"));
		return true;
	}
	
	@Override
	public boolean exportMonthDataByLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByLine(param, 0, 9999);
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		String[] headers = {
							lineJson.getString("name"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("year1"),
							lossJson.getString("year2"),
							lossJson.getString("year3"),
							lossJson.getString("year4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report2"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = { 
					 lineLoss.getLineName(),
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getYearSupplyKwh().toString(),
					 lineLoss.getYearSoldKwh().toString(),
					 lineLoss.getYearLossKwh().toString(),
					 lineLoss.getYearLossRate().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report2"));
		return true;
	}
	
	@Override
	public boolean exportDataByTm(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByTransformerArea(param, 0, 9999);
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");// 获取多语言
		JSONObject deptJson = mdmUtil.getLang(isoCode, "dept");
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		String[] headers = {
							transformerJson.getString("name"),
							lineJson.getString("name"),
							deptJson.getString("index"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report3"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(lineLoss.getDeptId());
			sysDept.setOrgId(lineLoss.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String lineName = "-";
			if(lineLoss.getLineName() != null){
				lineName = lineLoss.getLineName();
			}
			String[] obj = { 
					 lineLoss.getTmName(),
					 lineName,
					 deptName,
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report3"));
		return true;
	}
	
	@Override
	public boolean exportMonthDataByTm(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByTransformerArea(param, 0, 9999);
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");// 获取多语言
		JSONObject deptJson = mdmUtil.getLang(isoCode, "dept");
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		String[] headers = {
							transformerJson.getString("name"),
							lineJson.getString("name"),
							deptJson.getString("index"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("year1"),
							lossJson.getString("year2"),
							lossJson.getString("year3"),
							lossJson.getString("year4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("report3"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(lineLoss.getDeptId());
			sysDept.setOrgId(lineLoss.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String lineName = "-";
			if(lineLoss.getLineName() != null){
				lineName = lineLoss.getLineName();
			}
			String[] obj = {
					 lineLoss.getTmName(),
					 lineName,
					 deptName,
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getYearSupplyKwh().toString(),
					 lineLoss.getYearSoldKwh().toString(),
					 lineLoss.getYearLossKwh().toString(),
					 lineLoss.getYearLossRate().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("report3"));
		return true;
	}
	
	@Override
	public boolean exportChartData(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {
		List<MdmAaLineLoss> list = lineLossDao.selectlineLossChatList(param, 0, 9999);
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");// 获取多语言
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		String[] headers = {
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info3"),
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, commonJson.getString("detail"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (MdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = {
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, commonJson.getString("detail"));
		return true;
	}
	
	@Override
	public boolean exportDataByMainLine(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByMainLine(param, 0, 9999);
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		String[] headers = {
							lineJson.getString("info1"),
							lineJson.getString("code1"),
							lineJson.getString("name1"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, lossJson.getString("bus"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = { 
					 lineLoss.getSubstationName(),
					 lineLoss.getLineCode(),
					 lineLoss.getLineName(),
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, lossJson.getString("bus"));
		return true;
	}
	
	@Override
	public boolean exportDataByMainTransformer(VtMdmAaLineLoss param, String isoCode, HttpServletResponse response) throws Exception {

		List<VtMdmAaLineLoss> list = lineLossDao.selectListByMainTransformer(param, 0, 9999);
		JSONObject maintransformerJson = mdmUtil.getLang(isoCode, "maintransformer");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject lossJson = mdmUtil.getLang(isoCode, "loss");
		JSONObject functionJson = mdmUtil.getLang(isoCode, "function");
		String[] headers = {
							maintransformerJson.getString("info1"),
							maintransformerJson.getString("code"),
							maintransformerJson.getString("name"),
							commonJson.getString("date"),
							lossJson.getString("supkwh"),
							lossJson.getString("soldkwh"),
							lossJson.getString("losskwh"),
							lossJson.getString("info6"),
							lossJson.getString("info4"),
							lossJson.getString("info1"),
							lossJson.getString("info2")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, functionJson.getString("_37"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (VtMdmAaLineLoss lineLoss : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = { 
					 lineLoss.getSubstationName(),
					 lineLoss.getLineCode(),
					 lineLoss.getLineName(),
					 lineLoss.getNowTime(),
					 lineLoss.getSupplyKwh().toString(),
					 lineLoss.getSoldKwh().toString(),
					 lineLoss.getLossKwh().toString(),
					 lineLoss.getLossRate().toString(),
					 lineLoss.getLineLoseIndex().toString(),
					 lineLoss.getLinkRelativeRatio().toString(),
					 lineLoss.getYearBasis().toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, functionJson.getString("_41"));
		return true;
	}
}
