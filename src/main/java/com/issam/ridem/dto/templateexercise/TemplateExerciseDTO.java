package com.issam.ridem.dto.templateexercise;

import com.issam.ridem.entity.TemplateExercise;
import lombok.Data;

// DTO for TemplateExercise entity, exposes all fields in API responses
@Data
public class TemplateExerciseDTO {

    private Long templateExerciseId;

    private Long templateId;

    private Long exerciseId;

    private Integer sets;

    private Integer reps;

    private Double weight;

    private Integer rest;

    public TemplateExerciseDTO(TemplateExercise templateExercise) {
        this.templateExerciseId = templateExercise.getTemplateExerciseId();
        this.templateId = templateExercise.getWorkoutTemplate().getTemplateId();
        this.exerciseId = templateExercise.getExercise().getExerciseId();
        this.sets = templateExercise.getSets();
        this.reps = templateExercise.getReps();
        this.weight = templateExercise.getWeight();
        this.rest = templateExercise.getRest();
    }
}