package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmPowerOffTask;

public interface MdmPowerOffTaskDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(MdmPowerOffTask record);

    MdmPowerOffTask selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MdmPowerOffTask record);

}