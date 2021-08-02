package com.inhe.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	public static final String JWT_TYPE = "tokenType";
	public static final String JWT_USERID = "userID";
	public static final String JWT_ROLE = "role";
	public static final String JWT_ORG = "org";
	public static final String JWT_DEPT = "dept";
	public static final String JWT_DEPLOYID = "deployId";
	public static final String JWT_LOCKTIME = "lockTime";
	private static String secret = "inhe@2021011914:40";

	public static String createUserToken(String id, String roles, String org, String dept, Integer validity, Integer lockTime)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		Date iDate = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, validity);

		Map<String, Object> type = new HashMap<String, Object>();
		type.put("alg", "HS256");
		type.put("typ", "JWT");
		String token = JWT.create().withHeader(type)
				.withClaim(JWT_TYPE, "0")
				.withClaim(JWT_USERID, id)
				.withClaim(JWT_ROLE, roles)
				.withClaim(JWT_ORG, org)
				.withClaim(JWT_DEPT, dept)
				.withClaim(JWT_LOCKTIME, lockTime)
				.withExpiresAt(c.getTime())
				.withIssuedAt(iDate)
				.sign(Algorithm.HMAC256(secret));
		return token;
	}
	
	public static String createServerToken(String id, int validity)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		Date iDate = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, validity);

		Map<String, Object> type = new HashMap<String, Object>();
		type.put("alg", "HS256");
		type.put("typ", "JWT");
		String token = JWT.create().withHeader(type)
				.withClaim(JWT_TYPE, "1")
				.withClaim(JWT_DEPLOYID, id)
				.withExpiresAt(c.getTime())
				.withIssuedAt(iDate)
				.sign(Algorithm.HMAC256(secret));
		return token;
	}

	public static Map<String, Claim> decodeToken(String token)
			throws IllegalArgumentException, UnsupportedEncodingException {
		JWTVerifier v = JWT.require(Algorithm.HMAC256(secret)).build();
		DecodedJWT jwt = null;
		jwt = v.verify(token);
		return jwt.getClaims();
	}
}
