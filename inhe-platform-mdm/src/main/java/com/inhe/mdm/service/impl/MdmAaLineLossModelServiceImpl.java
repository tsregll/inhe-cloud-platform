package com.inhe.mdm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.admin.model.SysDepartment;
import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaLineLossModelDao;
import com.inhe.mdm.dao.MdmAaLineLossModelDveDao;
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.model.VtMdmAaLineLossModel;
import com.inhe.mdm.model.VtMdmAaLineLossModelDve;
import com.inhe.mdm.service.IMdmAaLineLossModelService;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.node.service.impl.SysDeptServiceImpl;
import com.inhe.node.service.impl.SysOrgServiceImpl;


/**   
* Description :  线损模型服务
* @author : xd
* @Date : 2021年1月4日  
*/ 
@Service
public class MdmAaLineLossModelServiceImpl implements IMdmAaLineLossModelService {
	
	@Autowired
	MdmAaLineLossModelDao lineLossModelDao;
	
	@Autowired
	MdmAaLineLossModelDveDao lineLossModelDveDao;
	
	@Autowired
	MdmAmDeviceDao amDeviceDao;
	
	@Autowired
	SysDeptServiceImpl sysDeptServiceImpl;
	
	@Autowired
	SysOrgServiceImpl sysOrgServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmAaLineLossModelServiceImpl.class);
	
	/**
	 * Description : 分区线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryPartitionModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize) throws Exception{
		SysDepartment sysDept = new SysDepartment();
		sysDept.setOrgId(param.getOrgId());
		sysDept.setDeptId("001");
		JSONArray deptArray = sysDeptServiceImpl.selectComboRoot(sysDept, true);
		List<String> deptIdLists = new ArrayList<>();
			for(int i = 0;i < deptArray.size();i++){
				JSONObject rootObject = deptArray.getJSONObject(i);
				String deptRoot = rootObject.getString("value");
				deptIdLists.add(deptRoot);
				JSONArray childrenArray = rootObject.getJSONArray("children");
				for(int j = 0;j < childrenArray.size(); j++){
					JSONObject childrenObject = childrenArray.getJSONObject(j);
					deptIdLists.add(childrenObject.getString("value"));
				}
			}
		lineLossModelDao.createDeptTable();
		lineLossModelDao.insertDeptIdBatch(deptIdLists);
		int totalCount = lineLossModelDao.selectCountByPartition(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByPartition(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		lineLossModelDao.deleteDeptTable();
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	/**
	 * Description : 分压线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryVoltageModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize) throws Exception{
		SysDepartment sysDept = new SysDepartment();
		sysDept.setOrgId(param.getOrgId());
		sysDept.setDeptId("001");
		JSONArray deptArray = sysDeptServiceImpl.selectComboRoot(sysDept, true);
		List<String> deptIdLists = new ArrayList<>();
			for(int i = 0;i < deptArray.size();i++){
				JSONObject rootObject = deptArray.getJSONObject(i);
				String deptRoot = rootObject.getString("value");
				deptIdLists.add(deptRoot);
				JSONArray childrenArray = rootObject.getJSONArray("children");
				for(int j = 0;j < childrenArray.size(); j++){
					JSONObject childrenObject = childrenArray.getJSONObject(j);
					deptIdLists.add(childrenObject.getString("value"));
				}
			}
		lineLossModelDao.createDeptTable();
		lineLossModelDao.insertDeptIdBatch(deptIdLists);
		int totalCount = lineLossModelDao.selectCountByVoltage(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByVoltage(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		lineLossModelDao.deleteDeptTable();
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	/**
	 * Description : 分线线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryLineModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize){
		int totalCount = lineLossModelDao.selectCountByLine(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByLine(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	/**
	 * Description : 分台区线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryTmAreaModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize){
		int totalCount = lineLossModelDao.selectCountByTransformerArea(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByTransformerArea(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	/**
	 * Description : 母线不平衡线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryMainLineModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize){
		int totalCount = lineLossModelDao.selectCountByMainLine(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByMainLine(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	/**
	 * Description : 主变线损模型
	 */
	@Override
	public PageBean<VtMdmAaLineLossModel> queryMainTransformerModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize){
		int totalCount = lineLossModelDao.selectCountByMainTransformer(param);
		List<VtMdmAaLineLossModel> rows = lineLossModelDao.selectListByMainTransformer(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModel> pageBean = new PageBean<VtMdmAaLineLossModel>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public PageBean<VtMdmAaLineLossModelDve> queryMeterList(VtMdmAaLineLossModelDve param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = lineLossModelDveDao.selectMeterCount(param);
		List<VtMdmAaLineLossModelDve> rows = lineLossModelDveDao.selectMeterList(param, (pageNo - 1) * pageSize, pageSize);
		for(VtMdmAaLineLossModelDve row : rows){
			if(row.getSupNum() == 0){
				row.setSupplyDire("1");
				row.setMeteringDire("1");
			}
		}
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAaLineLossModelDve> pageBean = new PageBean<VtMdmAaLineLossModelDve>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public PageBean<MdmAaLineLossModelDve> queryModelDevList(MdmAaLineLossModelDve param, int pageNo, int pageSize){
		int totalCount = lineLossModelDveDao.selectDevCount(param);
		List<MdmAaLineLossModelDve> rows = lineLossModelDveDao.selectDevList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<MdmAaLineLossModelDve> pageBean = new PageBean<MdmAaLineLossModelDve>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}
	
	@Override
	public boolean insert(List<VtMdmAaLineLossModel> param) {
		for(VtMdmAaLineLossModel mdmAaLineLossModel : param){
			if(mdmAaLineLossModel.getId() == null || "".equals(mdmAaLineLossModel.getId())){
				mdmAaLineLossModel.setId(MdmCodeUtil.getAaLineLossCode());
			}else{
				lineLossModelDveDao.deleteById(mdmAaLineLossModel.getId());
			}
			mdmAaLineLossModel.setSupplyInCount((short) 0);
			mdmAaLineLossModel.setSupplyOutCount((short) 0);
			mdmAaLineLossModel.setGenerationFlag("1");
			mdmAaLineLossModel.setGenerationWay("0");
			mdmAaLineLossModel.setGenerateTime(new Date());
			mdmAaLineLossModel.setCdate(new Date());
		}
		lineLossModelDao.insertBatchSelective(param);
		for(VtMdmAaLineLossModel mdmAaLineLossAutoModel : param){
			if("2".equals(mdmAaLineLossAutoModel.getSort())){
				List<MdmAaLineLossModelDve> lineSupplyInrows = lineLossModelDveDao.selectAutoSupplyInLineList(mdmAaLineLossAutoModel);
				if(lineSupplyInrows.size() != 0){
					for(MdmAaLineLossModelDve mdmAaLineLossModelDve : lineSupplyInrows){
						mdmAaLineLossModelDve.setSupplyDire("0");
						mdmAaLineLossModelDve.setMeteringDire("0");
					}
				List<MdmAaLineLossModelDve> lineSupplyOutrows = lineLossModelDveDao.selectAutoSupplyOutLineList(mdmAaLineLossAutoModel);
				if(lineSupplyOutrows.size() != 0){
					for(MdmAaLineLossModelDve mdmAaLineLossModelDve : lineSupplyOutrows){
						mdmAaLineLossModelDve.setSupplyDire("1");
						mdmAaLineLossModelDve.setMeteringDire("0");
					}
				}
				lineSupplyInrows.addAll(lineSupplyOutrows);
				lineSupplyInrows.removeAll(Collections.singleton(null));//去除集合中的空元素
				this.insertMeter(lineSupplyInrows, mdmAaLineLossAutoModel.getId());
				}
			}
			if("3".equals(mdmAaLineLossAutoModel.getSort())){
				List<MdmAaLineLossModelDve> tmSupplyInrows = lineLossModelDveDao.selectAutoSupplyInTmList(mdmAaLineLossAutoModel);
				if(tmSupplyInrows.size() != 0){
					for(MdmAaLineLossModelDve mdmAaLineLossModelDve : tmSupplyInrows){
						mdmAaLineLossModelDve.setSupplyDire("0");
						mdmAaLineLossModelDve.setMeteringDire("0");
					}
				List<MdmAaLineLossModelDve> tmSupplyOutrows = lineLossModelDveDao.selectAutoSupplyOutTmList(mdmAaLineLossAutoModel);
				if(tmSupplyOutrows.size() != 0){
					for(MdmAaLineLossModelDve mdmAaLineLossModelDve : tmSupplyOutrows){
						mdmAaLineLossModelDve.setSupplyDire("1");
						mdmAaLineLossModelDve.setMeteringDire("0");
					}
				}
				tmSupplyInrows.addAll(tmSupplyOutrows);
				tmSupplyInrows.removeAll(Collections.singleton(null));
				this.insertMeter(tmSupplyInrows, mdmAaLineLossAutoModel.getId());
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean insertMeter(List<MdmAaLineLossModelDve> param,String id) {
		for(MdmAaLineLossModelDve mdmAaLineLossModelDve : param){
			mdmAaLineLossModelDve.setId(id);
		}
		lineLossModelDveDao.insertBatchSelective(param);
		MdmAaLineLossModel mdmAaLineLossModel = new MdmAaLineLossModel();
		VtMdmAaLineLossModelDve mdmAaLineLossModelDve = new VtMdmAaLineLossModelDve();
		mdmAaLineLossModelDve.setId(id);
	    mdmAaLineLossModelDve = lineLossModelDveDao.selectSumById(mdmAaLineLossModelDve);
	    mdmAaLineLossModel.setId(id);
		mdmAaLineLossModel.setSupplyInCount(mdmAaLineLossModelDve.getSupplyInCount());
		mdmAaLineLossModel.setSupplyOutCount(mdmAaLineLossModelDve.getSupplyOutCount());
		lineLossModelDao.updateByPrimaryKeySelective(mdmAaLineLossModel);
		return true;
	}
	
	@Override
	public boolean update(List<MdmAaLineLossModel> mdmAaLineLossModel) {
	    for(MdmAaLineLossModel model : mdmAaLineLossModel){
	        if(model.getId() == null || "".equals(model.getId())){
	            throw new InheExceptionBase(-108201,"您所选择的记录中有未生成的线损模型");
	        }
	    }
		Integer result = lineLossModelDao.updateBatchSelective(mdmAaLineLossModel);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}
	
	@Override
	public boolean delete(List<String> id) {
	    if(id.size() == 0){
	        throw new InheExceptionBase(-108201,"您所选择的记录中有未生成的线损模型");
	    }
		Date deleteTime = new Date();
		Integer result = lineLossModelDao.deleteBatch(id,"0",deleteTime);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		lineLossModelDveDao.deleteBatch(id);
		return true;
	}
	
	@Override
	public boolean deleteMeter(VtMdmAaLineLossModelDve mdmAaLineLossModelDve) {
		String id = mdmAaLineLossModelDve.getId();
		Integer result = lineLossModelDveDao.deleteByPrimaryKey(mdmAaLineLossModelDve);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		MdmAaLineLossModel mdmAaLineLossModel = new MdmAaLineLossModel();
	    mdmAaLineLossModelDve = lineLossModelDveDao.selectSumById(mdmAaLineLossModelDve);
	    if(mdmAaLineLossModelDve == null){
	    	mdmAaLineLossModel.setId(id);
	    	mdmAaLineLossModel.setSupplyInCount((short) 0);
			mdmAaLineLossModel.setSupplyOutCount((short) 0);
	    }else{
	    mdmAaLineLossModel.setId(id);
		mdmAaLineLossModel.setSupplyInCount(mdmAaLineLossModelDve.getSupplyInCount());
		mdmAaLineLossModel.setSupplyOutCount(mdmAaLineLossModelDve.getSupplyOutCount());
		}
		lineLossModelDao.updateByPrimaryKeySelective(mdmAaLineLossModel);
		return true;
	}

}
