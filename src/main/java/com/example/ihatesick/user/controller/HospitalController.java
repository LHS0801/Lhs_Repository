package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.entity.HospitalEntity;
import com.example.ihatesick.user.data.repository.CustomerRepository;
import com.example.ihatesick.user.data.entity.CustomerEntity;
import com.example.ihatesick.user.service.HospitalService;
import com.example.ihatesick.user.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller

public class HospitalController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HospitalService hospitalService;




    // 메인 페이지 접근 시 병원 목록 불러오기
    @GetMapping("/mainpage")
    public String showMainPage(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        // 로그인 상태 확인
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        model.addAttribute("loggedInUser", loggedInUser);

        // 로그인 상태일 때만 로그아웃 버튼을 보여주기 위해
        if (loggedInUser != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "mainpage";  // mainpage.html로 병원 목록 전달
    }


    @GetMapping("/mainpage_log")
    public String showMainPage_log(Model model, HttpSession session) {
        List<HospitalEntity> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);

        // 로그인 상태 확인
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        model.addAttribute("loggedInUser", loggedInUser);

        // 로그인 상태일 때만 로그아웃 버튼을 보여주기 위해
        if (loggedInUser != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "mainpage_log_1";  // mainpage.html로 병원 목록 전달
    }




    @GetMapping("/hospital_info/{code}")
    public String hospital_info(@PathVariable("code") Long code, Model model) {
        // 병원 정보를 DB에서 조회 (서비스 메서드 호출)
        HospitalEntity hospital = hospitalService.getHospital(code);

        if (hospital != null) {
            model.addAttribute("hospital", hospital);
        } else {
            model.addAttribute("error", "병원 정보를 찾을 수 없습니다.");
        }
        return "hospital_info";
    }

}

