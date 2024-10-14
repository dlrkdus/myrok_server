package com.example.myrok.controller;

import com.example.myrok.exception.CustomJWTException;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {

    @RequestMapping("/myrok/reissue/google")
    public Map<String, Object> refresh(
            @RequestHeader("Authorization") String authHeader,
            String refreshToken
    ){
        if(refreshToken == null){
            throw new CustomJWTException(ErrorCode.NULL_REFRESH);
        }

        if(authHeader == null || authHeader.length() < 7){
            throw new CustomJWTException(ErrorCode.MALFORMED);
        }

        //Bearer //7
        String accessToken = authHeader.substring(7);

        //accessToken의 만료 여부 확인
        //만료가 되지 않은 경우 괜춘
        if(checkExpiredToken(accessToken) == false){
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }

        //Refresh토큰 검증
        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
        log.info("refresh ... claims: " + claims);
        //다시 accessToken을 만들어준다
        String newAccessToken = JWTUtil.generateToken(claims, 24 * 60 * 36);
        //refresh token 유효 시간 별로 남지 않았을 경우
        String newRefreshToken = checkTime((Integer)claims.get("exp")) == true ?
                JWTUtil.generateToken(claims, 24 * 60 * 36) : refreshToken;
        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    private boolean checkTime(Integer exp) {
        //JWT exp를 날짜로 변환
        java.util.Date expDate = new java.util.Date( (long)exp * (1000 ));
        //현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = expDate.getTime() - System.currentTimeMillis();
        //분단위 계산
        long leftMin = gap / (1000 * 60);
//1시간도 안남았는지..
        return leftMin < 60;
    }


    private boolean checkExpiredToken(String token) {
        try{
            JWTUtil.validateToken(token);
        }catch(CustomJWTException ex) {
            if(ex.getErrorCode() == ErrorCode.EXPIRED){
                return true;
            }
        }
        return false;
    }

}
