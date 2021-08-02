package com.inhe.mdm.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.inhe.build.exception.InheExceptionBase;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

public class ExcelUtil {
	public ExcelUtil() {
		throw new InheExceptionBase(-10000, "工具类不允许实例化！");
	}

	/**
	 * 获取并解析excel文件，返回一个二维集合
	 * 
	 * @param file 上传的文件
	 *            
	 * @return 二维集合（第一重集合为行，第二重集合为列，每一行包含该行的列集合，列集合包含该行的全部单元格的值）
	 */
	public static ArrayList<ArrayList<String>> analysis(MultipartFile file, int cellNum) {
		ArrayList<ArrayList<String>> row = new ArrayList<>();
		// 获取文件名称
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);

		try {
			// 获取输入流
			InputStream in = file.getInputStream();
			// 判断excel版本
			Workbook workbook = null;
			if (judegExcelEdition(fileName)) {
				workbook = new XSSFWorkbook(in);
			} else {
				workbook = new HSSFWorkbook(in);
			}

			// 循环获取每张工作表
			// for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
			Sheet sheet = workbook.getSheetAt(0);
			// 从第二行开始获取
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				// 循环获取工作表的每一行
				Row sheetRow = sheet.getRow(i);
				// 循环获取每一列
				ArrayList<String> cell = new ArrayList<>();
				for (int j = 0; j < cellNum; j++) {
					// 将每一个单元格的值装入列集合
					if (sheetRow.getCell(j) != null) {
						cell.add(getCellValue(sheetRow.getCell(j)));
					} else {
						cell.add(null);
					}
				}
				// 将装有每一列的集合装入大集合
				row.add(cell);

				// 关闭资源
				workbook.close();
			}
			// }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("===================未找到文件======================");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("===================上传失败======================");
		}
		return row;
	}

	/**
	 * 判断上传的excel文件版本（xls为2003，xlsx为2017）
	 * 
	 * @param fileName
	 *            文件路径
	 * @return excel2007及以上版本返回true，excel2007以下版本返回false
	 */
	private static boolean judegExcelEdition(String fileName) {
		if (fileName.matches("^.+\\.(?i)(xls)$")) {
			return false;
		} else {
			return true;
		}
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		// 以下是判断数据的类型
		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()))
						.toString();
			} else {
				DataFormatter dataFormatter = new DataFormatter();
				cellValue = dataFormatter.formatCellValue(cell);
			}
			break;
		case STRING: // 字符串
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN: // Boolean
			cellValue = cell.getBooleanCellValue() + "";
			break;
		case FORMULA: // 公式
			cellValue = cell.getCellFormula() + "";
			break;
		case BLANK: // 空值
			cellValue = "";
			break;
		case ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
	
	/**
	 * 数据填充
	 * @param templateName
	 * @param headers
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public static Workbook fillExcelDataWithTemplate(String templateName, String[] headers, String sheetName) throws IOException {
		InputStream in = ExcelUtil.class.getResourceAsStream("/excelTemplate/" + templateName);
		XSSFWorkbook xsWb = new XSSFWorkbook(in);
		SXSSFWorkbook wb = new SXSSFWorkbook(xsWb);
		
		CellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
		wb.setSheetName(0, sheetName);
		Font font = wb.createFont();// 创建字体样式
		// 设置字体样式
		font.setFontName("Arial");
		font.setBold(true);
		font.setFontHeightInPoints((short) 11);
		// 设置单元格样式
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		
		Sheet sheet = wb.getSheetAt(0);// 获取sheet页
		int rowIndex = 0;
		Row row = null;
		row = sheet.createRow(rowIndex);
		// 设置第一行数据和样式
		for (int i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(cellStyle);
			sheet.setColumnWidth(i, 25 * 256);
		}
		return wb;
	}
	
	/**
	 * 导出设置
	 * @param response
	 * @param wb
	 * @param fileName
	 * @throws Exception
	 */
	public static void export(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		wb.close();
	}
	
}
