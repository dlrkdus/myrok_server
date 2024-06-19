package com.example.myrok.util;

import com.example.myrok.exception.CustomJWTException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {
    private static String jwtKey;
    @Value("${jwt.key}")
    public void setJwtKey(String value){
        jwtKey = value;
    }
    //jwt token 을 만든다
    //min -> 유효시간을 통해 검증을 할 수 있으며 accessToken을 만드는데 사용한다

    public static String generateToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;
        try{
            key = Keys.hmacShaKeyFor(jwtKey.getBytes("UTF-8"));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ","JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
        return jwtStr;
    }

    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;
        try{
            SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes("UTF-8"));
            claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                    .getBody();
        }catch(MalformedJwtException malformedJwtException){
            throw new CustomJWTException("MalFormed");
        }catch(ExpiredJwtException expiredJwtException){
            throw new CustomJWTException("Expired");
        }catch(InvalidClaimException invalidClaimException){
            throw new CustomJWTException("Invalid");
        }catch(JwtException jwtException){
            throw new CustomJWTException("JWTError");
        }catch(Exception e){
            throw new CustomJWTException("Error");
        }
        return claim;
    }
}
