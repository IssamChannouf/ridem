package com.issam.ridem.dto.sessionexercise;

import com.issam.ridem.entity.SessionExercise;
import lombok.Data;

// DTO for SessionExercise entity, exposes all fields in API responses
@Data
public class SessionExerciseDTO {

    private Long sessionExerciseId;

    private Long sessionId;

    private Long exerciseId;

    private Integer sets;

    private Integer reps;

    private Double weight;

    private Integer rest;

    public SessionExerciseDTO(SessionExercise sessionExercise) {
        this.sessionExerciseId = sessionExercise.getSessionExerciseId();
        this.sessionId = sessionExercise.getWorkoutSession().getSessionId();
        this.exerciseId = sessionExercise.getExercise().getExerciseId();
        this.sets = sessionExercise.getSets();
        this.reps = sessionExercise.getReps();
        this.weight = sessionExercise.getWeight();
        this.rest = sessionExercise.getRest();
    }
}