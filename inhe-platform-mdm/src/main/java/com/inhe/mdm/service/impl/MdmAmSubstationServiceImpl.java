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
import com.inhe.mdm.dao.MdmAmSubstationDao;
import com.inhe.mdm.model.MdmAmSubstation;
import com.inhe.mdm.model.VtMdmAmSubstation;
import com.inhe.mdm.service.IMdmAmSubstationService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.utils.ExcelUtil;

@Service
public class MdmAmSubstationServiceImpl implements IMdmAmSubstationService {

	@Autowired
	MdmAmSubstationDao substationDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAmSubstationServiceImpl.class);

	@Override
	public PageBean<VtMdmAmSubstation> queryList(VtMdmAmSubstation param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = substationDao.selectCount(param);
		List<VtMdmAmSubstation> rows = substationDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAmSubstation> pageBean = new PageBean<VtMdmAmSubstation>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public boolean insert(MdmAmSubstation mdmAmSubstation) {
		String maxId = substationDao.selectMaxId(mdmAmSubstation);
		mdmAmSubstation.setId(MdmCodeUtil.getAMStationId(maxId, mdmAmSubstation.getDeptId()));
		if (mdmAmSubstation.getStationCode() == null) {
			mdmAmSubstation.setStationCode(mdmAmSubstation.getId());
		}
		mdmAmSubstation.setCdate(new Date());
		substationDao.insertSelective(mdmAmSubstation);
		return true;
	}

	@Override
	public boolean update(MdmAmSubstation mdmAmSubstation) {
		Integer substation = substationDao.updateByPrimaryKeySelective(mdmAmSubstation);
		if (substation == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public MdmAmSubstation detail(MdmAmSubstation mdmAmSubstation) {
		MdmAmSubstation substation = substationDao.selectByPrimaryKey(mdmAmSubstation.getId());
		if (substation == null) {
			throw new InheExceptionBase(-108001, "找不到指定变电站");
		}
		return substation;
	}

	@Override
	public Boolean delete(MdmAmSubstation mdmAmSubstation) {
		Integer result = substationDao.delete(mdmAmSubstation);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		return true;
	}

	@Override
	public boolean switchType(MdmAmSubstation mdmAmSubstation) {
		MdmAmSubstation substation = substationDao.selectByPrimaryKey(mdmAmSubstation.getId());
		if (substation == null) {
			throw new InheExceptionBase(-108001, "找不到指定变电站");
		}
		substation.setStatus(mdmAmSubstation.getStatus());
		int result = substationDao.updateByPrimaryKeySelective(substation);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public boolean updateLaLo(MdmAmSubstation mdmAmSubstation) {
		MdmAmSubstation substation = substationDao.selectByPrimaryKey(mdmAmSubstation.getId());
		if (substation == null) {
			throw new InheExceptionBase(-108001, "找不到指定变电站");
		}
		if ("2".equals(substation.getStatus())) {
			throw new InheExceptionBase(-108002, "指定变电站被删除");
		}
		substation.setLa(mdmAmSubstation.getLa());
		substation.setLo(mdmAmSubstation.getLo());
		int result = substationDao.updateByPrimaryKeySelective(substation);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public JSONObject upload(MultipartFile file, MdmAmSubstation mdmAmSubstation) throws Exception {
		// 数据处理
		if (file.isEmpty()) {
			throw new InheExceptionBase(-101205, "请选择一个文件上传");
		}
		String fileName = file.getOriginalFilename();
		if (!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
			throw new InheExceptionBase(-108003, "请选择后缀为xlsx或xls的文件");
		}
		JSONObject result = new JSONObject();
		List<ArrayList<String>> row = ExcelUtil.analysis(file, 6);
		result.put("total", row.size());
		List<JSONObject> failSubstation = new ArrayList<JSONObject>();
		for (int i = 0; i < row.size(); i++) {
			List<String> cell = row.get(i);
			JSONObject data = new JSONObject();
			try {
			data.put("stationCode", cell.get(1));
			data.put("description", cell.get(2));
			for (int j = 0;j < cell.size();j++){
				if(j == 1 || j == 2 || j == 3){
					if ("".equals(cell.get(j)) || cell.get(j) == null) {
						throw new InheExceptionBase(-108004, "文件内容格式错误");
					}
				}
			}
				mdmAmSubstation.setStationCode(cell.get(1));
				MdmAmSubstation oldStation = substationDao.selectByStationCode(mdmAmSubstation);
				if (oldStation != null) {
					throw new InheExceptionBase(-108005, "变电站已经存在:"+ cell.get(1));
				}
				mdmAmSubstation.setDescription(cell.get(2));
				mdmAmSubstation.setVolLevel(cell.get(3).substring(0, 1));
				if (cell.get(4) == null) {
					mdmAmSubstation.setLo(0.0);
				} else {
					mdmAmSubstation.setLo(Double.valueOf(cell.get(4)));
				}
				if (cell.get(5) == null) {
					mdmAmSubstation.setLa(0.0);
				} else {
					mdmAmSubstation.setLa(Double.valueOf(cell.get(5)));
				}
				mdmAmSubstation.setStatus("0");
				mdmAmSubstation.setOrgId(mdmAmSubstation.getOrgId());
				mdmAmSubstation.setDeptId(mdmAmSubstation.getDeptId());
				mdmAmSubstation.setCoperator(mdmAmSubstation.getCoperator());
				this.insert(mdmAmSubstation);
			} catch (InheExceptionBase e) {
				data.put("errorCode", e.getErrorCode());
				data.put("result", e.getMessage());
				failSubstation.add(data);
				System.out.println(failSubstation);
				logger.error("insert fail: " + row.get(i), e);
				e.printStackTrace();
			} catch (Exception e) {
				data.put("errorCode",-10000);
				data.put("result", e.getMessage());
				failSubstation.add(data);
				logger.error("insert fail: " + row.get(i), e);
			}
		}
		result.put("success", row.size() - failSubstation.size());
		result.put("fail", failSubstation.size());
		result.put("failList", failSubstation);
		System.out.println(result);
		return result;
	}

}