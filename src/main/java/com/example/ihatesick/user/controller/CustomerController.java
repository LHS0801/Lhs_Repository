package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.repository.CustomerRepository;
import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller

public class CustomerController {


    @GetMapping("/mainpage")
    public String mainpage(){
        return "mainpage";
    }

    @Autowired
    private LoginService loginService;

    // 로그인 처리
    @PostMapping("/customer_login")
    public String customer_login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session, Model model) {
        System.out.println("ID: " + username);
        System.out.println("PW: " + password);

        // 아이디와 비밀번호를 확인하고 오류 메시지 받기
        String errorMessage = loginService.authenticate(username, password);
        if (errorMessage == null) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("loggedInUser", username);
            return "customer_logout"; // 로그인 후 로그아웃 페이지로 리다이렉트
        } else {
            // 로그인 실패 시 오류 메시지를 모델에 추가하여 로그인 페이지로 돌아감
            model.addAttribute("error", errorMessage);
            return "customer_login"; // 로그인 페이지로 돌아감
        }
    }

    // 로그인 페이지
    @GetMapping("/customer_login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/customer_logout"; // 이미 로그인한 사용자는 로그아웃 페이지로 리다이렉트
        }
        return "customer_login"; // 로그인하지 않은 사용자는 로그인 페이지로 이동
    }

    // 로그아웃 처리
    @GetMapping("/customer_logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/mainpage"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    // 메인 페이지
    @GetMapping("/")
    public String mainPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "customer_login"; // 로그인되지 않은 사용자는 로그인 페이지로 리다이렉트
        }
        return "main"; // 로그인된 사용자는 메인 페이지로 이동
    }

    @GetMapping("/hospital_info")
    public String hospital_info(){
        return "hospital_info";
    }

    @GetMapping("/business_login")
    public String business_login(){
        return "business_login";
    }

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer_join")
    public String customer_join(Model model) {
        model.addAttribute("customer", new CustomerEntity());
        return "customer_join";
    }

    @PostMapping("/customer_join")
    public String submitForm(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("gender") String gender,
                             @RequestParam("birth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth,
                             @RequestParam("phone") String phone,
                             Model model) {

        CustomerEntity customer = new CustomerEntity();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setBirth(birth);
        customer.setPhone(phone);

        customerRepository.save(customer);

        model.addAttribute("message", "회원가입이 완료되었습니다!");
        return "join_success";
    }
}

