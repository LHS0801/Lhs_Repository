package com.example.ihatesick.user.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "questionnaire")
public class QuestionnaireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Questionnaire_id;

    @Column(name = "questionnaire_doctor", nullable = false)
    private String Questionnaire_doctor;

    @Column(name = "questionnaire_form", nullable = false)
    private String Questionnaire_form;

    public String getQuestionnaireDoctor() {
        return Questionnaire_doctor;
    }

    public void setQuestionnaireDoctor(String doctor) {
        this.Questionnaire_doctor = doctor;
    }

    public String getQuestionnaireForm() {
        return Questionnaire_form;
    }

    public void setQuestionnaireForm(String form) {
        this.Questionnaire_form = form;
    }

}