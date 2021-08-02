package com.inhe.mdm.dao;

import java.util.List;

import com.inhe.mdm.model.MdmVeeReadQry;
import com.inhe.mdm.model.MdmDeviceRead;

public interface MdmVeeReadQryDao {
	//查询上次值
	List<MdmDeviceRead> queryLast(MdmVeeReadQry qry);
	//瞬时-查询上次时间最近值
	MdmDeviceRead queryOnceLast(MdmVeeReadQry qry);      
}
