package com.issam.ridem.dto.workoutsession;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

// DTO for incoming workout session creation requests
@Data
public class CreateWorkoutSessionRequest {

    // Owner of the session
    @NotNull
    private Long userId;

    // Template followed in this session
    @NotNull
    private Long templateId;

    // Date the session was performed, defaults to today if not provided
    private LocalDate date;

    // Optional notes about the session, max 200 characters
    @Size(max=200)
    private String notes;
}