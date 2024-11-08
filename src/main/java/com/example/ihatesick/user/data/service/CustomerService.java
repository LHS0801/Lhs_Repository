package com.example.ihatesick.user.data.service;

import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.data.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Service  // Spring에서 자동으로 관리되는 빈으로 등록되도록 @Service 애너테이션을 추가
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    // 생성자 주입을 통해 CustomerRepository를 주입받습니다.
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 사용자 이름으로 사용자를 로드하는 메서드입니다.
     * @param customerId 사용자 ID (username)으로 사용자를 조회
     * @return UserDetails - Spring Security에서 사용되는 사용자 정보 객체
     * @throws UsernameNotFoundException 사용자 정보가 없을 경우 예외 발생
     */
    @Override
    public UserDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
        // customerId로 사용자를 조회합니다.
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID " + customerId + " not found"));

        // 조회된 사용자 정보를 기반으로 UserDetails 객체를 반환합니다.
        return new User(customer.getId(), customer.getPassword(), getAuthorities(customer));
    }

    /**
     * 사용자에게 부여된 권한을 반환하는 메서드입니다.
     * @param customer CustomerEntity - 사용자 엔티티
     * @return Collection<GrantedAuthority> - 사용자의 권한 목록
     */
    private Collection<? extends GrantedAuthority> getAuthorities(CustomerEntity customer) {
        // 사용자의 역할(roles)을 Stream으로 처리하여 GrantedAuthority로 변환
        return customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role))  // 역할을 GrantedAuthority 객체로 변환
                .collect(Collectors.toList());  // List로 반환
    }
}
