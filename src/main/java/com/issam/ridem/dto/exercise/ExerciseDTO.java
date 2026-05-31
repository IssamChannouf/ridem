package com.issam.ridem.dto.exercise;

import com.issam.ridem.entity.Exercise;
import lombok.Data;

// DTO for Exercise entity, exposes all field in API responses
@Data
public class ExerciseDTO {
    
    private Long exerciseId;

    private String name;

    private String muscleGroup;

    private String equipment;

    private String description;

    public ExerciseDTO(Exercise exercise) {
        this.exerciseId = exercise.getExerciseId();
        this.name = exercise.getName();
        this.muscleGroup = exercise.getMuscleGroup();
        this.equipment = exercise.getEquipment();
        this.description = exercise.getDescription();
    }
}
