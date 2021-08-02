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
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.dao.MdmAaStealDetailDao;
import com.inhe.mdm.model.MdmAaStealDetail;
import com.inhe.mdm.model.VtMdmAaStealDetail;
import com.inhe.mdm.service.IMdmAaStealDetailService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;

/**
 * @Description : 窃电分析服务
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAaStealDetailServiceImpl implements IMdmAaStealDetailService {

	@Autowired
	MdmAaStealDetailDao stealDetailDao;
	
	@Autowired
	MdmAaKwhDetailDao kwhDetailDao;

	@Autowired
	MdmUtil mdmUtil;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(MdmAaStealDetailServiceImpl.class);

	@Override
	public PageBean<VtMdmAaStealDetail> queryListByDeviceNum(VtMdmAaStealDetail param, int pageNo, int pageSize) {
		int totalCount = stealDetailDao.selectCountByDeviceNum(param);
		List<VtMdmAaStealDetail> rows = stealDetailDao.selectListByDeviceNum(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaStealDetail> pageBean = new PageBean<VtMdmAaStealDetail>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public PageBean<VtMdmAaStealDetail> queryListByEvent(VtMdmAaStealDetail param, int pageNo, int pageSize) {
		int totalCount = stealDetailDao.selectCountByEvent(param);
		List<VtMdmAaStealDetail> rows = stealDetailDao.selectListByEvent(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaStealDetail> pageBean = new PageBean<VtMdmAaStealDetail>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public PageBean<VtMdmAaStealDetail> queryRuleDetailList(VtMdmAaStealDetail param, int pageNo, int pageSize) {
		int totalCount = stealDetailDao.selectRuleDetailCount(param);
		List<VtMdmAaStealDetail> rows = stealDetailDao.selectRuleDetailList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaStealDetail> pageBean = new PageBean<VtMdmAaStealDetail>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public PageBean<Map<String, Object>> queryKwhList(VtMdmAaStealDetail param, int pageNo, int pageSize) {
		int totalCount = kwhDetailDao.selectKwhCountByDeviceId(param);
		List<Map<String, Object>> rows = kwhDetailDao.selectKwhList(param);
		logger.info("rows: - {}", rows);
		for(Map<String, Object> list:rows){
			if(list.get("totalKwh") == null){
				list.put("totalKwh", "-");
			}
			if(list.get("kwhT1") == null){
				list.put("kwhT1", "-");
				list.put("kwhT2", "-");
				list.put("kwhT3", "-");
				list.put("kwhT4", "-");
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
	public boolean update(MdmAaStealDetail param) {
		Integer rule = stealDetailDao.updateByPrimaryKeySelective(param);
		if (rule == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public boolean exportStealDataByDeviceNum(VtMdmAaStealDetail param, String isoCode, HttpServletResponse response)
			throws Exception {

		List<VtMdmAaStealDetail> list = stealDetailDao.selectListByDeviceNum(param, 0, 9999);
		JSONObject stealJson = mdmUtil.getLang(isoCode, "steal");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject dataeditJson = mdmUtil.getLang(isoCode, "dataedit");
		JSONObject meterJson = mdmUtil.getLang(isoCode, "meter");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = { commonJson.getString("status"), commonJson.getString("time"),
				dataeditJson.getString("device_num"), meterJson.getString("index1"), meterJson.getString("index2"),
				stealJson.getString("factor"), transformerJson.getString("info2"), stealJson.getString("event") };
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, stealJson.getString("showtype1"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		String status = null;
		String meterSort = null;
		for (VtMdmAaStealDetail steal : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			if(steal.getStatus().contains("0")){
				status = "0";
			}else {
				status = "1";
			}
			if ("0".equals(status)) {
				status = commonJson.getString("process0");
			} else {
				status = commonJson.getString("process1");
			}
			Set<String> keys = dctJson.keySet();
			for (String key : keys) {
				if (key.startsWith("METER_SORT") && key.endsWith(steal.getMeterSort())) {
					meterSort = dctJson.getString(key);
				}
			}
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(steal.getDeptId());
			sysDept.setOrgId(steal.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { status, steal.getNowTime(), steal.getDeviceNum(), steal.getMeterDescription(), meterSort,
					steal.getSumSuspicion().toString(), deptName, steal.getCountStealId() + "" };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, stealJson.getString("showtype1") + ".xlsx");
		return true;
	}

	@Override
	public boolean exportStealDataByEvent(VtMdmAaStealDetail param, String isoCode, HttpServletResponse response)
			throws Exception {

		List<VtMdmAaStealDetail> list = stealDetailDao.selectListByEvent(param, 0, 9999);
		JSONObject stealJson = mdmUtil.getLang(isoCode, "steal");// 获取多语言
		JSONObject commonJson = mdmUtil.getLang(isoCode, "common");
		JSONObject dataeditJson = mdmUtil.getLang(isoCode, "dataedit");
		JSONObject meterJson = mdmUtil.getLang(isoCode, "meter");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		JSONObject dctJson = mdmUtil.getLang(isoCode, "dct");
		String[] headers = { commonJson.getString("status"), commonJson.getString("time"),
				dataeditJson.getString("device_num"), meterJson.getString("index1"), meterJson.getString("index2"),
				stealJson.getString("index1"), stealJson.getString("index3"), stealJson.getString("factor"),
				transformerJson.getString("info2")};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, stealJson.getString("showtype2"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		String status = null;
		String meterSort = null;
		String stealTheme = null;
		String stealRule = null;
		for (VtMdmAaStealDetail steal : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			if ("0".equals(steal.getStatus())) {
				status = commonJson.getString("process0");
			} else {
				status = commonJson.getString("process1");
			}
			Set<String> dctkeys = dctJson.keySet();
			for (String dctkey : dctkeys) {
				if (dctkey.startsWith("METER_SORT") && dctkey.endsWith(steal.getMeterSort())) {
					meterSort = dctJson.getString(dctkey);
				}
			}
			Set<String> stealkeys = stealJson.keySet();
			for (String stealkey : stealkeys) {
				if (stealkey.startsWith("theme") && stealkey.endsWith(steal.getThemeId())) {
					stealTheme = stealJson.getString(stealkey);
				}
				if (stealkey.startsWith("rule") && stealkey.endsWith(steal.getStealId())) {
					stealRule = stealJson.getString(stealkey);
				}
			}
			SysDepartment sysDept = new SysDepartment();
			sysDept.setDeptId(steal.getDeptId());
			sysDept.setOrgId(steal.getOrgId());
			String deptName = sysDeptServiceImpl.detail(sysDept).getString("deptName");
			String[] obj = { status, steal.getNowTime(), steal.getDeviceNum(), steal.getMeterDescription(), meterSort,
					stealTheme, stealRule, steal.getSuspicion().toString(), deptName};
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, stealJson.getString("showtype2") + ".xlsx");
		return true;
	}
}
