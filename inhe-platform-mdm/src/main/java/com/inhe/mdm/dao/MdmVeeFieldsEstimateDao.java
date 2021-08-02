package com.inhe.mdm.dao;

import java.util.List;

import com.inhe.mdm.model.MdmVeeFieldsEstimate;

public interface MdmVeeFieldsEstimateDao {
	
    int deleteByPrimaryKey(MdmVeeFieldsEstimate record);

    int insertSelective(MdmVeeFieldsEstimate record);

    MdmVeeFieldsEstimate selectByPrimaryKey(MdmVeeFieldsEstimate record);

    int updateByPrimaryKeySelective(MdmVeeFieldsEstimate record);

    List<String> selectByType(String type);

}