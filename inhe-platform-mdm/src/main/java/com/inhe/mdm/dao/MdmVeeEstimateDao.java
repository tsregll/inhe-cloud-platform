package com.inhe.mdm.dao;

import java.util.List;

import com.inhe.mdm.model.MdmVeeEstimate;

public interface MdmVeeEstimateDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmVeeEstimate record);

    MdmVeeEstimate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmVeeEstimate record);

    List<MdmVeeEstimate> selectAll(MdmVeeEstimate param);

}