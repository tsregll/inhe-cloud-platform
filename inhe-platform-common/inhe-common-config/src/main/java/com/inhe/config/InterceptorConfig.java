package com.inhe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.inhe.security.PlatformInterceptor;

@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	PlatformInterceptor myInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor).addPathPatterns("/**");
	}
}