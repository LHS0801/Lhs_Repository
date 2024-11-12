package com.example.ihatesick.user.service;

import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private CustomerRepository customerRepository;

    // 로그인 인증 메서드 수정
    public String authenticate(String customer_id, String customer_password) {
        CustomerEntity customer = customerRepository.findByCustomerId(customer_id);

        if (customer == null) {
            // 아이디가 존재하지 않으면
            return "아이디가 존재하지 않습니다.";
        }

        if (!customer.getPassword().equals(customer_password)) {
            // 비밀번호가 틀리면
            return "비밀번호가 틀렸습니다.";
        }

        // 아이디와 비밀번호가 모두 맞으면
        return null; // 인증 성공 시 null 반환
    }
}
