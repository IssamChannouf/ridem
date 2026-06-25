package com.issam.ridem.dto.workouttemplate;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming workoutTemplate update requests
@Data
public class UpdateWorkoutTemplateRequest {

    // Name of the workoutTemplate, must start with uppercase, between 3 and 30 characters
    @NotBlank
    @Size(min=3, max=30)
    @Pattern(regexp="^[A-Z].*")
    private String name;

}