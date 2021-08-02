package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.mdm.model.VtMdmDeviceRead;

public interface MdmDeviceReadDao {
	
    int deleteByPrimaryKey(MdmDeviceRead record);

    int insertSelective(MdmDeviceRead record);

    MdmDeviceRead selectByPrimaryKey(MdmDeviceRead record);

    int updateByPrimaryKeySelective(MdmDeviceRead record);

    int selectCountByPowerFactor(VtMdmDeviceRead vtMdmDeviceRead);

}