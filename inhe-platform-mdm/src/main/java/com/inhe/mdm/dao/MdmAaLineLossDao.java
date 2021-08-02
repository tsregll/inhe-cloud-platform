package com.inhe.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaLineLoss;
import com.inhe.mdm.model.VtMdmAaLineLoss;

public interface MdmAaLineLossDao {
	
	int selectLineLossNumCount(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss);

	List<Map<String, Object>> selectLineLossNumList(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	List<VtMdmAaLineLoss> selectDictionaryByTableType(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss);
	
	int selectReportCount(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss);

	List<VtMdmAaLineLoss> selectListByDeptOrVol(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	List<VtMdmAaLineLoss> selectListByLine(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	List<VtMdmAaLineLoss> selectListByTransformerArea(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	List<VtMdmAaLineLoss> selectListByMainLine(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	List<VtMdmAaLineLoss> selectListByMainTransformer(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectSupplyDetailCount(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss);

	List<Map<String, Object>> selectSupplyDetailList(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectlineLossChatCount(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss);

	List<MdmAaLineLoss> selectlineLossChatList(@Param("mdmAaLineLoss") VtMdmAaLineLoss mdmAaLineLoss,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	int deleteByPrimaryKey(String id);

	int insertSelective(MdmAaLineLoss record);

	MdmAaLineLoss selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(MdmAaLineLoss record);

    MdmAaLineLoss selectByRef(MdmAaLineLoss lastYearLoss);

    int deleteByOld(MdmAaLineLoss mdmAaLineLoss);

}