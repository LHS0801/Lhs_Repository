package com.example.ihatesick.user.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 로그인 상태 확인
        if (request.getSession().getAttribute("loggedInUser") == null) {
            response.sendRedirect("/customer_login");  // 로그인되지 않은 사용자는 로그인 페이지로 리다이렉트
            return false;  // 요청 처리 중단
        }
        return true;  // 로그인 상태라면 요청 계속 진행
    }
}
