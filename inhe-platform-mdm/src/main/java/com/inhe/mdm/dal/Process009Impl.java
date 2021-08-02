package com.inhe.mdm.dal;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.admin.model.SysOrg;
import com.inhe.mdm.dao.MdmAaKwhDetailDao;
import com.inhe.mdm.dao.MdmAaLineLossDao;
import com.inhe.mdm.dao.MdmAaLineLossModelDao;
import com.inhe.mdm.dao.MdmAaLineLossModelDveDao;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAaLineLoss;
import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.node.service.ISysOrgService;
import com.inhe.utils.DateUtil;

/**
 * @description 月线损分析
 * @author ghf
 * @date 2021/01/07
 */
@Service
public class Process009Impl {
    
    private final Logger logger = LoggerFactory.getLogger(Process009Impl.class);
	
	@Autowired
    MdmAaLineLossModelDao mdmAaLineLossModelDao;
    
    @Autowired
    MdmAaLineLossDao mdmAaLineLossDao;
    
    @Autowired
    MdmAaKwhDetailDao mdmAaKwhDetailDao;
    
    @Autowired
    MdmAaLineLossModelDveDao mdmAaLineLossModelDveDao;
    
    @Autowired
    ISysOrgService sysOrgService;
	
	@Autowired
	Process008Impl process008Impl;
	
