package com.inhe.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAmSubstation;
import com.inhe.mdm.model.VtMdmAmSubstation;

public interface MdmAmSubstationDao {
    int selectCount(@Param("mdmAmSubstation") VtMdmAmSubstation mdmAmSubstation);

    List<VtMdmAmSubstation> selectList(@Param("mdmAmSubstation") VtMdmAmSubstation mdmAmSubstation,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
    
    String selectMaxId(@Param("mdmAmSubstation") MdmAmSubstation mdmAmSubstation);
    
    MdmAmSubstation selectByPrimaryKey(String id);
    
    MdmAmSubstation selectByStationCode(MdmAmSubstation mdmAmSubstation);
    
    Map<String, Object> selectDashboardCount(@Param("orgId") String orgId,@Param("deptId") String deptId);

    int insertSelective(MdmAmSubstation record);

    int updateByPrimaryKeySelective(MdmAmSubstation record);
    
    int delete(MdmAmSubstation record);
    
    int deleteByPrimaryKey(MdmAmSubstation record);

}