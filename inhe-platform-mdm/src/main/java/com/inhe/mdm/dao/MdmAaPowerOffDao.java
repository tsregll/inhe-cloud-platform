package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaPowerOff;
import com.inhe.mdm.model.VtMdmAaPowerOff;

public interface MdmAaPowerOffDao {
	
	int selectCount(@Param("mdmAaPowerOff") MdmAaPowerOff record);

	List<VtMdmAaPowerOff> selectList(@Param("mdmAaPowerOff") MdmAaPowerOff record,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	List<VtMdmAaPowerOff> selectListByLine(@Param("mdmAaPowerOff") MdmAaPowerOff record,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	List<VtMdmAaPowerOff> selectListByTm(@Param("mdmAaPowerOff") MdmAaPowerOff record,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmAaPowerOff record);

    MdmAaPowerOff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmAaPowerOff record);
    
    int deleteByOld(MdmAaPowerOff record);
}