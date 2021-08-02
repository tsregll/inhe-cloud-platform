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
import com.inhe.mdm.dao.MdmAmLineDao;
import com.inhe.mdm.dao.MdmAmSubstationDao;
import com.inhe.mdm.dao.MdmAmSubstationMtDao;
import com.inhe.mdm.dao.MdmAmTmDao;
import com.inhe.mdm.dao.MdmDictionaryDao;
import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmAaLineLoss;
import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.MdmAmSubstation;
import com.inhe.mdm.model.MdmAmSubstationMt;
import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.MdmDictionary;
import com.inhe.mdm.send.SendCmdProxy;
import com.inhe.mdm.utils.MdmCodeUtil;
import com.inhe.node.service.ISysOrgService;
import com.inhe.utils.DateUtil;

/**
 * @description 日线损分析
 * @author ghf
 * @date 2021/01/07
 */
@Service
public class Process008Impl {
    
    private final Logger logger = LoggerFactory.getLogger(Process008Impl.class);
    
    //分区线损
    private static final String LINE_LOSS_WAY_REGION = "0";
    //分线线损
    private static final String LINE_LOSS_WAY_LINE = "1";
    //分台区线损
    private static final String LINE_LOSS_WAY_COURTS = "2";
    //分压线损
    private static final String LINE_LOSS_WAY_VOLTAGE = "3";
    //母线不平衡线损
    private static final String LINE_LOSS_WAY_BUS = "4";
    //主变损耗
    private static final String LINE_LOSS_WAY_MTSF = "5";
    
    //分区线损异常
    private static final String LINE_LOSS_EVENT_REGION = "-000933";
    //分压线损异常
    private static final String LINE_LOSS_EVENT_VOLTAGE = "-000934";
    //分线线损异常
    private static final String LINE_LOSS_EVENT_LINE = "-000935";
    //分台区线损异常
    private static final String LINE_LOSS_EVENT_COURTS = "-000936";
    //母线不平衡线损异常
    private static final String LINE_LOSS_EVENT_BUS = "-000937";
    //主变损耗线损异常
    private static final String LINE_LOSS_EVENT_MTSF = "-000938";
	
	@Autowired
	MdmAaLineLossModelDao mdmAaLineLossModelDao;
	
	@Autowired
	MdmAaLineLossDao mdmAaLineLossDao;
	
	@Autowired
	MdmAaKwhDetailDao mdmAaKwhDetailDao;
	
	@Autowired
	MdmAaLineLossModelDveDao mdmAaLineLossModelDveDao;
	
	@Autowired
	MdmAmLineDao mdmAmLineDao;
	
	@Autowired
	MdmAmTmDao mdmAmTmDao;
	
	@Autowired
	MdmAmSubstationDao mdmAmSubstationDao;
	
	@Autowired
	MdmAmSubstationMtDao mdmAmSubstationMtDao;
	
	@Autowired
    MdmDictionaryDao mdmDictionaryDao;
	
	@Autowired
	ISysOrgService sysOrgService;
	
	@Autowired
	SendCmdProxy sendCmdProxy;
	
