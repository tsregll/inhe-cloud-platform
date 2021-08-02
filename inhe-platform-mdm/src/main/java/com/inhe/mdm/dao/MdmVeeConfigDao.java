package com.inhe.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.inhe.mdm.model.MdmAaKwhDetail;
import com.inhe.mdm.model.MdmDeviceRead;
import com.inhe.mdm.model.MdmVeeConfig;
import com.inhe.mdm.model.MdmVeeReadQry;

public interface MdmVeeConfigDao {
    
    int deleteByPrimaryKey(String code);

    int insertSelective(MdmVeeConfig record);

    MdmVeeConfig selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MdmVeeConfig record);

    int selectCount(@Param("mdmVeeConfig") MdmVeeConfig mdmVeeConfig);

    List<MdmVeeConfig> selectList(@Param("mdmVeeConfig") MdmVeeConfig mdmVeeConfig, 
            @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    List<MdmVeeConfig> selectAll();


    String selectMaxCode();
    
    List<String> SelectVeeRuleDevice(MdmVeeConfig veeConfig);   
    List<Double> selectAvgValue(MdmVeeReadQry qry);
    List<MdmAaKwhDetail> selectEkwhValue(MdmVeeReadQry qry);
    List<MdmAaKwhDetail> selectRkwhValue(MdmVeeReadQry qry);
    List<Double> selectKwhValue(MdmVeeReadQry qry);
    String selectDescByCmd(String cmd);
    String selectDescByEid(String id);
    MdmDeviceRead selectByLastTime(MdmDeviceRead amrRead);
    MdmDeviceRead selectByReqLastTime(MdmDeviceRead amrRead);
    List<MdmDeviceRead> selectZeroValue(MdmDeviceRead amrRead);
    List<MdmDeviceRead> selectZeroValue2(MdmVeeReadQry qry);
    List<MdmDeviceRead> selectZeroValueTest(MdmVeeReadQry qry);
    MdmAaKwhDetail selectLastKwhValue(MdmAaKwhDetail aakwhdetail);
    MdmAaKwhDetail calcSum(MdmAaKwhDetail mdmAaKwhDetail);
    Double calcSumCheck(MdmAaKwhDetail mdmAaKwhDetail);

}