package com.issam.ridem.dto.set;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming set update requests
@Data
public class UpdateSetRequest {

    // Performed number of repetitions
    @Min(1)
    private Integer reps;

    // Performed weight in kg
    @Min(0)
    private Double weight;
}