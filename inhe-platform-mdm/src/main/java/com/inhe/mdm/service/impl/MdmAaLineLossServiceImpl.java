package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaLineLossDao;
import com.inhe.mdm.model.VtMdmAaLineLoss;
import com.inhe.mdm.service.IMdmAaLineLossService;

/**
 * @Description : 线损分析服务
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAaLineLossServiceImpl implements IMdmAaLineLossService {
	@Autowired
	MdmAaLineLossDao lineLossDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAaLineLossServiceImpl.class);

	@Override
	public PageBean<Map<String, Object>> queryLineLossList(VtMdmAaLineLoss param, int pageNo, int pageSize) {
		List<VtMdmAaLineLoss> lists = lineLossDao.selectDictionaryByTableType(param);
		String field = "";
		String min = "";
		String max = "";
		String[] lineRangeArray = null;
		for (VtMdmAaLineLoss list : lists) {
			String lineRangeDescription = list.getDescription();

			if (lineRangeDescription.contains(",")) {
				lineRangeArray = lineRangeDescription.substring(1, lineRangeDescription.length() - 1).split(",");
				List<String> lineRangeList = Arrays.asList(lineRangeArray);
				min = lineRangeList.get(0);
				max = lineRangeList.get(1);
			} else {
				min = lineRangeDescription;
			}
			if (min.startsWith("<")) {
				field += "sum(case when LOSS_RATE < " + min.substring(1) + " then 1 else 0 end) "
						+ "\"" + lineRangeDescription + "\",";
			}else if (min.startsWith(">")) {
				field += "sum(case when LOSS_RATE >= " + min.substring(1) + " then 1 else 0 end) "
						+ "\"" + lineRangeDescription + "\",";
			}else {
				field += "sum(case when LOSS_RATE >= " + min  + " and LOSS_RATE < " + max + " then 1 else 0 end)"
						+ "\"[" + lineRangeDescription + ")\",";
			}
		}
		int totalCount = lineLossDao.selectLineLossNumCount(param);
		param.setField(field.substring(0,field.length() - 1));
		List<Map<String, Object>> rows = lineLossDao.selectLineLossNumList(param,(pageNo - 1) * pageSize, pageSize);
		List<Map<String, Object>> row = new ArrayList<Map<String,Object>>();
		if(rows.get(0) == null){
			rows = row;
		}
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
}
