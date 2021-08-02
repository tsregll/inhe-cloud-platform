package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmPowerOffTaskData;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface MdmPowerOffTaskDataDao {
	int deleteByPrimaryKey(@Param("dataLevel") String dataLevel, @Param("code") String code,
			@Param("deviceId") String deviceId, @Param("offExecTime") Date offExecTime,
			@Param("onExecTime") Date onExecTime);

	int insertSelective(MdmPowerOffTaskData record);

	MdmPowerOffTaskData selectByPrimaryKey(@Param("dataLevel") String dataLevel, @Param("code") String code,
			@Param("deviceId") String deviceId, @Param("offExecTime") Date offExecTime,
			@Param("onExecTime") Date onExecTime);

	int updateByPrimaryKeySelective(MdmPowerOffTaskData record);

}