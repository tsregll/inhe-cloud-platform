package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmVeeFields;
import com.inhe.mdm.model.VtMdmVeeFields;

public interface MdmVeeFieldsDao {
	
    int deleteByPrimaryKey(MdmVeeFields record);

    int insertSelective(MdmVeeFields record);

    MdmVeeFields selectByPrimaryKey(MdmVeeFields record);

    int updateByPrimaryKeySelective(MdmVeeFields record);

    int selectCount(@Param("vtMdmVeeFields") VtMdmVeeFields vtMdmVeeFields);

    List<VtMdmVeeFields> selectList(@Param("vtMdmVeeFields") VtMdmVeeFields vtMdmVeeFields, 
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

}