package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmDeviceEvent;

public interface MdmDeviceEventDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmDeviceEvent record);

    MdmDeviceEvent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmDeviceEvent record);
    
    int updateByPrimaryKeySelectives(MdmDeviceEvent record);//where DEVICE_ADDR EVENT_TYPE DISPOSE_FLAG='N'

}