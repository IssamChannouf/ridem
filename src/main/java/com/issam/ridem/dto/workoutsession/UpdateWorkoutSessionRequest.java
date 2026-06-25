package com.issam.ridem.dto.workoutsession;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

// DTO for incoming workout session update requests
@Data
public class UpdateWorkoutSessionRequest {

    // Date the session was performed
    private LocalDate date;

    // Optional notes about the session, max 200 characters
    @Size(max=200)
    private String notes;
}