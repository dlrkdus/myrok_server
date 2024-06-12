package com.example.myrok.service;


import com.example.myrok.domain.Member;
import com.example.myrok.dto.MemberSecurityDTO;
import com.example.myrok.dto.classtype.MemberDTO;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.type.LoginProvider;
import com.example.myrok.type.MemberRole;
import com.example.myrok.util.JWTUtil;
import com.example.myrok.util.OAuth2Util;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service{

    public final PasswordEncoder passwordEncoder;
    public final OAuth2Util oAuth2Util;
    private final MemberRepository memberRepository;
    public String getRedirectUrl(LoginProvider provider) {
        if (provider == LoginProvider.GOOGLE) {
            return oAuth2Util.getGoogleRedirectUrl();
        }
        return null;
    }

    public String getAccessToken(String authorizationCode, LoginProvider provider) {
        String accessToken = null;
        if (provider == LoginProvider.GOOGLE) {
            accessToken = oAuth2Util.getGoogleAccessToken(authorizationCode);
        }
        return accessToken;
    }

    public void login(HttpServletResponse response, String accessToken, LoginProvider provider) throws IOException {
        MemberDTO.MemberInformation memberInformation;
        if (provider == LoginProvider.GOOGLE) {
            memberInformation = oAuth2Util.getGoogleUserInfo(accessToken);
        } else {
            memberInformation = null;
        }

        if (memberInformation == null) {
            throw new RuntimeException();
        }

        Member member = memberRepository.findBySocialIdAndLoginProvider(memberInformation.getSocialId(), provider)
                .orElseGet(() ->
                        memberRepository.save(Member.builder()
                                .socialId(memberInformation.getSocialId())
                                .loginProvider(provider)
                                .name(memberInformation.getName())
                                .email(memberInformation.getEmail())
                                .password(passwordEncoder.encode(memberInformation.getSocialId()))
                                .imgUrl(memberInformation.getPostUrl())
                                .memberRoleList(Arrays.asList(MemberRole.USER))
                                .memberRoleList(Arrays.asList(MemberRole.USER))
                                .build())
                );

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(member.getSocialId(), member.getPassword(), member.getMemberRoleList().stream().map(Enum::toString).collect(Collectors.toList()), member.getName());

        Map<String, Object> claims = memberSecurityDTO.getClaims();

        String jwtToken = JWTUtil.generateToken(memberSecurityDTO.getClaims(), 10); //지금 당장 사용할 수 있는 권리
        String jwtRefreshToken = JWTUtil.generateToken(memberSecurityDTO.getClaims(), 60 * 24); //교환권

        claims.put("accessToken", jwtToken);
        claims.put("refreshToken", jwtRefreshToken);

        sendAccessTokenAndRefreshToken(member, response, jwtToken, jwtRefreshToken);
    }

    public void sendAccessTokenAndRefreshToken(Member member, HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        String str = "http://localhost:3000/login?accessToken=" + accessToken + "&refreshToken=" + refreshToken;

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(str);
        printWriter.close();
    }
}