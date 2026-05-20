package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.ExerciseRepository;
import com.issam.ridem.entity.Exercise;
import java.util.List;
import java.util.Optional;

// Service layer for exercise-related business logic, acts as the bridge between the controller and the repository
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    //Retrieves all exercises from the database and returns them as a list
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    // Saves a new exercise to the database and returns the persisted exercise with auto-generated ID
    public Exercise createExercise(Exercise exercise){
        return exerciseRepository.save(exercise);
    }

    //Retrieves a single exercise by ID, throws Runtime Exception if no exercise is found with the given ID
    public Exercise getExerciseById(Long id){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            return exercise.get();
        } else {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
    }

    // Deletes a single exercise by ID, throws RuntimeException if not found
    public void deleteExercise(Long id) {
        getExerciseById(id);
        exerciseRepository.deleteById(id);
    }

    // Updates all fields of an existing exercise by ID except exerciseId, throws Runtime Exception if not found
    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        Exercise existingExercise = getExerciseById(id);
        existingExercise.setName(updatedExercise.getName());
        existingExercise.setMuscleGroup(updatedExercise.getMuscleGroup());
        existingExercise.setEquipment(updatedExercise.getEquipment());
        existingExercise.setDescription(updatedExercise.getDescription());
        return exerciseRepository.save(existingExercise);
    }
}
