package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmDeviceSet;

public interface MdmDeviceSetDao {
	
    int deleteByPrimaryKey(MdmDeviceSet record);

    int insertSelective(MdmDeviceSet record);

    MdmDeviceSet selectByPrimaryKey(MdmDeviceSet record);

    int updateByPrimaryKeySelective(MdmDeviceSet record);

    String selectLastDeviceSet(MdmDeviceSet record);

    String selectLastDeviceRead(MdmDeviceSet record);

}