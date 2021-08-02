package com.inhe.mdm.dao;

import java.util.List;

import com.inhe.mdm.model.MdmVeeFieldsValidation;

public interface MdmVeeFieldsValidationDao {
	
    int deleteByPrimaryKey(MdmVeeFieldsValidation record);

    int insertSelective(MdmVeeFieldsValidation record);

    MdmVeeFieldsValidation selectByPrimaryKey(MdmVeeFieldsValidation record);

    int updateByPrimaryKeySelective(MdmVeeFieldsValidation record);

    List<String> selectByType(String type);

}