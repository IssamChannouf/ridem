package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.WorkoutTemplateRepository;
import com.issam.ridem.entity.WorkoutTemplate;
import java.util.List;
import java.util.Optional;

// Service layer for workoutTemplate-related business logic, acts as a bridge between the controller and the repository
@Service
public class WorkoutTemplateService {
    
    private final WorkoutTemplateRepository workoutTemplateRepository;
    
    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository){
        this.workoutTemplateRepository = workoutTemplateRepository;
    }

    // Retrieves all templates from the database and returns them as a list
    public List<WorkoutTemplate> getAllWorkoutTemplates(){
        return workoutTemplateRepository.findAll();
    }

    // Saves a new template to the database and returns persisted template with auto-generated ID
    public WorkoutTemplate createWorkoutTemplate(WorkoutTemplate workoutTemplate){
        return workoutTemplateRepository.save(workoutTemplate);
    }

    // Retrieves a single template by ID, throws RuntimeException if no existing template is found with the given ID
    public WorkoutTemplate getWorkoutTemplateById(Long id){
        Optional<WorkoutTemplate> workoutTemplate = workoutTemplateRepository.findById(id);
        if (workoutTemplate.isPresent()) {
            return workoutTemplate.get();
        } else {
            throw new RuntimeException("Workout Template not found with id: " + id);
        }
    }

    // Deletes a single template by ID, throws RuntimeException if not found
    public void deleteWorkoutTemplate(Long id) {
        getWorkoutTemplateById(id);
        workoutTemplateRepository.deleteById(id);
    }

    // Updates the name of an existing template by ID
    public WorkoutTemplate updateWorkoutTemplate(Long id, WorkoutTemplate updatedWorkoutTemplate){
        WorkoutTemplate existingTemplate = getWorkoutTemplateById(id);
        existingTemplate.setName(updatedWorkoutTemplate.getName());
        return workoutTemplateRepository.save(existingTemplate);
    }
}
