package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaKwh;

public interface MdmAaKwhDao {
	
	int selectCount(@Param("mdmAaKwh") MdmAaKwh mdmAaKwh);

	List<MdmAaKwh> selectList(@Param("mdmAaKwh") MdmAaKwh mdmAaKwh,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
    int deleteByPrimaryKey(String id);

    int insertSelective(MdmAaKwh record);

    MdmAaKwh selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmAaKwh record);

    int deleteByOld(MdmAaKwh record);

}