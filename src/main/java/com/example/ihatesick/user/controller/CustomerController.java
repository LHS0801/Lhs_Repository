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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class CustomerController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HospitalService hospitalService;

    // 메인 페이지 접근 시 병원 목록 불러오기
    @GetMapping("/mainpage")
    public String showMainPage(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        // 로그인 상태 확인
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        model.addAttribute("loggedInUser", loggedInUser);

        // 로그인 상태일 때만 로그아웃 버튼을 보여주기 위해
        if (loggedInUser != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "mainpage";  // mainpage.html로 병원 목록 전달
    }

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

            return "customer_logout";  // 수정: 모델을 사용하도록 변경
        } else {
            model.addAttribute("error", errorMessage);
            return "customer_login";
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
    public String logout(HttpSession session, Model model) {
        // 세션 무효화
        session.invalidate();

        // 병원 목록을 모델에 추가 (로그인 상태가 아니므로 병원 목록을 메인 페이지에 추가)
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        // 메인 페이지로 리다이렉트
        return "redirect:/mainpage";
    }

    // 메인 페이지
    @GetMapping("/")
    public String mainPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "customer_login"; // 로그인되지 않은 사용자는 로그인 페이지로 리다이렉트
        }
        return "mainpage"; // 로그인된 사용자는 메인 페이지로 이동
    }

    @GetMapping("/hospital_info/{id}")
    public String hospital_info(@PathVariable("id") Long id, Model model) {
        // 병원 정보를 DB에서 조회 (서비스 메서드 호출)
        HospitalEntity hospital = hospitalService.getHospitalById(id);

        if (hospital != null) {
            model.addAttribute("hospital", hospital);
        } else {
            model.addAttribute("error", "병원 정보를 찾을 수 없습니다.");
        }
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

