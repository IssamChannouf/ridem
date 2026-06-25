package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.WorkoutTemplateRepository;
import com.issam.ridem.repository.UserRepository;
import com.issam.ridem.entity.WorkoutTemplate;
import com.issam.ridem.entity.User;
import com.issam.ridem.dto.workouttemplate.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for workoutTemplate-related business logic, acts as a bridge between the controller and the repository
@Service
public class WorkoutTemplateService {
    
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final UserRepository userRepository;
    
    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository, UserRepository userRepository){
        this.workoutTemplateRepository = workoutTemplateRepository;
        this.userRepository = userRepository;
    }

    // Retrieves all templates from the database and returns them as a list
    public List<WorkoutTemplateDTO> getAllWorkoutTemplates(){
        return workoutTemplateRepository.findAll()
            .stream()
            .map(workoutTemplate -> new WorkoutTemplateDTO(workoutTemplate))
            .collect(Collectors.toList());
    }

    // Saves a new template to the database and returns persisted template with auto-generated ID
    public WorkoutTemplateDTO createWorkoutTemplate(CreateWorkoutTemplateRequest request){
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        WorkoutTemplate workoutTemplate = new WorkoutTemplate();
        workoutTemplate.setName(request.getName());
        workoutTemplate.setUser(user);
        WorkoutTemplate savedWorkoutTemplate = workoutTemplateRepository.save(workoutTemplate);
        return new WorkoutTemplateDTO(savedWorkoutTemplate);
    }

    // Retrieves a single template by ID, throws RuntimeException if no existing template is found with the given ID
    public WorkoutTemplateDTO getWorkoutTemplateById(Long id){
        Optional<WorkoutTemplate> workoutTemplate = workoutTemplateRepository.findById(id);
        if (workoutTemplate.isPresent()) {
            return new WorkoutTemplateDTO(workoutTemplate.get());
        } else {
            throw new RuntimeException("Workout Template not found with id: " + id);
        }
    }

    // Deletes a single template by ID, throws RuntimeException if not found
    public void deleteWorkoutTemplate(Long id) {
        workoutTemplateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout Template not found with id: " + id));
        workoutTemplateRepository.deleteById(id);
    }

    // Updates the name of an existing template by ID
    public WorkoutTemplateDTO updateWorkoutTemplate(Long id, UpdateWorkoutTemplateRequest request){
        WorkoutTemplate existingTemplate = workoutTemplateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout Template not found with id: " + id));
        existingTemplate.setName(request.getName());
        WorkoutTemplate savedWorkoutTemplate = workoutTemplateRepository.save(existingTemplate);
        return new WorkoutTemplateDTO(savedWorkoutTemplate);
    }
}