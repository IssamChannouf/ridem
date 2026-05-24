package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.TemplateExerciseRepository;
import com.issam.ridem.entity.TemplateExercise;
import java.util.List;
import java.util.Optional;

// Service layer for exercise-template assignments, acts as a bridge between the controller and the repository
@Service
public class TemplateExerciseService {
    
    private final TemplateExerciseRepository templateExerciseRepository;

    public TemplateExerciseService(TemplateExerciseRepository templateExerciseRepository){
        this.templateExerciseRepository = templateExerciseRepository;
    }

    // Retrieves all exercise-template assignments from the database and returns them as a list
    public List<TemplateExercise> getAllTemplateExercises(){
        return templateExerciseRepository.findAll();
    }

    // Saves a new exercise-template assignment and returns it with auto-generated ID
    public TemplateExercise createTemplateExercise(TemplateExercise templateExercise){
        return templateExerciseRepository.save(templateExercise);
    }

    // Retrieves a single exercise-template assignment by ID, throws RuntimeException if not found
    public TemplateExercise getTemplateExerciseById(Long id){
        Optional<TemplateExercise> templateExercise = templateExerciseRepository.findById(id);
        if (templateExercise.isPresent()) {
            return templateExercise.get();
        } else {
            throw new RuntimeException("Template Exercise not found with id: " + id);
        }
    }

    // Deletes a single exercise-template assignment by ID, throws RuntimeException if not found
    public void deleteTemplateExercise(Long id) {
        getTemplateExerciseById(id);
        templateExerciseRepository.deleteById(id);
    }

    // Updates planned sets, reps, weight and rest of an existing exercise-template assignment by ID
    public TemplateExercise updateTemplateExercise(Long id, TemplateExercise updatedTemplateExercise) {
        TemplateExercise existingTemplateExercise = getTemplateExerciseById(id);
        existingTemplateExercise.setSets(updatedTemplateExercise.getSets());
        existingTemplateExercise.setReps(updatedTemplateExercise.getReps());
        existingTemplateExercise.setWeight(updatedTemplateExercise.getWeight());
        existingTemplateExercise.setRest(updatedTemplateExercise.getRest());
        return templateExerciseRepository.save(existingTemplateExercise);
    }
}
