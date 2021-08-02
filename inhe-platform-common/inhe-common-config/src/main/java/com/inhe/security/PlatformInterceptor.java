package com.inhe.security;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inhe.build.utils.SpringUtil;

@Component
public class PlatformInterceptor implements HandlerInterceptor {

	@Autowired
	AccessControlService iAuthService;

	@Value("#{'${spring.mvc.white-list}'.split(',')}")
	private List<String> whiteList;

	Map<String, ISecurity> map = null;
	
	private void init() {
		map = SpringUtil.getApplicationContext().getBeansOfType(ISecurity.class);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURI().toString();
		
		if("/".equals(url) || "/index.html".equals(url) || "/error".equals(url)){
			return true;
		}
		
		for(int i=0;i<whiteList.size();i++) {
			
			String wUrl = whiteList.get(i);
			
			if(url.equals(wUrl)){
				return true;
			}
			if(wUrl.endsWith("*")){
				wUrl = wUrl.substring(0,wUrl.length() - 1);
				if(url.startsWith(wUrl)){
					return true;
				}
			}
		}
		if (map == null){
			init();
		}
		String token = request.getHeader("Access-Token");
		boolean result = iAuthService.isPass(token, url, request, response);
		for (String key : map.keySet()) {
			map.get(key).process(url, token, request, response);
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/*if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			handlerMethod.getMethod().getName();
			Parameter[] ps = handlerMethod.getMethod().getParameters();
			Annotation[] as = handlerMethod.getMethod().getAnnotations();
			for (Annotation a : as) {
				if (a instanceof PostMapping) {
					String uri = request.getRequestURI();
					String userId = (String) request.getAttribute(CommonFlag.REQUEST_USER_ID);
					String orgId = (String) request.getAttribute(CommonFlag.REQUEST_ORG_ID);
					SysLog log = new SysLog();
					log.setFunId(uri);
					log.setOrgId(orgId);
					log.setUserId(userId);
					log.setOperTime(new Date());
					log.setIp(StringUtil.getRemoteAddress(request));
					if (((PostMapping) a).value().length != 0)
						log.setActionId(((PostMapping) a).value()[0]);
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < ps.length; i++) {
						sb
						.append(i + 1)
						.append(" Parameter :")
						.append(ps[i].getClass().toString().toString())
						.append(";");
					}
					log.setContent(sb.toString());
					System.out.println(log.toString());
				}
			}
		}*/
	}
}