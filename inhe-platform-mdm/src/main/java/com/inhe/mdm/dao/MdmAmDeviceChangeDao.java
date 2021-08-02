package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmAmDeviceChange;

public interface MdmAmDeviceChangeDao {
	
    int deleteByPrimaryKey(MdmAmDeviceChange record);

    int insertSelective(MdmAmDeviceChange record);

    MdmAmDeviceChange selectByPrimaryKey(MdmAmDeviceChange record);

    int updateByPrimaryKeySelective(MdmAmDeviceChange record);

    MdmAmDeviceChange selectByDeviceNum(MdmAmDeviceChange record);

}