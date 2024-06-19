package com.example.myrok.config;

import com.example.myrok.security.filter.JWTCheckFilter;
import com.example.myrok.security.filter.TokenRefreshFilter;
import com.example.myrok.security.handler.APILoginFailureHandler;
import com.example.myrok.security.handler.APILoginSuccessHandler;
import com.example.myrok.security.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception:
//    이 메소드는 SecurityFilterChain 타입의 빈을 생성하고 반환합니다.
//    HttpSecurity 객체는 웹 보안 설정을 구성하는 데 사용됩니다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------security config-------------------");

        //header 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        //세션 못만들도록 만드는것
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
        });

        //csrf설정 -> request 위조 방지
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // 폼 로그인을 활성화하고, 로그인 페이지를 /api/member/login으로 설정합니다. 이 설정은 사용자가 로그인할 때 사용할 페이지를 지정합니다.
        http.formLogin(config -> {
            //로그인 페이지
            config.loginPage("/api/member/login");
            //로그인 성공시 핸들러 사용 -> 성공시 핸들러 사용
            config.successHandler(new APILoginSuccessHandler());
        });

        http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(config ->{
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        return http.build();
    }

    //password
    //사용자 계정 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //ajax 사용시 cross origin 문제 해결
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
