package com.inhe.mdm.dao;

import java.util.List;

import com.inhe.mdm.model.MdmVeeValidation;

public interface MdmVeeValidationDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmVeeValidation record);

    MdmVeeValidation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmVeeValidation record);

    List<MdmVeeValidation> selectAll(MdmVeeValidation record);

}