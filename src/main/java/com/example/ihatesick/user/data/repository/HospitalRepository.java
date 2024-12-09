package com.example.ihatesick.user.data.repository;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    List<HospitalEntity> findByCode(String code);  // code로 병원 목록 조회
}
