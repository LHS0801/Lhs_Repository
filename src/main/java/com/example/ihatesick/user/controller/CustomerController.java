package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import com.example.ihatesick.user.data.repository.CustomerRepository;
import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.service.HospitalService;
import com.example.ihatesick.user.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller

public class CustomerController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HospitalService hospitalService;

    // 로그인 처리
    @PostMapping("/customer_login")
    public String customer_login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session, Model model) {

        String errorMessage = loginService.authenticate(username, password);
        if (errorMessage == null) {
            session.setAttribute("loggedInUser", username);

            // 병원 목록을 모델에 추가
            List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
            model.addAttribute("hospitals", hospitals);

            return "mainpage_log_1";
        } else {
            model.addAttribute("error", errorMessage);
            return "customer_login";
        }
    }

    // 로그인 페이지
    @GetMapping("/customer_login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/mainpage_log_1"; // 이미 로그인한 사용자는 로그아웃 페이지로 리다이렉트
        }
        return "customer_login"; // 로그인하지 않은 사용자는 로그인 페이지로 이동
    }

    // 로그아웃 처리
    @GetMapping("/mainpage_log_1")
    public String logout(HttpSession session, Model model) {
        // 세션 무효화
        session.invalidate();

        // 병원 목록을 모델에 추가 (로그인 상태가 아니므로 병원 목록을 메인 페이지에 추가)
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        return "redirect:/customer_login";
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

