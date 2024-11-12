package com.example.ihatesick.user.data.repository;

import com.example.ihatesick.user.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByCustomerId(String customerId);
}
