package com.issam.ridem.dto.sessionexercise;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming session exercise creation requests
@Data
public class CreateSessionExerciseRequest {

    // Workout session this exercise belongs to
    @NotNull
    private Long sessionId;

    // Exercise being recorded in the session
    @NotNull
    private Long exerciseId;

    // Performed number of sets for the exercise
    @Min(1)
    private Integer sets;

    // Performed number of repetitions per set
    @Min(1)
    private Integer reps;

    // Performed weight for the exercise in kg
    @Min(1)
    private Double weight;

    // Performed rest time between sets in seconds
    @Min(0)
    private Integer rest;
}