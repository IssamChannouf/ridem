package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.Exercise;

//Creates an interface repository for exercises that inherits JpaRepository and its methods
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
}
