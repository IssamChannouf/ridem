package com.issam.ridem.dto.set;

import com.issam.ridem.entity.Set;
import lombok.Data;

// DTO for Set entity, exposes all fields in API responses
@Data
public class SetDTO {

    private Long setId;

    private Long sessionExerciseId;

    private Integer reps;

    private Double weight;

    public SetDTO(Set set) {
        this.setId = set.getSetId();
        this.sessionExerciseId = set.getSessionExercise().getSessionExerciseId();
        this.reps = set.getReps();
        this.weight = set.getWeight();
    }
}