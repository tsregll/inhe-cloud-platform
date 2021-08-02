package com.inhe.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jzm
 * 过滤器，处理body多次读取问题
 */
public class HttpServletRequestWrapperFilter implements Filter {
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        ServletRequest requestWrapper = null;
 
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            //遇到post方法才对request进行包装
            String methodType = httpRequest.getMethod();
            if ("POST".equals(methodType)) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(
                        (HttpServletRequest) request);
            }
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }
 
    @Override
    public void destroy() {
 
    }

}
