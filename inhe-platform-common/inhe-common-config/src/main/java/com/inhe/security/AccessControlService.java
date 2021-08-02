package com.inhe.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;
import com.inhe.build.redis.InheRedisTemplate;
import com.inhe.constant.RedisKeyParam;
import com.inhe.utils.JwtUtil;

@Service
public class AccessControlService {
	@Autowired
	InheRedisTemplate inheRedisTemplate;
	
	@Value("${spring.application.name}")
	private String appName;

	public boolean isPass(String token, String url, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Claim> map = new HashMap<String, Claim>();
		if (token == null || "".equals(token)) {
			this.response(response,-100017,"Token不能为空");
			return false;
		}
		try {
			map = JwtUtil.decodeToken(token);
		} 
		catch (Exception e) {
			this.response(response,-100018,"无效的Token: "+e.getMessage());
			return false;
		}
		if(map == null){
			this.response(response,-100018,"无效的Token");
			return false;
		}
		
		String tokenType = map.get(JwtUtil.JWT_TYPE).asString();
		if("0".equals(tokenType)){
			Integer lockTime = map.get(JwtUtil.JWT_LOCKTIME).asInt();
			if(lockTime == 0){
				refreshToken(map, response);
			}
			else
			if(lockTime > 0){
				refreshTokenLock(map, response, lockTime);
			}
			return checkUser(map, url, request, response);
		}
		else
		if("1".equals(tokenType)){
			
			return checkServer(map, url, request, response);
		}
		
		this.response(response,-100029,"找不到Token类型: "+tokenType);
		return false;
	}
	
	/**
	 * token刷新规则一：无锁屏
	 * token有效期大于有效期的一半时刷新token;<br /> 
	 * 如token生成时间10点，token过期时间11点，刷新token时间在10:30到11:00之间
	 * @param map
	 * @return token
	 */
	private void refreshToken(Map<String, Claim> map, HttpServletResponse response) {
		Date exp = map.get(PublicClaims.EXPIRES_AT).asDate();
		Date iat = map.get(PublicClaims.ISSUED_AT).asDate();
		long expDist = exp.getTime() - iat.getTime();
		long outTime = iat.getTime() + expDist / 2;
		if (outTime < System.currentTimeMillis()) {
			try {
				String userId = map.get(JwtUtil.JWT_USERID).asString();
				String role = map.get(JwtUtil.JWT_ROLE).asString();
				String orgId = map.get(JwtUtil.JWT_ORG).asString();
				String deptId = map.get(JwtUtil.JWT_DEPT).asString();
				
				String token = JwtUtil.createUserToken(userId, role, orgId, deptId, (int) (expDist / 1000), 0);
				response.setHeader("refresh_token", token);
				response.setHeader("refresh_exp", expDist /1000 + "");
			} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
				
			}
		}
	}
	
	/**
	 * token刷新规则二，有锁屏
	 * 生成时间超过10秒更新;<br /> 
	 * 锁屏判断;<br />
	 * @param map
	 * @return token
	 */
	private void refreshTokenLock(Map<String, Claim> map, HttpServletResponse response, Integer lockTime) {
		Date exp = map.get(PublicClaims.EXPIRES_AT).asDate();
		Date iat = map.get(PublicClaims.ISSUED_AT).asDate();
		long expDist = exp.getTime() - iat.getTime();
		//如果超过锁屏时间，则启动锁屏
		if(System.currentTimeMillis() - iat.getTime() > lockTime * 1000){
			response.setHeader("is_lock", "1");
		}
		else
		if (System.currentTimeMillis() - iat.getTime() > 10 * 1000) {
			try {
				String userId = map.get(JwtUtil.JWT_USERID).asString();
				String role = map.get(JwtUtil.JWT_ROLE).asString();
				String orgId = map.get(JwtUtil.JWT_ORG).asString();
				String deptId = map.get(JwtUtil.JWT_DEPT).asString();
				String token = JwtUtil.createUserToken(userId, role, orgId, deptId, (int) (expDist / 1000), lockTime);
				response.setHeader("refresh_token", token);
				response.setHeader("refresh_exp", expDist /1000 + "");
			
			} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
				
			}
		}
	}
	
	private boolean checkUser(Map<String, Claim> map,String url, HttpServletRequest request, HttpServletResponse response){
		String userRole = map.get(JwtUtil.JWT_ROLE).asString();
		String userID = map.get(JwtUtil.JWT_USERID).asString();
		String orgID = map.get(JwtUtil.JWT_ORG).asString();
		String deptID = map.get(JwtUtil.JWT_DEPT).asString();
		if(userRole==null || "".equals(userRole) || userID==null || "".equals(userID) || orgID==null || "".equals(orgID)) {
			this.response(response,-100018,"无效的Token");
			return false;
		}

		request.setAttribute(JwtUtil.JWT_USERID, userID);
		request.setAttribute(JwtUtil.JWT_ORG, orgID);
		request.setAttribute(JwtUtil.JWT_DEPT, deptID);
		request.setAttribute(JwtUtil.JWT_ROLE, userRole);
		
		String activeUrl = url.split("-")[0];
		if(activeUrl.endsWith("public")){
			return true;
		}
		
		String redisRoleKey = RedisKeyParam.URL_TO_ROLE + appName;
		String urlRole = inheRedisTemplate.getValue(redisRoleKey + activeUrl);
		if(urlRole==null || "".equals(urlRole)){
			this.response(response,-100019,"您没有权限访问此功能: "+url);
			return false;
		}
		JSONObject urlRoleMap = JSONObject.parseObject(urlRole);
		
		String [] roleList = userRole.split(",");
		boolean checked = false;
		for (String role : roleList) {
			if (urlRoleMap.containsKey(role)){
				checked = true;
				break;
			}
		}
		if(!checked){
			this.response(response,-100019,"您没有权限访问此功能:"+ url);
			return false;
		}
		return true;
	}
	
	private boolean checkServer(Map<String, Claim> map,String url, HttpServletRequest request, HttpServletResponse response){
		String deployId = map.get(JwtUtil.JWT_DEPLOYID).asString();
		if(deployId==null || "".equals(deployId)) {
			this.response(response,-100018,"无效的Token");
			return false;
		}
		request.setAttribute(JwtUtil.JWT_DEPLOYID, deployId);
		return true;
	}

	/**
	 * 响应给客户端
	 * @param response
	 * @param code
	 * @param message
	 */
	public void response(HttpServletResponse response,Integer code,String message) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("message", message);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
