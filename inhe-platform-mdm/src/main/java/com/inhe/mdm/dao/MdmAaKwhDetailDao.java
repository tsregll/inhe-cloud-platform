package com.inhe.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.VtMdmAaKwhDetail;
import com.inhe.mdm.model.VtMdmAaKwhSummary;
import com.inhe.mdm.model.VtMdmAaStealDetail;

public interface MdmAaKwhDetailDao {

	int deleteByPrimaryKey(MdmAaKwhDetail record);

	int insertSelective(MdmAaKwhDetail record);

	MdmAaKwhDetail selectByPrimaryKey(MdmAaKwhDetail record);

	int updateByPrimaryKeySelective(MdmAaKwhDetail record);

	int selectKwhCountByDeviceId(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail);
	
	List<Map<String, Object>> selectKwhList(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail);

	List<Map<String, Object>> selectKwhListByDeviceId(@Param("mdmAaStealDetail") VtMdmAaStealDetail mdmAaStealDetail,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByMeter(@Param("mdmAaKwhDetail") VtMdmAaKwhDetail mdmAaKwhDetail);

	List<Map<String, Object>> selectListByMeter(@Param("mdmAaKwhDetail") VtMdmAaKwhDetail mdmAaKwhDetail,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    int updateResultByPrimaryKey(MdmAaKwhDetail mdmAaKwhDetail);

    List<VtMdmAaKwhSummary> selectSummary(MdmAaKwhDetail mdmAaKwhDetail);

}