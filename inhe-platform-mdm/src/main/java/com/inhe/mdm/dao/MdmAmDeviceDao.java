package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAmDevice;
import com.inhe.mdm.model.VtMdmAmDevice;
import com.inhe.mdm.model.VtMdmDeviceSummary;

public interface MdmAmDeviceDao {
	
	int selectCount(@Param("mdmAmDevice") VtMdmAmDevice mdmAmDevice);

    List<VtMdmAmDevice> selectList(@Param("mdmAmDevice") VtMdmAmDevice mdmAmDevice,
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
    
    VtMdmAmDevice selectDesByPrimaryKey(@Param("mdmAmDevice") VtMdmAmDevice mdmAmDevice);//查询计量点名称
    
    MdmAmDevice selectByPrimaryKey(String id);
    
    MdmAmDevice selectByDevNum(MdmAmDevice record);
    
    VtMdmDeviceSummary	selectSummary(MdmAmDevice record);
    
    List<MdmAmDevice> selectByType(String type);
    
    List<MdmAmDevice> selectAll();
    
    int insertSelective(MdmAmDevice record);

    int updateByPrimaryKeySelective(MdmAmDevice record);
    
    int delete(MdmAmDevice record);
    
    int deleteByPrimaryKey(String id);

}