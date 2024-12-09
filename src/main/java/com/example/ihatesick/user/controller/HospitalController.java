package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import com.example.ihatesick.user.data.repository.HospitalRepository;
import com.example.ihatesick.user.service.HospitalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // 메인 페이지 접근 시 병원 목록 불러오기
    @GetMapping("/mainpage")
    public String showMainPage(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        return "mainpage";  // mainpage.html로 병원 목록 전달
    }


    @GetMapping("/mainpage_log")
    public String showMainPage_log(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        return "mainpage_log_1";  // mainpage.html로 병원 목록 전달
    }

    @GetMapping("/hospital_info/{id}")
    public String hospital_info(@PathVariable("id") Long id, Model model) {
        // 병원 정보를 DB에서 조회 (서비스 메서드 호출)
        HospitalEntity hospital = hospitalService.getHospital(id);

        if (hospital != null) {
            model.addAttribute("hospital", hospital);
            model.addAttribute("hospitalName", hospital.getName());  // 병원 이름 전달
        } else {
            model.addAttribute("error", "병원 정보를 찾을 수 없습니다.");
        }
        return "hospital_info";
    }

    // 카테고리별 병원 목록 보기
    @GetMapping("/hospitals/category/{code}")
    public String showHospitalsByCategory(@PathVariable("code") String code, Model model) {
        List<HospitalEntity> hospitals = hospitalService.getHospitalsByCategory(code);
        model.addAttribute("hospitals", hospitals);
        return "mainpage_log_1";  // 같은 mainpage.html을 재사용
    }
    @GetMapping("/category/mainpage_log_1")
    public String showMainPage_log_1(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "mainpage_log_1";
    }
    @GetMapping("hospital_info/mainpage")
    public String showMainPage_mainpage(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "mainpage_log_1";
    }
    @GetMapping("hospital_info/mainpage_log_1")
    public String showMainPage_mainpage_log_1(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        return "mainpage_log_1";
    }

}

