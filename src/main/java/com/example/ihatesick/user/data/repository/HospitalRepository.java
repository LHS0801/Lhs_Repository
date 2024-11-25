package com.example.ihatesick.user.data.repository;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

}
