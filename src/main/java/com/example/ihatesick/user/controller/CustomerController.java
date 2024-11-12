package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.repository.CustomerRepository;
import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.service.LoginService;
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
    @GetMapping("/customer_login")
    public String showLoginPage() {
        return "customer_login"; // 로그인 페이지로 이동
    }

    @PostMapping("/customer_login")
    public String customer_login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 Model model) {
        System.out.println("ID: " + username);
        System.out.println("PW: " + password);
        String result = loginService.authenticate(username, password); // 인증 메서드 호출
        if ("성공".equals(result)) {
            return "redirect:/mainpage"; // 로그인 성공 시 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", result); // 에러 메시지를 모델에 추가
            return "customer_login"; // 로그인 페이지로 돌아감
        }
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

