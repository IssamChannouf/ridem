package com.issam.ridem.dto.workouttemplate;

import com.issam.ridem.entity.WorkoutTemplate;
import lombok.Data;


// DTO for WorkoutTemplate entity, exposes only safe fields in API response
@Data
public class WorkoutTemplateDTO {

    private Long templateId;
    
    private String name;

    private Long userId;

    public WorkoutTemplateDTO(WorkoutTemplate workoutTemplate) {
        this.templateId = workoutTemplate.getTemplateId();
        this.name = workoutTemplate.getName();
        this.userId = workoutTemplate.getUser().getUserId();
    }
}