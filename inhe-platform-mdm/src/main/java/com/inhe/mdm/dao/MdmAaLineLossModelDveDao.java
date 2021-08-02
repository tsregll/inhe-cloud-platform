package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.inhe.mdm.model.MdmAaLineLossModelDve;
import com.inhe.mdm.model.VtMdmAaLineLossModel;
import com.inhe.mdm.model.VtMdmAaLineLossModelDve;

public interface MdmAaLineLossModelDveDao {
	
	int selectDevCount(@Param("mdmAaLineLossModelDve") MdmAaLineLossModelDve mdmAaLineLossModelDve);

	List<MdmAaLineLossModelDve> selectDevList(@Param("mdmAaLineLossModelDve") MdmAaLineLossModelDve mdmAaLineLossModelDve,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectMeterCount(@Param("mdmAaLineLossModelDve") VtMdmAaLineLossModelDve mdmAaLineLossModelDve);

	List<VtMdmAaLineLossModelDve> selectMeterList(@Param("mdmAaLineLossModelDve") VtMdmAaLineLossModelDve mdmAaLineLossModelDve,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	List<MdmAaLineLossModelDve> selectAutoSupplyInLineList(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModelDve);
	
	List<MdmAaLineLossModelDve> selectAutoSupplyOutLineList(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModelDve);
	
	List<MdmAaLineLossModelDve> selectAutoSupplyInTmList(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModelDve);
	
	List<MdmAaLineLossModelDve> selectAutoSupplyOutTmList(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModelDve);
	
    int deleteByPrimaryKey(@Param("mdmAaLineLossModel") VtMdmAaLineLossModelDve record);
    
    int deleteBatch(@Param("list") List<String> record);
    
    int deleteById(String Id);

    int insertSelective(MdmAaLineLossModelDve record);
    
    int insertBatchSelective(@Param("list") List<MdmAaLineLossModelDve> record);

    MdmAaLineLossModelDve selectByPrimaryKey(MdmAaLineLossModelDve record);
    
    VtMdmAaLineLossModelDve selectSumById(@Param("mdmAaLineLossModelDve") VtMdmAaLineLossModelDve mdmAaLineLossModelDve);

    int updateByPrimaryKeySelective(MdmAaLineLossModelDve record);

    List<MdmAaLineLossModelDve> selectById(String id);

}