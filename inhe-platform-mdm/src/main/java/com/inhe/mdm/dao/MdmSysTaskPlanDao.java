package com.inhe.mdm.dao;

import org.apache.ibatis.annotations.Param;

import com.inhe.admin.model.SysTaskPlan;

public interface MdmSysTaskPlanDao {
	
    int deleteByPrimaryKey(String code);

    int insertSelective(SysTaskPlan record);

    SysTaskPlan selectByPrimaryKey(String code);
    
    String selectPlanCodeByTaskCode(@Param("taskCode") String taskCode, @Param("dataTime") String dataTime);
 
    int updateByPrimaryKeySelective(SysTaskPlan record);
}