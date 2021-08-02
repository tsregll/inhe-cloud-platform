package com.inhe.utils;

import java.lang.reflect.Field;

public class ObjectUtil {
	
	 /**
	  * 根据属性名获取属性值
	  * @param fieldName
	  * @param object
	  * @return
	  */
	 public static String getFieldValueByFieldName(Object object, String fieldName) {
	    try {
	    	Field field = object.getClass().getDeclaredField(fieldName);
	    	//设置对象的访问权限，保证对private的属性的访问
	    	field.setAccessible(true);
	    	return  (String)field.get(object);
	    } catch (Exception e) {
	    	return null;
	    }
	 }
	
	/**
	  * 根据属性名设置属性值
	  *
	  * @param fieldName
	  * @param object
	  * @return
	  */
	 public static void setFieldValueByFieldName(Object object, String fieldName, String value) throws Exception {
	   	Class<? extends Object> c = object.getClass();
	   	Field f = c.getDeclaredField(fieldName);
	   	f.setAccessible(true);
	   	f.set(object, value);
	 }
}
