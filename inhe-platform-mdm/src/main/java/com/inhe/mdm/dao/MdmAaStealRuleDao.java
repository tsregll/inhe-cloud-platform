package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaStealRule;

public interface MdmAaStealRuleDao {

	int selectCount(@Param("mdmAaStealRule") MdmAaStealRule mdmAaStealRule);

	List<MdmAaStealRule> selectList(@Param("mdmAaStealRule") MdmAaStealRule mdmAaStealRule,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

	MdmAaStealRule selectByPrimaryKey(MdmAaStealRule record);

	int updateByPrimaryKeySelective(MdmAaStealRule record);

    List<MdmAaStealRule> selectAll();

}