package com.example.myrok.controller;

import com.example.myrok.service.oauth.OAuth2Service;
import com.example.myrok.type.LoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myrok/auth")
public class AuthController {


    private final OAuth2Service oAuth2Service;


    //callback url 정보 담아오기
    @ResponseBody
    @GetMapping("/google")
    public Map<String, String> getGoogleRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("loginUrl", oAuth2Service.getRedirectUrl(LoginProvider.GOOGLE));
        return map;
    }

    @GetMapping("/callback")
    public String getGoogleAccessToken(String code, String scope, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, LoginProvider.GOOGLE);
        String loginUrl = oAuth2Service.login(accessToken, LoginProvider.GOOGLE);
        return "redirect:" + loginUrl;
    }

}
