package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaStealDetail;
import com.inhe.mdm.model.VtMdmAaStealDetail;
import com.inhe.mdm.model.VtMdmAmDeviceChange;
import com.inhe.mdm.model.VtMdmDeviceEvent;

public interface MdmAaStealDetailDao {
	
	int selectCountByDeviceNum(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail);

	List<VtMdmAaStealDetail> selectListByDeviceNum(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByEvent(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail);

	List<VtMdmAaStealDetail> selectListByEvent(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectRuleDetailCount(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail);

	List<VtMdmAaStealDetail> selectRuleDetailList(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    MdmAaStealDetail selectByPrimaryKey(MdmAaStealDetail record);

    int updateByPrimaryKeySelective(MdmAaStealDetail record);

    int insertSelective(MdmAaStealDetail record);

    int selectCountByEventSource(VtMdmDeviceEvent vtMdmDeviceEvent);

    int selectCountByDeviceChange(VtMdmAmDeviceChange vtMdmAmDeviceChange);

}