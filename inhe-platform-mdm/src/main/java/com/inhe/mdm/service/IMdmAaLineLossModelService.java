package com.inhe.mdm.service;

import java.util.List;

import com.inhe.build.result.PageBean;
import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.model.VtMdmAaLineLossModel;
import com.inhe.mdm.model.VtMdmAaLineLossModelDve;

public interface IMdmAaLineLossModelService {
	public PageBean<VtMdmAaLineLossModel> queryPartitionModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize) throws Exception;
	
	public PageBean<VtMdmAaLineLossModel> queryVoltageModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize) throws Exception;
	
	public PageBean<VtMdmAaLineLossModel> queryLineModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize);
	
	public PageBean<VtMdmAaLineLossModel> queryTmAreaModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize);
	
	public PageBean<VtMdmAaLineLossModel> queryMainLineModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize);
	
	public PageBean<VtMdmAaLineLossModel> queryMainTransformerModelList(VtMdmAaLineLossModel param, int pageNo, int pageSize);
	
	public PageBean<MdmAaLineLossModelDve> queryModelDevList(MdmAaLineLossModelDve param, int pageNo, int pageSize);
	
	public PageBean<VtMdmAaLineLossModelDve> queryMeterList(VtMdmAaLineLossModelDve param, int pageNo, int pageSize);

	public boolean insert(List<VtMdmAaLineLossModel> mdmAaLineLossModel);
	
	public boolean insertMeter(List<MdmAaLineLossModelDve> mdmAaLineLossModelDve,String id);

	public boolean update(List<MdmAaLineLossModel> mdmAaLineLossModel);

	public boolean delete(List<String> id);
	
	public boolean deleteMeter(VtMdmAaLineLossModelDve mdmAaLineLossModelDve);
}
