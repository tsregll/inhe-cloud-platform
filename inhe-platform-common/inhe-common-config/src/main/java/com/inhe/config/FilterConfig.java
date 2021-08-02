package com.inhe.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inhe.security.HttpServletRequestWrapperFilter;

/**
 * 配置node请求的过滤器
 * @author JXINHE1548
 */
@Configuration
public class FilterConfig {
	
	@Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> registFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> registration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        registration.addUrlPatterns("/api/client/*");
        registration.setName("HttpServletRequestWrapperFilter");
        registration.setOrder(1);
        return registration;
    }
}
