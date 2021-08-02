package com.inhe.mdm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaLineLossModel;
import com.inhe.mdm.model.VtMdmAaLineLossModel;

public interface MdmAaLineLossModelDao {
	
	int selectCountByPartition(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByPartition(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByVoltage(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByVoltage(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByLine(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByLine(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByTransformerArea(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByTransformerArea(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByMainLine(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByMainLine(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int selectCountByMainTransformer(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel);

	List<VtMdmAaLineLossModel> selectListByMainTransformer(@Param("mdmAaLineLossModel") VtMdmAaLineLossModel mdmAaLineLossModel,
			@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
	
	int createDeptTable();
	
	int insertDeptIdBatch(@Param("list") List<String> record);
	
	int deleteDeptTable();
    
    int deleteByPrimaryKey(String id);
    
    int deleteBatch(@Param("list") List<String> record ,@Param("generationFlag") String generationFlag,@Param("deleteTime") Date deleteTime);

    int insertSelective(MdmAaLineLossModel record);
    
    int insertBatchSelective(@Param("list") List<VtMdmAaLineLossModel> record);

    MdmAaLineLossModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdmAaLineLossModel record);
    
    int updateBatchSelective(@Param("list") List<MdmAaLineLossModel> record);

    List<MdmAaLineLossModel> selectBySort(String id);

    List<MdmAaLineLossModel> selectByEffective();

}