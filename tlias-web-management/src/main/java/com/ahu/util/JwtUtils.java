package com.ahu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "TXlTdXBlclNlY3VyZVRlc3RLZXlAMjAyNkFCQ0RFR" +
            "jEyMzQ1Njc4OTBaWE4wTE5CaGRHZ3VjM04wYUhWeVBHOHpZVzVqYUdWc2JHOWpZWFJwY0M5b2IzTmhjbVZrYVc4";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8)))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}
