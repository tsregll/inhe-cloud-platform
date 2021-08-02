package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmSysEventType;

public interface MdmSysEventTypeDao {
    int deleteByPrimaryKey(Integer code);

    int insertSelective(MdmSysEventType record);

    MdmSysEventType selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(MdmSysEventType record);

}