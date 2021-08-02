package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAmTm;
import com.inhe.mdm.model.VtMdmAmTm;

public interface MdmAmTmDao {
	
	int selectCount(@Param("mdmAmTm") VtMdmAmTm mdmAmTm);

    List<VtMdmAmTm> selectList(@Param("mdmAmTm") VtMdmAmTm mdmAmTm,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
    
    String selectMaxId(@Param("mdmAmTm") MdmAmTm mdmAmTm);
    
    String selectDeptIdByStationId(@Param("mdmAmTm") MdmAmTm mdmAmTm); 
    
    MdmAmTm selectByPrimaryKey(String id);
    
    VtMdmAmTm selectDesByPrimaryKey(@Param("mdmAmTm") VtMdmAmTm mdmAmTm);//查询线路名称
    
    MdmAmTm selectByTmCode(MdmAmTm mdmAmTm);
    
    List<MdmAmTm> selectByAll();

    int insertSelective(MdmAmTm record);

    int updateByPrimaryKeySelective(MdmAmTm record);
    
    int delete(MdmAmTm record);
    
    int deleteByPrimaryKey(MdmAmTm record);
}