package com.issam.ridem.dto.templateexercise;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming template exercise update requests
@Data
public class UpdateTemplateExerciseRequest {

    // Exercise being updated in the template
    @NotNull
    private Long exerciseId;

    // Planned number of sets for the exercise
    @Min(1)
    private Integer sets;

    // Planned number of repetitions per set
    @Min(1)
    private Integer reps;

    // Planned weight for the exercise in kg
    @Min(1)
    private Double weight;

    // Planned rest time between sets in seconds
    @Min(0)
    private Integer rest;
}