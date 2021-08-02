package com.inhe.mdm.service.impl;

import java.math.BigDecimal;
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
import com.inhe.mdm.dao.MdmAmTmDao;
import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.VtMdmAmTm;
import com.inhe.mdm.service.IMdmAmTmService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.mdm.utils.ExcelUtil;

/**
 * @Description : 变压器服务
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAmTmServiceImpl implements IMdmAmTmService {
	@Autowired
	MdmAmTmDao tmDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAmTmServiceImpl.class);

	@Override
	public PageBean<VtMdmAmTm> queryList(VtMdmAmTm param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = tmDao.selectCount(param);
		List<VtMdmAmTm> rows = tmDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAmTm> pageBean = new PageBean<VtMdmAmTm>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public boolean insert(MdmAmTm mdmAmTm) {
		String maxId = tmDao.selectMaxId(mdmAmTm);
		mdmAmTm.setId(MdmCodeUtil.getAMStationId(maxId, mdmAmTm.getDeptId()));
		if (mdmAmTm.getTmCode() == null) {
			mdmAmTm.setTmCode(mdmAmTm.getId());
		}
		mdmAmTm.setCdate(new Date());
		tmDao.insertSelective(mdmAmTm);
		return true;
	}
	
	@Override
	public boolean update(MdmAmTm mdmAmSubstation) {
		Integer tm = tmDao.updateByPrimaryKeySelective(mdmAmSubstation);
		if (tm == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}
	
	@Override
	public VtMdmAmTm detail(VtMdmAmTm mdmAmSubstation) {
		VtMdmAmTm tm = tmDao.selectDesByPrimaryKey(mdmAmSubstation);
		if (tm == null) {
			throw new InheExceptionBase(-108010, "找不到指定变压器");
		}
		return tm;
	}
	
	@Override
	public Boolean delete(MdmAmTm mdmAmSubstation) {
		Integer result = tmDao.delete(mdmAmSubstation);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		return true;
	}
	
	@Override
	public boolean switchType(MdmAmTm mdmAmTm) {
		MdmAmTm tm = tmDao.selectByPrimaryKey(mdmAmTm.getId());
		if (tm == null) {
			throw new InheExceptionBase(-108010, "找不到指定变压器");
		}
		tm.setStatus(mdmAmTm.getStatus());
		int result = tmDao.updateByPrimaryKeySelective(tm);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}
	
	@Override
	public boolean updateLaLo(MdmAmTm mdmAmTm) {
		MdmAmTm tm = tmDao.selectByPrimaryKey(mdmAmTm.getId());
		if (tm == null) {
			throw new InheExceptionBase(-108010, "找不到指定变压器");
		}
		if ("2".equals(tm.getStatus())) {
			throw new InheExceptionBase(-108014, "指定变压器被删除");
		}
		tm.setLa(mdmAmTm.getLa());
		tm.setLo(mdmAmTm.getLo());
		int result = tmDao.updateByPrimaryKeySelective(mdmAmTm);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}
	
	@Override
	public JSONObject upload(MultipartFile file, MdmAmTm mdmAmTm) throws Exception {
		// 数据处理
		if (file.isEmpty()) {
			throw new InheExceptionBase(-101205, "请选择一个文件上传");
		}
		String fileName = file.getOriginalFilename();
		if (!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
			throw new InheExceptionBase(-108003, "请选择后缀为xlsx或xls的文件");
		}
		JSONObject result = new JSONObject();
		List<ArrayList<String>> row = ExcelUtil.analysis(file, 7);
		result.put("total", row.size());
		List<JSONObject> failTm = new ArrayList<JSONObject>();
		for (int i = 0; i < row.size(); i++) {
			List<String> cell = row.get(i);
			JSONObject data = new JSONObject();
			try{
				data.put("description", cell.get(2));
				data.put("tmCode", cell.get(1));
				for(int j = 0;j < cell.size();j++){
					if(j == 1 || j == 2 || j == 3 || j == 4){
						if ("".equals(cell.get(j)) || cell.get(j) == null) {
							throw new InheExceptionBase(-108004, "文件内容格式错误");
						}
					}
				}
				mdmAmTm.setTmCode(cell.get(1));
				MdmAmTm oldTm = tmDao.selectByTmCode(mdmAmTm);
				if (oldTm != null) {
					throw new InheExceptionBase(-108011, "变压器已经存在:"+ cell.get(1));
				}
				mdmAmTm.setDescription(cell.get(2));
				mdmAmTm.setCapacity(new BigDecimal(cell.get(3)));
				mdmAmTm.setLoadType(cell.get(4).substring(0,1));
				if (cell.get(5) == null) {
					mdmAmTm.setLo(0.0);
				} else {
					mdmAmTm.setLo(Double.valueOf(cell.get(6)));
				}
				if (cell.get(6) == null) {
					mdmAmTm.setLa(0.0);
				} else {
					mdmAmTm.setLa(Double.valueOf(cell.get(7)));
				}
				mdmAmTm.setLineId(mdmAmTm.getLineId());
				mdmAmTm.setStatus("0");
				mdmAmTm.setOrgId(mdmAmTm.getOrgId());
				mdmAmTm.setCoperator(mdmAmTm.getCoperator());
				this.insert(mdmAmTm);
			} catch (InheExceptionBase e) {
				data.put("errorCode", e.getErrorCode());
				data.put("result", e.getMessage());
				failTm.add(data);
				logger.error("insert fail: " + row.get(i), e);
				e.printStackTrace();
			} catch (Exception e) {
				data.put("errorCode",-10000);
				data.put("result", e.getMessage());
				failTm.add(data);
				logger.error("insert fail: " + row.get(i), e);
			}
		}
		result.put("success", row.size() - failTm.size());
		result.put("fail", failTm.size());
		result.put("failList", failTm);
		return result;
	}
}
