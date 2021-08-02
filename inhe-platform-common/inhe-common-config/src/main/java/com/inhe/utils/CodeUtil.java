package com.inhe.utils;

import java.util.UUID;

public class CodeUtil {
	
	public static String getMqCode(){
		String id=UUID.randomUUID().toString();
		id=id.replace("-", "");
		return id;
	}
	
	public static String getLogCode(){
		String id = System.currentTimeMillis() + "";
		while (id.length() < 20) {
			id = id + (int)(Math.random() * 10);
		}
		return id;
	}
	
	public static String getPlanCode(){
		String id = System.currentTimeMillis() + "";
		while (id.length() < 20) {
			id = id + (int)(Math.random() * 10);
		}
		return id;
	}
}
