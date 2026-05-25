package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.WorkoutSession;

// Creates an interface repository for workout sessions that inherits JpaRepository and its methods
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}