	public void execute(JSONObject jobj) throws Exception{
	    Date startDate = DateUtil.rollByDay(jobj.getDate("data_time"), -1);
		String day = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
		logger.info("Process008-start-"+day);
		String id =  jobj.getString("params");
		List<MdmAaLineLossModel> modelList = null;
		if(id != null && !"".equals(id)){		//重新执行单个模型
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
		String day = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
		MdmAaLineLoss mdmAaLineLoss = new MdmAaLineLoss();
		mdmAaLineLoss.setId(MdmCodeUtil.getAaLineLossCode());
		mdmAaLineLoss.setOrgId(mdmAaLineLossModel.getOrgId());
		mdmAaLineLoss.setDeptId(mdmAaLineLossModel.getDeptId());
		mdmAaLineLoss.setLineLoseIndex(mdmAaLineLossModel.getLossIndexDay());
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
		String refId= mdmAaLineLossModel.getRefId();
		
		mdmAaLineLoss.setRefId(refId);
		mdmAaLineLoss.setDataType("0");
		mdmAaLineLoss.setNowTime(day);
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
			mdmAaKwhDetail.setTimeType("0");
			mdmAaKwhDetail.setNowTime(day);
			mdmAaKwhDetail = mdmAaKwhDetailDao.selectByPrimaryKey(mdmAaKwhDetail);
			if(mdmAaKwhDetail == null) {//用电量不存在
			    logger.error("AaKwhDetail not exist:deviceNum-{},day-{}",dev.getDeviceNum(),day);
			}else {
			    if("0".equals(dev.getSupplyDire())){     //供入
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
		lastYearLoss.setNowTime(DateUtil.dateToString(last1Year, DateUtil.DATE_WITH_LINE));
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
		Date last1Month = DateUtil.rollByDay(startDate, -1);
		lastMonLoss.setNowTime(DateUtil.dateToString(last1Month, DateUtil.DATE_WITH_LINE));
		lastMonLoss = mdmAaLineLossDao.selectByRef(lastMonLoss);
		if(lastMonLoss == null || lastMonLoss.getLossRate() == null ||lastMonLoss.getLossRate() == 0.0){
			mdmAaLineLoss.setLinkRelativeRatio(0.0);			
		}
		else{
			mdmAaLineLoss.setLinkRelativeRatio((mdmAaLineLoss.getLossRate()-lastMonLoss.getLossRate()) / lastMonLoss.getLossRate() * 100);
		}
		mdmAaLineLoss.setLineLoseIndex(mdmAaLineLossModel.getLossIndexDay()); 		//线损指标
		mdmAaLineLoss.setCdate(new Date()); 	
		mdmAaLineLossDao.insertSelective(mdmAaLineLoss);
		
		if(rate<0 || rate>mdmAaLineLoss.getLineLoseIndex()) {
		    send0901(mdmAaLineLoss, mdmAaLineLossModel, sysOrg);
		}
		
	}
	
	public void send0901(MdmAaLineLoss mdmAaLineLoss, MdmAaLineLossModel amrLineLossModel, SysOrg sysOrg) {
	    try {
            JSONObject param = new JSONObject();
            param.put("lossRate",String.format("%.2f", mdmAaLineLoss.getLossRate()));
            param.put("time", mdmAaLineLoss.getNowTime());
            param.put("orgId", amrLineLossModel.getOrgId());
            param.put("lossValue", String.format("%.4f",mdmAaLineLoss.getLossKwh()));//需格式化，保留4位小数
            param.put("mmc",String.format("%.4f",mdmAaLineLoss.getSupplyKwh()));
            param.put("smc",String.format("%.4f",mdmAaLineLoss.getSoldKwh()));
            String type = "";
            String dev = amrLineLossModel.getId();
            String way = mdmAaLineLoss.getWay();
            if(LINE_LOSS_WAY_REGION.equals(way)) {  //分区线损933
                type = LINE_LOSS_EVENT_REGION;
                param.put("orgName", sysOrg.getOrgName());
            }else if(LINE_LOSS_WAY_LINE.equals(way)) {  //分线线损935
                type = LINE_LOSS_EVENT_LINE;
                param.put("orgName", sysOrg.getOrgName());
                MdmAmLine mdmAmLine = mdmAmLineDao.selectByPrimaryKey(mdmAaLineLoss.getRefId());
                if(mdmAmLine != null) {
                    param.put("line", mdmAmLine.getDescription());
                }
            }else if(LINE_LOSS_WAY_COURTS.equals(way)) {  //台区线损936
                type = LINE_LOSS_EVENT_COURTS;
                param.put("orgName", sysOrg.getOrgName());
                MdmAmTm amTm = mdmAmTmDao.selectByPrimaryKey(mdmAaLineLoss.getRefId());
                if(amTm != null) {
                    MdmAmLine mdmAmLine = mdmAmLineDao.selectByPrimaryKey(amTm.getLineId());
                    param.put("line", mdmAmLine.getDescription());
                    param.put("tm", amTm.getDescription());
                }
            }else if(LINE_LOSS_WAY_VOLTAGE.equals(way)) {  //分压线损934
                type=LINE_LOSS_EVENT_VOLTAGE;
                param.put("orgName", sysOrg.getOrgName());
                MdmDictionary paramDic = new MdmDictionary();
                paramDic.setTableType("VOL_LEVEL");
                paramDic.setCode(mdmAaLineLoss.getRefId());
                MdmDictionary dic = mdmDictionaryDao.selectByPrimaryKey(paramDic);
                if(dic != null) {
                    param.put("vol", dic.getDescription());
                }
            }else if(LINE_LOSS_WAY_BUS.equals(way)) {  //母线不平衡937
                type = LINE_LOSS_EVENT_BUS;
                MdmAmLine mdmAmLine = mdmAmLineDao.selectByPrimaryKey(mdmAaLineLoss.getRefId());
                if(mdmAmLine != null) {
                    MdmAmSubstation mdmAmSubstation = mdmAmSubstationDao.selectByPrimaryKey(mdmAmLine.getStationId());
                    param.put("bus", mdmAmLine.getDescription());
                    param.put("substation", mdmAmSubstation.getDescription());
                }
            }else if(LINE_LOSS_WAY_MTSF.equals(way)) {  //主变损耗938
                type = LINE_LOSS_EVENT_MTSF;
                MdmAmSubstationMt mdmAmSubstationMt = mdmAmSubstationMtDao.selectByPrimaryKey(amrLineLossModel.getRefId());
                if(mdmAmSubstationMt != null) {
                    param.put("mtsf", mdmAmSubstationMt.getName());
                    MdmAmSubstation mdmAmSubstation = mdmAmSubstationDao.selectByPrimaryKey(mdmAmSubstationMt.getStationId());
                    param.put("substation", mdmAmSubstation.getDescription());
                }
            }
            String data = param.toJSONString();
            sendCmdProxy.send0901Simple(sysOrg.getOrgId(),type, data, dev);
        } catch (Exception e) {
            logger.error("send line loss msg failed.",e);
        }
	    
	}
	
}
