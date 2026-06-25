package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.TemplateExerciseRepository;
import com.issam.ridem.repository.WorkoutTemplateRepository;
import com.issam.ridem.repository.ExerciseRepository;
import com.issam.ridem.entity.TemplateExercise;
import com.issam.ridem.entity.WorkoutTemplate;
import com.issam.ridem.entity.Exercise;
import com.issam.ridem.dto.templateexercise.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for exercise-template assignments, acts as a bridge between the controller and the repository
@Service
public class TemplateExerciseService {
    
    private final TemplateExerciseRepository templateExerciseRepository;
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final ExerciseRepository exerciseRepository;

    public TemplateExerciseService(TemplateExerciseRepository templateExerciseRepository, WorkoutTemplateRepository workoutTemplateRepository, ExerciseRepository exerciseRepository){
        this.templateExerciseRepository = templateExerciseRepository;
        this.workoutTemplateRepository = workoutTemplateRepository;
        this.exerciseRepository = exerciseRepository;
    }

    // Retrieves all exercise-template assignments from the database and returns them as a list
    public List<TemplateExerciseDTO> getAllTemplateExercises(){
        return templateExerciseRepository.findAll()
            .stream()
            .map(templateExercise -> new TemplateExerciseDTO(templateExercise))
            .collect(Collectors.toList());
    }

    // Saves a new exercise-template assignment and returns it with auto-generated ID
    public TemplateExerciseDTO createTemplateExercise(CreateTemplateExerciseRequest request){
        WorkoutTemplate workoutTemplate = workoutTemplateRepository.findById(request.getTemplateId())
            .orElseThrow(() -> new RuntimeException("Workout Template not found with id: " + request.getTemplateId()));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + request.getExerciseId()));
        TemplateExercise templateExercise = new TemplateExercise();
        templateExercise.setWorkoutTemplate(workoutTemplate);
        templateExercise.setExercise(exercise);
        templateExercise.setSets(request.getSets());
        templateExercise.setReps(request.getReps());
        templateExercise.setWeight(request.getWeight());
        templateExercise.setRest(request.getRest());
        TemplateExercise saved = templateExerciseRepository.save(templateExercise);
        return new TemplateExerciseDTO(saved);
    }

    // Retrieves a single exercise-template assignment by ID, throws RuntimeException if not found
    public TemplateExerciseDTO getTemplateExerciseById(Long id){
        Optional<TemplateExercise> templateExercise = templateExerciseRepository.findById(id);
        if (templateExercise.isPresent()) {
            return new TemplateExerciseDTO(templateExercise.get());
        } else {
            throw new RuntimeException("Template Exercise not found with id: " + id);
        }
    }

    // Deletes a single exercise-template assignment by ID, throws RuntimeException if not found
    public void deleteTemplateExercise(Long id) {
        templateExerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Template Exercise not found with id: " + id));
        templateExerciseRepository.deleteById(id);
    }

    // Updates planned sets, reps, weight, rest and exercise of an existing assignment by ID
    public TemplateExerciseDTO updateTemplateExercise(Long id, UpdateTemplateExerciseRequest request){
        TemplateExercise existingTemplateExercise = templateExerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Template Exercise not found with id: " + id));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + request.getExerciseId()));
        existingTemplateExercise.setExercise(exercise);
        existingTemplateExercise.setSets(request.getSets());
        existingTemplateExercise.setReps(request.getReps());
        existingTemplateExercise.setWeight(request.getWeight());
        existingTemplateExercise.setRest(request.getRest());
        TemplateExercise saved = templateExerciseRepository.save(existingTemplateExercise);
        return new TemplateExerciseDTO(saved);
    }
}