	public void execute(JSONObject jobj) throws Exception{
	    Date startDate = DateUtil.rollByMonth(jobj.getDate("data_time"), -1);
		String month = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
		logger.info("Process009-start-"+month);
		String id =  jobj.getString("params");
		List<MdmAaLineLossModel> modelList = null;
		if(id != null && !"".equals(id)){		//重新执行一种模型
			modelList = mdmAaLineLossModelDao.selectBySort(id);
		}
		else{									//执行全部模型
			modelList = mdmAaLineLossModelDao.selectByEffective();
		}
		for (MdmAaLineLossModel model : modelList) {
			try {
				statistic(model, startDate);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
	private void statistic(MdmAaLineLossModel mdmAaLineLossModel, Date startDate) throws Exception{
		String month = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
		MdmAaLineLoss mdmAaLineLoss = new MdmAaLineLoss();
		mdmAaLineLoss.setId(MdmCodeUtil.getAaLineLossCode());
		mdmAaLineLoss.setOrgId(mdmAaLineLossModel.getOrgId());
		mdmAaLineLoss.setDeptId(mdmAaLineLossModel.getDeptId());
		mdmAaLineLoss.setLineLoseIndex(mdmAaLineLossModel.getLossIndexMonth());
		String sort = mdmAaLineLossModel.getSort();
        String way = null;
        if("1".equals(sort)) {
            way = "3";
        }else if("2".equals(sort)) {
            way = "1";
        }else if("3".equals(sort)) {
            way = "2";
        }else {
            way = sort;
        }
        mdmAaLineLoss.setWay(way);
		mdmAaLineLoss.setRefId(mdmAaLineLossModel.getRefId());
		mdmAaLineLoss.setDataType("1");
		mdmAaLineLoss.setNowTime(month);
		//删除老的结果
		mdmAaLineLossDao.deleteByOld(mdmAaLineLoss);
		
		JSONObject sysOrgJson = sysOrgService.detail(mdmAaLineLoss.getOrgId());
        if(sysOrgJson == null){
            return;
        }
        JSONObject sysOrgObject = sysOrgJson.getJSONObject("org");
        SysOrg sysOrg = new SysOrg();
        sysOrg.setOrgId(sysOrgObject.getString("orgId"));
        sysOrg.setOrgName(sysOrgObject.getString("orgName"));
        sysOrg.setOrgType(sysOrgObject.getString("orgType"));
		
		Double supplyKwh = 0.0;
		Double soldKwh = 0.0;
		
		List<MdmAaLineLossModelDve> devList = mdmAaLineLossModelDveDao.selectById(mdmAaLineLossModel.getId());
		if(devList == null || devList.size() == 0){
            return;
        }
		for (MdmAaLineLossModelDve dev : devList) {
			MdmAaKwhDetail mdmAaKwhDetail = new MdmAaKwhDetail();
			mdmAaKwhDetail.setDeviceId(dev.getDeviceId());
			mdmAaKwhDetail.setTimeType("1");
			mdmAaKwhDetail.setNowTime(month);
			mdmAaKwhDetail = mdmAaKwhDetailDao.selectByPrimaryKey(mdmAaKwhDetail);
			if(mdmAaKwhDetail == null) {//用电量不存在
                logger.error("AaKwhDetail not exist:deviceNum-{},month-{}",dev.getDeviceNum(),month);
            }else {
                if("0".equals(dev.getSupplyDire())){        //供入
                    if("0".equals(dev.getMeteringDire())){      //正向
                        supplyKwh += mdmAaKwhDetail.getKwhPaPtct()==null?0.0:mdmAaKwhDetail.getKwhPaPtct();
                    }
                    else
                    if("1".equals(dev.getMeteringDire())){      //反向
                        supplyKwh += mdmAaKwhDetail.getKwhRaPtct()==null?0.0:mdmAaKwhDetail.getKwhRaPtct();
                    }
                }
                else
                if("1".equals(dev.getSupplyDire())){        //供出
                    if("0".equals(dev.getMeteringDire())){      //正向
                        soldKwh += mdmAaKwhDetail.getKwhPaPtct()==null?0.0:mdmAaKwhDetail.getKwhPaPtct();
                    }
                    else
                    if("1".equals(dev.getMeteringDire())){      //反向
                        soldKwh += mdmAaKwhDetail.getKwhRaPtct()==null?0.0:mdmAaKwhDetail.getKwhRaPtct();
                    }
                }
            }
			
		}
		
		mdmAaLineLoss.setSupplyKwh(supplyKwh);			//供电量(供入正向+供入反向)
		mdmAaLineLoss.setSoldKwh(soldKwh);				//售电量(供出正向+供出反向)
		
		Double lossKwh = supplyKwh-soldKwh;
		mdmAaLineLoss.setLossKwh(lossKwh);				//线损量
		Double rate = 0.0;
		if(supplyKwh > 0){
			rate = lossKwh / supplyKwh * 100;
		}
//		else{
//			rate = null;
//		}
		mdmAaLineLoss.setLossRate(Double.valueOf(String.format("%.2f", rate)));  //线损率
		
		//计算同比
		MdmAaLineLoss lastYearLoss = new MdmAaLineLoss();
		lastYearLoss.setWay(mdmAaLineLoss.getWay());
		lastYearLoss.setRefId(mdmAaLineLoss.getRefId());
		lastYearLoss.setDataType(mdmAaLineLoss.getDataType());
		Date last1Year = DateUtil.rollYear(startDate, -1);
		lastYearLoss.setNowTime(DateUtil.dateToString(last1Year, DateUtil.DATE_MONTH_LINE));
		lastYearLoss = mdmAaLineLossDao.selectByRef(lastYearLoss);
		if(lastYearLoss == null || lastYearLoss.getLossRate() == 0.0){
			mdmAaLineLoss.setYearBasis(0.0);			
		}
		else{
			mdmAaLineLoss.setYearBasis((mdmAaLineLoss.getLossRate()-lastYearLoss.getLossRate()) / lastYearLoss.getLossRate() * 100);
		}
		
		//计算环比
		MdmAaLineLoss lastMonLoss = new MdmAaLineLoss();
		lastMonLoss.setWay(mdmAaLineLoss.getWay());
		lastMonLoss.setRefId(mdmAaLineLoss.getRefId());
		lastMonLoss.setDataType(mdmAaLineLoss.getDataType());
		Date last1Month = DateUtil.rollByMonth(startDate, -1);
		lastMonLoss.setNowTime(DateUtil.dateToString(last1Month, DateUtil.DATE_MONTH_LINE));
		lastMonLoss = mdmAaLineLossDao.selectByRef(lastMonLoss);
		if(lastMonLoss == null || lastMonLoss.getLossRate() == 0.0){
			mdmAaLineLoss.setLinkRelativeRatio(0.0);			
		}
		else{
			mdmAaLineLoss.setLinkRelativeRatio((mdmAaLineLoss.getLossRate()-lastMonLoss.getLossRate()) / lastMonLoss.getLossRate() * 100);
		}
		mdmAaLineLoss.setLineLoseIndex(mdmAaLineLossModel.getLossIndexMonth()); 		//线损指标
		mdmAaLineLoss.setCdate(new Date()); 	
		mdmAaLineLossDao.insertSelective(mdmAaLineLoss);
		
		if(rate<0 || rate>mdmAaLineLoss.getLineLoseIndex()) {
		    process008Impl.send0901(mdmAaLineLoss, mdmAaLineLossModel, sysOrg);
        }
	}
}
