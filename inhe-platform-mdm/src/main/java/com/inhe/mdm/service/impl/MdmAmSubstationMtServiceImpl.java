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
import com.inhe.mdm.dao.MdmAmSubstationMtDao;
import com.inhe.mdm.model.MdmAmSubstationMt;
import com.inhe.mdm.model.VtMdmAmSubstationMt;
import com.inhe.mdm.service.IMdmAmSubstationMtService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.utils.ExcelUtil;

/**
 * Description : 变电站主变
 * 
 * @author : xd
 * @Date : 2020年12月11日
 */
@Service
public class MdmAmSubstationMtServiceImpl implements IMdmAmSubstationMtService {
	@Autowired
	MdmAmSubstationMtDao substationMtDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAmSubstationMtServiceImpl.class);

	@Override
	public PageBean<VtMdmAmSubstationMt> queryList(VtMdmAmSubstationMt param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = substationMtDao.selectCount(param);
		List<VtMdmAmSubstationMt> rows = substationMtDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAmSubstationMt> pageBean = new PageBean<VtMdmAmSubstationMt>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public boolean insert(MdmAmSubstationMt mdmAmSubstationMt) {
		String deptId = substationMtDao.selectDeptIdByStationId(mdmAmSubstationMt);
		String maxId = substationMtDao.selectMaxId(mdmAmSubstationMt);
		mdmAmSubstationMt.setId(MdmCodeUtil.getAMStationId(maxId, deptId));
		if (mdmAmSubstationMt.getCode() == null) {
			mdmAmSubstationMt.setCode(mdmAmSubstationMt.getId());
		}
		mdmAmSubstationMt.setDeptId(deptId);
		mdmAmSubstationMt.setCdate(new Date());
		substationMtDao.insertSelective(mdmAmSubstationMt);
		return true;
	}

	@Override
	public boolean update(MdmAmSubstationMt mdmAmSubstation) {
		Integer substationMt = substationMtDao.updateByPrimaryKeySelective(mdmAmSubstation);
		if (substationMt == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public VtMdmAmSubstationMt detail(VtMdmAmSubstationMt mdmAmSubstation) {
		VtMdmAmSubstationMt substationMt = substationMtDao.selectDesByPrimaryKey(mdmAmSubstation);
		if (substationMt == null) {
			throw new InheExceptionBase(-108006, "找不到指定变电站主变");
		}
		return substationMt;
	}

	@Override
	public Boolean delete(MdmAmSubstationMt mdmAmSubstation) {
		Integer result = substationMtDao.delete(mdmAmSubstation);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		return true;
	}

	@Override
	public boolean switchType(MdmAmSubstationMt mdmAmSubstation) {
		MdmAmSubstationMt substationMt = substationMtDao.selectByPrimaryKey(mdmAmSubstation.getId());
		if (substationMt == null) {
			throw new InheExceptionBase(-108006, "找不到指定变电站主变");
		}
		substationMt.setStatus(mdmAmSubstation.getStatus());
		int result = substationMtDao.updateByPrimaryKeySelective(substationMt);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public JSONObject upload(MultipartFile file, MdmAmSubstationMt mdmAmSubstationMt) throws Exception {
		// 数据处理
		if (file.isEmpty()) {
			throw new InheExceptionBase(-101205, "请选择一个文件上传");
		}
		String fileName = file.getOriginalFilename();
		if (!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
			throw new InheExceptionBase(-108003, "请选择后缀为xlsx或xls的文件");
		}
		JSONObject result = new JSONObject();
		List<ArrayList<String>> row = ExcelUtil.analysis(file, 3);
		result.put("total", row.size());
		List<JSONObject> failSubstationMt = new ArrayList<JSONObject>();
		for (int i = 0; i < row.size(); i++) {
			List<String> cell = row.get(i);
			JSONObject data = new JSONObject();
			try {
				data.put("code", cell.get(1));
				data.put("name", cell.get(2));
				for (int j = 0; j < cell.size(); j++) {
					if (j == 1 || j == 2) {
						if ("".equals(cell.get(j)) || cell.get(j) == null) {
							throw new InheExceptionBase(-108004, "文件内容格式错误");
						}
					}
				}
				mdmAmSubstationMt.setCode(cell.get(1));
				MdmAmSubstationMt oldStation = substationMtDao.selectByStationCode(mdmAmSubstationMt);
				if (oldStation != null) {
					throw new InheExceptionBase(-108007, "变电站主变已经存在:" + cell.get(1));
				}
				String deptId = substationMtDao.selectDeptIdByStationId(mdmAmSubstationMt);
				if (deptId == null || deptId.indexOf(deptId) != 0) {
					throw new InheExceptionBase(-101082, "超过当前操作员所在部门范围");
				}
				mdmAmSubstationMt.setName(cell.get(2));
				mdmAmSubstationMt.setStationId(mdmAmSubstationMt.getStationId());
				mdmAmSubstationMt.setStatus("0");
				mdmAmSubstationMt.setOrgId(mdmAmSubstationMt.getOrgId());
				mdmAmSubstationMt.setDeptId(deptId);
				mdmAmSubstationMt.setCoperator(mdmAmSubstationMt.getCoperator());
				this.insert(mdmAmSubstationMt);
			} catch (InheExceptionBase e) {
				data.put("errorCode", e.getErrorCode());
				data.put("result", e.getMessage());
				failSubstationMt.add(data);
				logger.error("insert fail: " + row.get(i), e);
				e.printStackTrace();
			} catch (Exception e) {
				data.put("errorCode", -10000);
				data.put("result", e.getMessage());
				failSubstationMt.add(data);
				logger.error("insert fail: " + row.get(i), e);
			}
		}
		result.put("success", row.size() - failSubstationMt.size());
		result.put("fail", failSubstationMt.size());
		result.put("failList", failSubstationMt);
		return result;
	}
}