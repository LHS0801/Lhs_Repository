package com.example.ihatesick.user.controller;

import com.example.ihatesick.user.data.entity.QuestionnaireEntity;
import com.example.ihatesick.user.data.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller

public class QuestionnaireController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;


    @GetMapping("/hospital_reserve")
    public String hospital_reserve(Model model) {
        model.addAttribute("questionnaire", new QuestionnaireEntity());
        return "hospital_reserve";
    }

    @PostMapping("/hospital_reserve")
    public String submitForm(@RequestParam("questionnaireDoctor") String questionnaireDoctor,
                             @RequestParam("questionnaireForm") String questionnaireForm,
                             Model model) {

        QuestionnaireEntity questionnaire = new QuestionnaireEntity();

        questionnaire.setQuestionnaireDoctor(questionnaireDoctor);
        questionnaire.setQuestionnaireForm(questionnaireForm);

        questionnaireRepository.save(questionnaire);

        model.addAttribute("message", "예약이 완료되었습니다!");
        return "reserve_success";
    }







}

