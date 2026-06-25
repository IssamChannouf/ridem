package com.issam.ridem.dto.workoutsession;

import com.issam.ridem.entity.WorkoutSession;
import lombok.Data;
import java.time.LocalDate;

// DTO for WorkoutSession entity, exposes all fields in API responses
@Data
public class WorkoutSessionDTO {

    private Long sessionId;

    private Long userId;

    private Long templateId;

    private LocalDate date;

    private String notes;

    public WorkoutSessionDTO(WorkoutSession workoutSession) {
        this.sessionId = workoutSession.getSessionId();
        this.userId = workoutSession.getUser().getUserId();
        this.templateId = workoutSession.getWorkoutTemplate().getTemplateId();
        this.date = workoutSession.getDate();
        this.notes = workoutSession.getNotes();
    }
}