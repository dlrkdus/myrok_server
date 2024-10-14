package com.example.myrok.security.filter;


import com.example.myrok.exception.CustomJWTException;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

public class TokenRefreshFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (!shouldNotFilter(request)) {
            String authHeader = request.getHeader("Authorization");
            String refreshToken = request.getParameter("refreshToken");

            if (refreshToken == null) {
                throw new CustomJWTException(ErrorCode.NULL_REFRESH);
            }

            if (authHeader == null || authHeader.length() < 7) {
                throw new CustomJWTException(ErrorCode.MALFORMED);
            }

            String accessToken = authHeader.substring(7);

            if (checkExpiredToken(accessToken) == false) {
                filterChain.doFilter(request, response);
                return;
            }

            Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
            String newAccessToken = JWTUtil.generateToken(claims, 1);
            String newRefreshToken = checkTime((Integer) claims.get("exp")) ? JWTUtil.generateToken(claims, 60 * 24) : refreshToken;

            // 새로 발급된 토큰을 헤더에 설정
            response.setHeader("Authorization", "Bearer " + newAccessToken);
            response.setHeader("Refresh-Token", newRefreshToken);
        }

        filterChain.doFilter(request, response);
    }

    private boolean checkTime(Integer exp) {
        //JWT exp를 날짜로 변환
        java.util.Date expDate = new java.util.Date((long) exp * (1000));
        //현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = expDate.getTime() - System.currentTimeMillis();
        //분단위 계산
        long leftMin = gap / (1000 * 60);
        return leftMin < 60;
    }

    private boolean checkExpiredToken(String token) {
        try {
            JWTUtil.validateToken(token);
        } catch (CustomJWTException ex) {
            if (ex.getErrorCode() == ErrorCode.EXPIRED) {
                return true;
            }
        }
        return false;
    }
}
