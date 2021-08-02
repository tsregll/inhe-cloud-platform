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
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaKwhDao;
import com.inhe.mdm.model.MdmAaKwh;
import com.inhe.mdm.service.IMdmPowerCompositionService;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.mdm.utils.MdmUtil;

/**   
* Description :  电量构成分析服务
* @author : xd
* @Date : 2020年12月29日  
*/ 
@Service
public class MdmPowerCompositionServiceImpl implements IMdmPowerCompositionService {
	
	@Autowired
	MdmAaKwhDao kwhDao;
	
	@Autowired
	MdmUtil mdmUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmPowerCompositionServiceImpl.class);

	@Override
	public PageBean<MdmAaKwh> queryList(MdmAaKwh param, int pageNo, int pageSize) {
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
	public boolean exportPowerData(MdmAaKwh param, String isoCode, HttpServletResponse response) throws Exception {

		List<MdmAaKwh> list = kwhDao.selectList(param, 0, 9999);
		JSONObject functionJson = mdmUtil.getLang(isoCode, "function");// 获取多语言
		JSONObject lineJson = mdmUtil.getLang(isoCode, "line");
		JSONObject powerJson = mdmUtil.getLang(isoCode, "power");
		JSONObject transformerJson = mdmUtil.getLang(isoCode, "transformer");
		String[] headers = {
							transformerJson.getString("info1"),
							lineJson.getString("code"),
							powerJson.getString("index2"),
							powerJson.getString("index9"),
							powerJson.getString("index10"),
							powerJson.getString("index11")
							};
		Workbook wb = ExcelUtil.fillExcelDataWithTemplate("导出模板.xlsx", headers, functionJson.getString("_33"));// 获取工作簿并设置标题行样式
		Sheet sheet = wb.getSheetAt(0);// 获取Sheet页
		int cellNums = sheet.getRow(0).getLastCellNum();// 获取列数
		int rowIndex = 1;
		Row row = null;
		for (MdmAaKwh kwh : list) {
			row = sheet.createRow(rowIndex++);// 创建行
			String[] obj = { 
					kwh.getRefContent(),
					kwh.getRefId(),
					kwh.getTotalKwh().toString(),
					kwh.getPriLinePriTmKwh().toString(),
					kwh.getPubLinePriTmKwh().toString(),
					kwh.getPubLinePubTmKwh().toString() };
			for (int i = 0; i < cellNums; i++) {// 单元格设置值
				row.createCell(i).setCellValue(obj[i]);
			}
		}
		ExcelUtil.export(response, wb, functionJson.getString("_33"));
		return true;
	}

}
