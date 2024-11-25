package com.example.ihatesick.user.service;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import com.example.ihatesick.user.data.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<HospitalEntity> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // 병원 ID로 병원 정보 조회
    public HospitalEntity getHospitalById(Long id) {
        // 병원 ID에 해당하는 병원을 데이터베이스에서 조회
        return hospitalRepository.findById(id).orElse(null);  // 병원이 없으면 null 반환
    }
}
