package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.WorkoutTemplate;

// Creates an interface repository for workout templates that inherits JpaRepository
public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplate, Long> {
    
}