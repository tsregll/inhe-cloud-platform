package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAmLineDao;
import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.VtMdmAmLine;
import com.inhe.mdm.service.IMdmAmLineService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.utils.ExcelUtil;

/**
 * @Description : 线路服务
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAmLineServiceImpl implements IMdmAmLineService {
	@Autowired
	MdmAmLineDao lineDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAmLineServiceImpl.class);

	@Override
	public PageBean<VtMdmAmLine> queryList(VtMdmAmLine param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = lineDao.selectCount(param);
		List<VtMdmAmLine> rows = lineDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAmLine> pageBean = new PageBean<VtMdmAmLine>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public boolean insert(MdmAmLine mdmAmLine) {
		String deptId = lineDao.selectDeptIdByStationId(mdmAmLine);
		String maxId = lineDao.selectMaxId(mdmAmLine);
		mdmAmLine.setId(MdmCodeUtil.getAMStationId(maxId, deptId));
		if (mdmAmLine.getLineCode() == null) {
			mdmAmLine.setLineCode(mdmAmLine.getId());
		}
		mdmAmLine.setCdate(new Date());
		lineDao.insertSelective(mdmAmLine);
		return true;
	}

	@Override
	public boolean update(MdmAmLine mdmAmLine) {
		Integer substationMt = lineDao.updateByPrimaryKeySelective(mdmAmLine);
		if (substationMt == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public VtMdmAmLine detail(VtMdmAmLine mdmAmLine) {
		VtMdmAmLine line = lineDao.selectDesByPrimaryKey(mdmAmLine);
		if (line == null) {
			throw new InheExceptionBase(-108008, "找不到指定线路");
		}
		return line;
	}

	@Override
	public Boolean delete(MdmAmLine mdmAmLine) {
		Integer result = lineDao.delete(mdmAmLine);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		return true;
	}

	@Override
	public boolean switchType(MdmAmLine mdmAmLine) {
		MdmAmLine line = lineDao.selectByPrimaryKey(mdmAmLine.getId());
		if (line == null) {
			throw new InheExceptionBase(-108008, "找不到指定线路");
		}
		line.setStatus(mdmAmLine.getStatus());
		int result = lineDao.updateByPrimaryKeySelective(line);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public JSONObject upload(MultipartFile file, MdmAmLine mdmAmLine) throws Exception {
		// 数据处理
		if (file.isEmpty()) {
			throw new InheExceptionBase(-101205, "请选择一个文件上传");
		}
		String fileName = file.getOriginalFilename();
		if (!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
			throw new InheExceptionBase(-108003, "请选择后缀为xlsx或xls的文件");
		}
		JSONObject result = new JSONObject();
		List<ArrayList<String>> row = ExcelUtil.analysis(file, 5);
		result.put("total", row.size());
		List<JSONObject> failLine = new ArrayList<JSONObject>();
		for(int i = 0; i < row.size(); i++) {
			List<String> cell = row.get(i);
			JSONObject data = new JSONObject();
			try {
				data.put("description", cell.get(2));
				data.put("lineCode", cell.get(1));
				for (int j = 0; j < cell.size(); j++) {
					if (j != 0) {
						if ("".equals(cell.get(j)) || cell.get(j) == null) {
							throw new InheExceptionBase(-108004, "文件内容格式错误");
						}
					}
				}
				mdmAmLine.setLineCode(cell.get(1));
				MdmAmLine oldStation = lineDao.selectByLineCode(mdmAmLine);
				if (oldStation != null) {
					throw new InheExceptionBase(-108009, "线路已经存在:" + cell.get(1));
				}
				mdmAmLine.setDescription(cell.get(2));
				mdmAmLine.setSort(cell.get(3).substring(0, 1));
				mdmAmLine.setType(cell.get(4).substring(0, 1));
				mdmAmLine.setStationId(mdmAmLine.getStationId());
				mdmAmLine.setStatus("0");
				mdmAmLine.setOrgId(mdmAmLine.getOrgId());
				mdmAmLine.setCoperator(mdmAmLine.getCoperator());
				this.insert(mdmAmLine);
			} catch (InheExceptionBase e) {
				data.put("errorCode", e.getErrorCode());
				data.put("result", e.getMessage());
				failLine.add(data);
				logger.error("insert fail: " + row.get(i), e);
				e.printStackTrace();
			} catch (Exception e) {
				data.put("errorCode", -10000);
				data.put("result", e.getMessage());
				failLine.add(data);
				logger.error("insert fail: " + row.get(i), e);
			}
		}
		result.put("success", row.size() - failLine.size());
		result.put("fail", failLine.size());
		result.put("failList", failLine);
		return result;
	}
}
