package com.inhe.mdm.utils;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.inhe.build.exception.InheExceptionBase;

public class MdmCodeUtil {

	public static String getOrgId() {
		String id = System.currentTimeMillis() + "";
		while (id.length() < 20) {
			id = id + (int) (Math.random() * 10);
		}
		return id;
	}

	public static String getAMStationId(String maxId, String deptId) {
		if (maxId == null || "".equals(maxId)) {
			return deptId + "00001";
		}
		if (!StringUtils.isNumeric(maxId)) {
			throw new InheExceptionBase(-101081, "生成的部门编号错误(可能存在非数字字符)");
		}
		String sortId = String.format("%05d", Integer.valueOf(maxId) + 1);
		String id = deptId + sortId;
		return id;
	}
	
	public static String getReadCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }
	
	public static String getEventCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }
	
	public static String getPowerOffCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }

    public static String getConfigCode(String maxCode) {
        Integer nextCode = Integer.valueOf(maxCode.substring(1))+1;
        String code = maxCode.substring(0,1)+String.format("%03d", nextCode);
        return code;
    }
    
    public static String getDeviceId() {
		String id = System.currentTimeMillis() + "";
		while (id.length() < 20) {
			id = id + (int)(Math.random() * 10);
		}
		return id;
	}
    
    public static String getAaLineLossCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }
    
    public static String getAaKwhCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }
    
    public static String getVeeTaskDataCode(){
        String id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }
    
}
