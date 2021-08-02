package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmAaPowerOffDetail;
import com.inhe.mdm.model.VtMdmAaPowerOffDetail;
import com.inhe.mdm.model.VtMdmAaPowerOffSummary;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MdmAaPowerOffDetailDao {
	
	int selectCount(@Param("list") List<String> deviceIds ,@Param("mdmAaPowerOffDetail") VtMdmAaPowerOffDetail record);

	List<VtMdmAaPowerOffDetail> selectList(@Param("list") List<String> deviceIds ,@Param("mdmAaPowerOffDetail") VtMdmAaPowerOffDetail record,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	List<String> selectDeviceNumCount(@Param("timesFlag") String timesFlag);
	
    int deleteByPrimaryKey(@Param("deviceId") String deviceId, @Param("startTime") Date startTime, @Param("offType") String offType);

    int insertSelective(MdmAaPowerOffDetail record);

    MdmAaPowerOffDetail selectByPrimaryKey(@Param("deviceId") String deviceId, @Param("startTime") Date startTime, @Param("offType") String offType);

    int updateByPrimaryKeySelective(MdmAaPowerOffDetail record);
    
    List<MdmAaPowerOffDetail> selectByEvent(@Param("day") String day);
    
    List<VtMdmAaPowerOffSummary> selectSummary(VtMdmAaPowerOffDetail record);
    
	int insertByPlan(@Param("day") String day);

}