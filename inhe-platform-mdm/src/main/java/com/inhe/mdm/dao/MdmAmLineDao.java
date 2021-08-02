package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAmLine;
import com.inhe.mdm.model.VtMdmAmLine;

public interface MdmAmLineDao {
	
	int selectCount(@Param("mdmAmLine") VtMdmAmLine mdmAmLine);

    List<VtMdmAmLine> selectList(@Param("mdmAmLine") VtMdmAmLine mdmAmLine,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
    
    String selectMaxId(@Param("mdmAmLine") MdmAmLine mdmAmLine);
    
    String selectDeptIdByStationId(@Param("mdmAmLine") MdmAmLine mdmAmLine); 
    
    MdmAmLine selectByPrimaryKey(String id);
    
    VtMdmAmLine selectDesByPrimaryKey(@Param("mdmAmLine") VtMdmAmLine mdmAmLine);//查询变电站名称
    
    MdmAmLine selectByLineCode(MdmAmLine mdmAmLine);

    int insertSelective(MdmAmLine record);

    int updateByPrimaryKeySelective(MdmAmLine record);
    
    int delete(MdmAmLine record);
    
    int deleteByPrimaryKey(MdmAmLine record);

    List<MdmAmLine> selectByAll();
    
    List<MdmAmLine> selectByAllPowerOff(@Param("orgId") String orgId);

}