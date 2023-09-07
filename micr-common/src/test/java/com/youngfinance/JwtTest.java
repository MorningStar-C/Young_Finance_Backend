package com.youngfinance;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTest {

    // 创建Jwt
    @Test
    public void testCreatJwt() {
//        String s = UUID.randomUUID().toString();
//        System.out.println(s);
        String key = "fa8b00d4c8964aa29b427a8109bb9a35";

        // 创建SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        Date curDate = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("userId", 1001);
        data.put("name", "Bob");
        data.put("role", "经理");
        // 创建Jwt
        String jwt = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, 10))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString())
                .addClaims(data).compact();

        System.out.println("jwt: " + jwt);
    }

    @Test
    public void testReadJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTM5ODc4NDIsImlhdCI6MTY5Mzk4NzI0MiwianRpIjoiMzAwMDZiNTItNmQ2MS00ODlmLWFkNmUtNjA5YWU1YTgzZjNlIiwicm9sZSI6Iue7j-eQhiIsIm5hbWUiOiJCb2IiLCJ1c2VySWQiOjEwMDF9.G2fHqjOFOj16fPHSMf_C-45vE8RPEdkj10Cjz9ZAA-Q";
        String key = "fa8b00d4c8964aa29b427a8109bb9a35";

        // 创建SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        // 解析Jwt, 没有异常则解析成功
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build()
                                .parseClaimsJws(jwt);
        // 读数据
        Claims body = claimsJws.getBody();
        Integer userId = body.get("userId", Integer.class);
        System.out.println("userId: " + userId);

        Object userId1 = body.get("userId");
        System.out.println("userId1: " + userId1);

        Object name = body.get("name");

        if(name != null) {
            String str = (String) name;
            System.out.println("name: " + str);
        }

        String jwtId = body.getId();
        System.out.println("jwtId: " + jwtId);

        Date expiration = body.getExpiration();
        System.out.println("expiration: " + expiration);

    }
}
