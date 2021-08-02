package com.inhe.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: LogUtil
 * @Description: 日志工具类，记录参数、异常、返回值日志
 * @author sunlz@gxmis.com
 * @date 2012-3-17 上午10:15:16
 * 
 */
@Aspect
@Component
public class LogUtil {

	public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 声明 记录参数、返回值信息 日志切入点
	 */
	@Pointcut("execution(* com.inhe.service.impl1.*.*(..))")
	public void infoLogMethod() {
	}
	
	/**
	 * 声明异常日志切入点
	 */
	@Pointcut("execution(* com.inhe.service.impl1.*.*(..))")
	public void exceptionLogMethod() {
	}

	/**
	 * 记录接口参数日志
	 * @param jp
	 */
	@Before("infoLogMethod()")
	public void beforFunctionExce(JoinPoint jp) {
		Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		Object[] objs = jp.getArgs();
		StringBuffer sb = new StringBuffer();
		sb.append(jp.getSignature().getName()+"-Parameter : ");
		for (Object o : objs) {
			sb.append("[");
			if (o != null) {
				if (o instanceof Date) {
					sb.append(LOG_DATE_FORMAT.format(o));
				} else {
					Class<?> clazz = o.getClass();
					if(clazz.isArray()){
						sb.append(Arrays.toString((Object[])o));
					}else{
						sb.append(o.toString());
					}
				}
			}
			sb.append("],");
		}
		sb.deleteCharAt(sb.length() - 1);
		logger.info(sb.toString());
	}

	/**
	 * 记录接口返回值日志
	 * @param jp
	 * @param rtn
	 */
	@AfterReturning(value = "infoLogMethod()", argNames = "rtn", returning = "rtn")
	public void returnFunctionExce(JoinPoint jp, Object rtn) {
		Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		logger.info(jp.getSignature().getName() + "-Return : " + rtn);
	}
	
	/**
	 * 异常发生时记录日志
	 * 
	 * @param jp
	 * @param e
	 */
	@AfterThrowing(pointcut = "exceptionLogMethod()", throwing = "e")
	public void afterThrowingLog(JoinPoint jp, Exception e) {
		Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		
		Object[] objs = jp.getArgs();
		StringBuffer sb = new StringBuffer();
		sb.append(jp.getSignature().getName() + "-Parameter : ");
		for (Object o : objs) {
			sb.append("[");
			if (o != null) {
				if (o instanceof Date) {
					sb.append(LOG_DATE_FORMAT.format(o));
				} else {
					Class<?> clazz = o.getClass();
					if(clazz.isArray()){
						sb.append(Arrays.toString((Object[])o));
					}else{
						sb.append(o.toString());
					}
				}
			}
			sb.append("],");
		}
		sb.deleteCharAt(sb.length() - 1);
		logger.error(e.getMessage()+sb.toString(), e);
	}
}
