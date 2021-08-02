package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmMonthFreezing;

public interface MdmMonthFreezingDao {
	
    int deleteByPrimaryKey(MdmMonthFreezing record);

    int insertSelective(MdmMonthFreezing record);

    MdmMonthFreezing selectByPrimaryKey(MdmMonthFreezing record);

    int updateByPrimaryKeySelective(MdmMonthFreezing record);

    int updateVeeByPrimaryKeySelective(MdmMonthFreezing record);

    MdmMonthFreezing selectByDeviceNum(MdmMonthFreezing record);

}