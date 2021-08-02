package com.inhe.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ISecurity {
	
	public void process(String url, String token, HttpServletRequest request, HttpServletResponse response);
}
