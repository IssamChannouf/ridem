package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.issam.ridem.entity.Set;

// Creates an interface repository for sets that inherits JpaRepository and its methods
public interface SetRepository extends JpaRepository<Set, Long> {
    @Query("SELECT MAX(s.weight) FROM Set s WHERE s.sessionExercise.exercise.exerciseId = :exerciseId AND s.sessionExercise.workoutSession.user.userId = :userId")
    Double findPersonalBest(@Param("exerciseId") Long exerciseId, @Param("userId") Long userId);
}
