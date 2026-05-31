package com.issam.ridem.dto.exercise;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming exercise creation requests
@Data
public class CreateExerciseRequest {

        // Name of the exercise, must start with uppercase, between 5 and 80 characters
        @NotBlank
        @Size(min=5, max=80)
        @Pattern(regexp="^[A-Z].*")
        private String name;

        // Muscle group targeted with the exercise, must start with uppercase, between 3 and 10 characters
        @NotBlank
        @Size(min=3, max=10)
        @Pattern(regexp="^[A-Z].*")
        private String muscleGroup;
        
        // Optional equipment used in the exercise
        private String equipment;

        // Optional description of the exercise
        private String description;
}
