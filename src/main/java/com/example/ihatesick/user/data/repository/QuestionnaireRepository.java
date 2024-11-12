package com.example.ihatesick.user.data.repository;

import com.example.ihatesick.user.data.entity.QuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository  extends JpaRepository<QuestionnaireEntity, String> {
}
