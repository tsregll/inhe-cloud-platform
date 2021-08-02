package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmField;
import com.inhe.mdm.model.VtMdmField;

public interface MdmFieldDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmField record);

    MdmField selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmField record);

    List<MdmField> searchFieldsDetail(@Param("vtMdmField") VtMdmField vtMdmField,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

}