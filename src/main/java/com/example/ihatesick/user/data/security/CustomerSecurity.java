package com.example.ihatesick.user.data.security;

import com.example.ihatesick.user.data.service.CustomerService;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Getter
@Configuration
@EnableWebSecurity
public class CustomerSecurity {

    private final CustomerService customerService;

    public CustomerSecurity(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz ->
                        authz
                                .requestMatchers("/customer_login", "/mainpage", "/customer_join","/hospital_info").permitAll()  // 로그인과 회원가입 페이지는 모두 접근 가능
                                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()  // 정적 파일은 모두 접근 가능
                                .anyRequest().authenticated()  // 나머지 페이지는 인증 필요
                )
                .formLogin(form ->
                        form
                                .loginPage("/customer_login")  // 로그인 페이지 URL
                                .usernameParameter("customerId")  // 로그인 폼에서 사용할 username 파라미터
                                .passwordParameter("password")  // 로그인 폼에서 사용할 비밀번호 파라미터
                                .defaultSuccessUrl("/login_success", true)  // 로그인 성공 후 리다이렉트 URL
                                .permitAll()  // 로그인 페이지는 누구나 접근 가능
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/customer_logout")  // 로그아웃 URL
                                .logoutSuccessUrl("/mainpage")  // 로그아웃 후 리다이렉트 URL
                                .permitAll()  // 로그아웃 페이지는 누구나 접근 가능
                )
                .csrf(csrf -> csrf.disable());  // CSRF 보호 비활성화 (필요한 경우)

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
