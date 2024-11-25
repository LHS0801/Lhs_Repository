package com.example.ihatesick.user.config;

import com.example.ihatesick.user.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")  // 모든 요청에 인터셉터 적용
                .excludePathPatterns("/mainpage", "/customer_login", "/customer_join", "/resources/**",
                        "/static/**",        // 추가: static 디렉토리 경로
                        "/css/**",           // 추가: CSS 경로
                        "/js/**",            // 추가: JavaScript 경로
                        "/img/**",
                        "/hospital_info");  // 로그인, 회원가입, 정적 리소스는 제외
    }
}
