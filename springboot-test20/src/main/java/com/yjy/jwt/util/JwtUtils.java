package com.yjy.jwt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
	
	private final static String SUBJECT_KEY = "mySubject";
	private final static String ISSUER_KEY = "myIssuer";
	// 签名算法
	private final static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	// 签名密钥：写死，若要根据用户表的密码，则每次验证token都要查询一遍用户表
	private final static String SECRET_KEY = "42a93232-faa7-4075-b094-33a86d0484a6";
	
	/**
	 * 生成token
	 * @param username 用户名（也可以传入user类，这样就能保存多个属性）
	 */
	public static String createToken(String username) {
		// 设置过期时间 1分钟
		long nowMillis = System.currentTimeMillis();
		long ttlMillis = 60 * 1000;
		Date now = new Date(nowMillis);
		Date exp = new Date(nowMillis + ttlMillis);
		
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("alg", "HS256");
		headerMap.put("typ", "JWT");

		Map<String, Object> claimMap = new HashMap<>();
		claimMap.put("username", username);
		claimMap.put("realName", username);
		
		try {
			// 生成token
			String token = Jwts.builder()
					.setHeaderParams(headerMap) // 头部
					// 公共声明的6个jwt标准字段：sub-面向的客户，iss-签发者，iat-签发时间，exp-过期时间，nbf-在此期间不接收处理，jti-jwt唯一标识
					.setSubject(SUBJECT_KEY).setIssuer(ISSUER_KEY).setIssuedAt(now).setExpiration(exp)
					.addClaims(claimMap) // 私有声明
					.signWith(signatureAlgorithm, SECRET_KEY.getBytes()).compact(); // 签证
			return token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 解析token
	 */
	public static Claims parseToken(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		
		System.out.println("jwt的header：" + claimsJws.getHeader());
		System.out.println("jwt的payload:" + body);
		
		if (body.getExpiration().before(new Date())) { //当前时间 > 过期时间
			throw new RuntimeException("token已过期");
		}
		return body;
	}
	
	// 测试
	public static void main(String[] args) {
		String token = JwtUtils.createToken("user1");
		System.out.println("token:" + token);
		Claims claims = JwtUtils.parseToken(token);
		System.out.println(claims.get("username", String.class));
	}
}
