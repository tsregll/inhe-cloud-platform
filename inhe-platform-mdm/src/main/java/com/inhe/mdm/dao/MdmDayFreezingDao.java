package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmDayFreezing;

public interface MdmDayFreezingDao {
	
    int deleteByPrimaryKey(MdmDayFreezing record);

    int insertSelective(MdmDayFreezing record);

    MdmDayFreezing selectByPrimaryKey(MdmDayFreezing record);

    int updateByPrimaryKeySelective(MdmDayFreezing record);

    int updateVeeByPrimaryKeySelective(MdmDayFreezing record);

    MdmDayFreezing selectByDeviceNum(MdmDayFreezing record);

}