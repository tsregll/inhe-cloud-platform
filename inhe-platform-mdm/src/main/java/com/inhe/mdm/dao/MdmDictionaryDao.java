package com.inhe.mdm.dao;

import com.inhe.mdm.model.MdmDictionary;

public interface MdmDictionaryDao {
	
    int deleteByPrimaryKey(MdmDictionary key);

    int insertSelective(MdmDictionary record);

    MdmDictionary selectByPrimaryKey(MdmDictionary key);

    int updateByPrimaryKeySelective(MdmDictionary record);

}