package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.TemplateExercise;

// Creates an interface repository for template exercises that inherits JpaRepository and its methods
public interface TemplateExerciseRepository extends JpaRepository<TemplateExercise, Long> {    
}
