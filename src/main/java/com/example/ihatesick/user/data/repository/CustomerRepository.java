package com.example.ihatesick.user.data.repository;

import com.example.ihatesick.user.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findById(String customer_id);
}
