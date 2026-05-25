package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.SessionExercise;

// Creates an interface repository for session exercises that inherits JpaRepository and its methods
public interface SessionExerciseRepository extends JpaRepository<SessionExercise, Long>{
    
}