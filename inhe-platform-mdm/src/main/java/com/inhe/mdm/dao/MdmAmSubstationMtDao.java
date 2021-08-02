package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAmSubstationMt;
import com.inhe.mdm.model.VtMdmAmSubstationMt;

public interface MdmAmSubstationMtDao {
	
	int selectCount(@Param("mdmAmSubstationMt") VtMdmAmSubstationMt mdmAmSubstationMt);

    List<VtMdmAmSubstationMt> selectList(@Param("mdmAmSubstationMt") VtMdmAmSubstationMt mdmAmSubstationMt,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
    
    String selectMaxId(@Param("mdmAmSubstationMt") MdmAmSubstationMt mdmAmSubstationMt);
    
    String selectDeptIdByStationId(@Param("mdmAmSubstationMt") MdmAmSubstationMt mdmAmSubstationMt); 
    
    MdmAmSubstationMt selectByPrimaryKey(String id);
    
    VtMdmAmSubstationMt selectDesByPrimaryKey(@Param("mdmAmSubstationMt") VtMdmAmSubstationMt mdmAmSubstationMt);
    
    MdmAmSubstationMt selectByStationCode(MdmAmSubstationMt mdmAmSubstationMt);

    int insertSelective(MdmAmSubstationMt record);

    int updateByPrimaryKeySelective(MdmAmSubstationMt record);
    
    int delete(MdmAmSubstationMt record);
    
    int deleteByPrimaryKey(MdmAmSubstationMt record);

}