package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.repository.CustomerRepository;
import com.example.ihatesick.user.data.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller

public class CustomerController {


    @GetMapping("/mainpage")
    public String mainpage(){
        return "mainpage";
    }

    @GetMapping("/customer_login")
    public String csutomer_login(){
        return "customer_login";
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

