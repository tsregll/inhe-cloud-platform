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
import com.inhe.mdm.dao.MdmAmDeviceDao;
import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.service.IMdmAmDeviceService;
import com.inhe.node.service.impl.SysDeviceServiceImpl;
import com.inhe.mdm.utils.ExcelUtil;
import com.inhe.admin.model.SysDevice;
import com.inhe.utils.DateUtil;

/**
 * @Description : 主（配）网计量
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAmDeviceServiceImpl implements IMdmAmDeviceService {
	@Autowired
	MdmAmDeviceDao amDeviceDao;
	
	@Autowired
	SysDeviceServiceImpl sysDeviceServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MdmAmDeviceServiceImpl.class);

	@Override
	public PageBean<VtMdmAmDevice> queryList(VtMdmAmDevice param, int pageNo, int pageSize) {
		if (param.getStatus() == null) {
			param.setStatus("0");
		}
		int totalCount = amDeviceDao.selectCount(param);
		List<VtMdmAmDevice> rows = amDeviceDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<VtMdmAmDevice> pageBean = new PageBean<VtMdmAmDevice>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public PageBean<SysDevice> publicSearchDeviceList(SysDevice param, int pageNo, int pageSize) throws Exception {
		PageBean<SysDevice> pageBean = sysDeviceServiceImpl.selectTable(param, pageNo, pageSize);
		return pageBean;
	}
	

	@Override
	public boolean insert(MdmAmDevice mdmAmDevice) throws Exception {
		if (mdmAmDevice.getRegDate() == null) {
			mdmAmDevice.setRegDate(new Date());
		}
//		mdmAmDevice.setId(MdmCodeUtil.getDeviceId());
		mdmAmDevice.setCdate(new Date());
		amDeviceDao.insertSelective(mdmAmDevice);
		SysDevice sysDevice = new SysDevice();
		sysDevice.setDevNo(mdmAmDevice.getDeviceNum());
		SysDevice dev =  sysDeviceServiceImpl.detail(sysDevice);
		sysDeviceServiceImpl.devRegister("0", dev.getDevId());//将表注册到SYS_DEVICE_REG表中
		return true;
	}

	@Override
	public boolean update(MdmAmDevice mdmAmDevice) {
		Integer device = amDeviceDao.updateByPrimaryKeySelective(mdmAmDevice);
		if (device == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public VtMdmAmDevice detail(VtMdmAmDevice mdmAmDevice) {
		VtMdmAmDevice device = amDeviceDao.selectDesByPrimaryKey(mdmAmDevice);
		if (device == null) {
			throw new InheExceptionBase(-108012, "找不到指定计量点");
		}
		return device;
	}

	@Override
	public Boolean delete(MdmAmDevice mdmAmDevice) throws Exception{
		Integer result = amDeviceDao.delete(mdmAmDevice);
		if (result == 0) {
			throw new InheExceptionBase(-100054, "删除记录失败");
		}
		MdmAmDevice amDevice = amDeviceDao.selectByPrimaryKey(mdmAmDevice.getId());
		SysDevice sysDevice = new SysDevice();
		sysDevice.setDevNo(amDevice.getDeviceNum());
		SysDevice dev =  sysDeviceServiceImpl.detail(sysDevice);
		sysDeviceServiceImpl.devRegister("1", dev.getDevId());//反注册(将电表信息从reg表里面删除,type="1")
		return true;
	}

	@Override
	public boolean switchType(MdmAmDevice mdmAmDevice) throws Exception{
		MdmAmDevice device = amDeviceDao.selectByPrimaryKey(mdmAmDevice.getId());
		if (device == null) {
			throw new InheExceptionBase(-108012, "找不到指定计量点");
		}
		if("2".equals(device.getStatus())){
			SysDevice sysDevice = new SysDevice();
			sysDevice.setDevNo(device.getDeviceNum());
			SysDevice dev =  sysDeviceServiceImpl.detail(sysDevice);
			sysDeviceServiceImpl.devRegister("0", dev.getDevId());
		}
		device.setStatus(mdmAmDevice.getStatus());
		int result = amDeviceDao.updateByPrimaryKeySelective(device);
		if (result == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}

	@Override
	public JSONObject upload(MultipartFile file, MdmAmDevice mdmAmDevice) throws Exception {
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
		List<JSONObject> failDevice = new ArrayList<JSONObject>();
		for (int i = 0; i < row.size(); i++) {
			List<String> cell = row.get(i);
			JSONObject data = new JSONObject();
			try {
				data.put("deviceNum", cell.get(0));
				data.put("description", cell.get(1));
				for (int j = 0; j < cell.size(); j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 4) {
						if ("".equals(cell.get(j)) || cell.get(j) == null) {
							throw new InheExceptionBase(-108004, "文件内容格式错误");
						}
					}
				}
				mdmAmDevice.setDeviceNum(cell.get(0));
				MdmAmDevice device = amDeviceDao.selectByDevNum(mdmAmDevice);
				if (device != null) {
					throw new InheExceptionBase(-108013, "计量点已经存在:" + cell.get(0));
				}
				mdmAmDevice.setDeviceNum(cell.get(0));
				SysDevice sysDevice = new SysDevice();
				sysDevice.setDevNo(mdmAmDevice.getDeviceNum());
				SysDevice dev=  sysDeviceServiceImpl.detail(sysDevice);
				logger.info("devjson -{}", dev);
				if(dev.getDeptId().indexOf(mdmAmDevice.getDeptId()) != 0){
					throw new InheExceptionBase(-108016, "设备不在变电站或变压器所属部门下");
				}
				if (cell.get(5) != null || "".equals(cell.get(5))) {
					mdmAmDevice.setRegDate(DateUtil.stringToDate(cell.get(5), DateUtil.DATE_WITH_LINE));
				}
				mdmAmDevice.setId(dev.getDevId());
				mdmAmDevice.setAddress(cell.get(6));
				mdmAmDevice.setDescription(cell.get(1));
				mdmAmDevice.setMeterSort(cell.get(2).substring(0, 1));
				mdmAmDevice.setVolLevel(cell.get(3).substring(0, 1));
				mdmAmDevice.setImportant(cell.get(4));
				if (mdmAmDevice.getMeterType() == "0") {
					mdmAmDevice.setMeasMode(cell.get(6).substring(0, 1));
				}
				mdmAmDevice.setAddress(dev.getAddress());
				mdmAmDevice.setMeterType(mdmAmDevice.getMeterType());
				mdmAmDevice.setTmId(mdmAmDevice.getTmId());
				mdmAmDevice.setLineId(mdmAmDevice.getLineId());
				mdmAmDevice.setSubstationId(mdmAmDevice.getSubstationId());
				mdmAmDevice.setStatus("0");
				mdmAmDevice.setOrgId(mdmAmDevice.getOrgId());
				mdmAmDevice.setDeptId(dev.getDeptId());
				mdmAmDevice.setCoperator(mdmAmDevice.getCoperator());
				this.insert(mdmAmDevice);
			} catch (InheExceptionBase e) {
				data.put("errorCode", e.getErrorCode());
				data.put("result", e.getMessage());
				failDevice.add(data);
				logger.error("insert fail: " + row.get(i), e);
				e.printStackTrace();
			} catch (Exception e) {
				data.put("errorCode", -10000);
				data.put("result", e.getMessage());
				failDevice.add(data);
				logger.error("insert fail: " + row.get(i), e);
			}
		}
		result.put("success", row.size() - failDevice.size());
		result.put("fail", failDevice.size());
		result.put("failList", failDevice);
		return result;
	}
}
