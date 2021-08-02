package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmVeeTaskData;
import com.inhe.mdm.model.VtMdmVeeTaskData;

public interface MdmVeeTaskDataDao {
	
    int deleteByPrimaryKey(MdmVeeTaskData record);

    int insertSelective(MdmVeeTaskData record);

    MdmVeeTaskData selectByPrimaryKey(MdmVeeTaskData record);

    int updateByPrimaryKeySelective(MdmVeeTaskData record);

    int selectCount(@Param("vtMdmVeeTaskData") VtMdmVeeTaskData vtMdmVeeTaskData);

    List<VtMdmVeeTaskData> selectList(@Param("vtMdmVeeTaskData") VtMdmVeeTaskData vtMdmVeeTaskData,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    List<MdmVeeTaskData> selectDetailByRule(VtMdmVeeTaskData vtMdmVeeTaskData);

}