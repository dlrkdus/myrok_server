package com.example.myrok.security.filter;


import com.example.myrok.dto.security.MemberSecurityDTO;
import com.example.myrok.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

//모든 리케스트에 대해 동작한다
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //필터가 필요없는것
        //로그인하는 첫 페이지와 같이 필터링이 필요 없는 것

        //true == not checking
        String path = request.getRequestURI();
        if (path.startsWith("/myrok/auth/google") ||
                path.startsWith("/myrok/reissue/google") ||
                path.startsWith("/myrok/auth/callback")) {
            return true;
        }

        // Swagger UI와 관련된 요청도 필터링하지 않음
        if (path.startsWith("/swagger-ui.html") ||
                path.startsWith("/swagger-ui/") ||
                path.startsWith("/v3/api-docs")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeaderStr = request.getHeader("Authorization");

        //Bearer //7 JWT 문자열
        try {
            //Bearer accestoken...
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);
            log.info("JWT claims: " + claims);

            //사용자의 정보를 꺼낼 수 있으며 DTO구성이 가능하다
            String name = (String) claims.get("name");
            String password = (String) claims.get("password");
            String socialId = (String) claims.get("socialId");
            List<String> memberRole = (List<String>) claims.get("memberRole");

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO( socialId, password, memberRole, name);
            log.info("-----------------------------------");
            log.info(memberSecurityDTO);
            log.info(memberSecurityDTO.getAuthorities());
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberSecurityDTO,password,memberSecurityDTO.getAuthorities());
            //무상태이다
            //매번 토큰이 호출 될때마다 springsecurity에 넣어준 다음 preAuthorize되어야한다 단점 있음
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //dest
            //성공하면 다음 목적지로 보낸다
            filterChain.doFilter(request, response);
        }catch(Exception e){
            //만료와 같이 문제가 생길 경우 처리해준다
            log.error("JWT Check Error..............");
            log.error(e.getMessage());
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }
}