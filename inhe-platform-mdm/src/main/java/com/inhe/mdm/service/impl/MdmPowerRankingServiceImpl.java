package com.inhe.mdm.service.impl;

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
import com.inhe.mdm.dao.MdmAaKwhDao;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.model.MdmAaKwh;
import com.inhe.mdm.model.VtMdmAaKwhDetail;
import com.inhe.mdm.service.IMdmPowerRankingService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;

/**   
* Description :  电量排名分析服务
* @author : xd
* @Date : 2020年12月29日  
*/ 
@Service
public class MdmPowerRankingServiceImpl implements IMdmPowerRankingService {
	
	@Autowired
	MdmAaKwhDao kwhDao;
	
	@Autowired
	MdmAaKwhDetailDao kwhDetaiDao;
	
	@Autowired
	MdmUtil mdmUtil;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmPowerRankingServiceImpl.class);
	
	@Override
	public PageBean<MdmAaKwh> queryListByLine(MdmAaKwh param, int pageNo, int pageSize){
		int totalCount = kwhDao.selectCount(param);
		List<MdmAaKwh> rows = kwhDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<MdmAaKwh> pageBean = new PageBean<MdmAaKwh>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public PageBean<Map<String, Object>> queryListByMeter(VtMdmAaKwhDetail param, int pageNo, int pageSize){
		int totalCount = kwhDetaiDao.selectCountByMeter(param);
		List<Map<String, Object>> rows = kwhDetaiDao.selectListByMeter(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		for(Map<String, Object> list:rows){
			if(list.get("totalKwh") == null){
				list.put("totalKwh", "-");
			}
			if(list.get("totalKwhStart") == null){
				list.put("totalKwhStart", "-");
			}
			if(list.get("totalKwhEnd") == null){
				list.put("totalKwhEnd", "-");
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
	public boolean exportPowerDataByLine(MdmAaKwh mdmAaKwh, String isoCode, HttpServletResponse response) throws Exception {

		List<MdmAaKwh> list = kwhDao.selectList(mdmAaKwh, 0, 9999);
		JSONObject analysisJson = mdmUtil.getLang(isoCode, "analysis");// 获取多语言
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");
		JSONObject powerJson = mdmUtil.getLang(isoCode, "power");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		String[] headers = {
							lineJson.getString("name"),
							transformerJson.getString("info2"),
							powerJson.getString("index2"),
							powerJson.getString("index3"),
							powerJson.getString("index4"),
							powerJson.getString("index5"),
							powerJson.getString("index6")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, analysisJson.getString("type"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (MdmAaKwh kwh : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(kwh.getDeptId());
			sysDept.setOrgId(kwh.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { 
					kwh.getRefContent(),
					deptName,
					kwh.getTotalKwh().toString(),
					kwh.getTotalKwhStart().toString(),
					kwh.getTotalKwhEnd().toString(),
					kwh.getYearBasis().toString(),
					kwh.getLinkRelativeRatio().toString()
					};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, analysisJson.getString("type"));
		return true;
	}
	
	@Override
	public boolean exportPowerDataByMeter(VtMdmAaKwhDetail param, String isoCode, HttpServletResponse response) throws Exception {

 		List<Map<String, Object>> list = kwhDetaiDao.selectListByMeter(param, 0, 9999);
		JSONObject dataeditJson = mdmUtil.getLang(isoCode, "dataedit");// 获取多语言
		JSONObject deviceJson = mdmUtil.getLang(isoCode, "device");
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");
		JSONObject powerJson = mdmUtil.getLang(isoCode, "power");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		JSONObject stealJson = mdmUtil.getLang(isoCode, "steal");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = {
							dataeditJson.getString("index8"),
							deviceJson.getString("index2"),
							deviceJson.getString("index4"),
							powerJson.getString("index7"),
							lineJson.getString("name"),
							transformerJson.getString("info2"),
							powerJson.getString("index2"),
							powerJson.getString("index3"),
							powerJson.getString("index4")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, stealJson.getString("showtype1"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		String meterSort = null;
		String important = null;
		for (Map<String, Object> kwhDetail : list) {
		    if(kwhDetail.get("totalKwh") == null){
		        kwhDetail.put("totalKwh", "-");
		    }
		    if(kwhDetail.get("totalKwhStart") == null){
		        kwhDetail.put("totalKwhStart", "-");
		    }
		    if(kwhDetail.get("totalKwhEnd") == null){
		        kwhDetail.put("totalKwhEnd", "-");
		    }
			row = sheet.createRow(rowIndex++);// 创建行
			Set<String> keys = dctJson.keySet();
			for (String key : keys) {
				if (key.startsWith("METER_SORT") && key.endsWith(kwhDetail.get("meterSort").toString())) {
					meterSort = dctJson.getString(key);
				}
			}
			if ("Y".equals(kwhDetail.get("important").toString())) {
				important = dctJson.getString("TASK_EXECINGY");
			} else {
				important = dctJson.getString("TASK_EXECINGN");
			}
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(kwhDetail.get("deptId").toString());
			sysDept.setOrgId(kwhDetail.get("orgId").toString());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String lineName = "-";
			if(kwhDetail.get("lineName") != null){
				lineName = kwhDetail.get("lineName").toString();
			}
			String[] obj = { 
					 kwhDetail.get("deviceNum").toString(),
					 kwhDetail.get("meterName").toString(),
					 meterSort,
					 important,
					 lineName,
					 deptName,
					 kwhDetail.get("totalKwh").toString(),
					 kwhDetail.get("totalKwhStart").toString(),
					 kwhDetail.get("totalKwhEnd").toString()
					 };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, stealJson.getString("showtype1"));
		return true;
	}

}
