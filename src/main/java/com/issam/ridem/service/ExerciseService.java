package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.ExerciseRepository;
import com.issam.ridem.entity.Exercise;
import com.issam.ridem.dto.exercise.CreateExerciseRequest;
import com.issam.ridem.dto.exercise.UpdateExerciseRequest;
import com.issam.ridem.dto.exercise.ExerciseDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for exercise-related business logic, acts as the bridge between the controller and the repository
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    // Retrieves all exercises from the database and returns them as a list
    public List<ExerciseDTO> getAllExercises(){
        return exerciseRepository.findAll()
            .stream()
            .map(exercise -> new ExerciseDTO(exercise))
            .collect(Collectors.toList());
    }

    // Saves a new exercise to the database and returns the persisted exercise with auto-generated ID
    public ExerciseDTO createExercise(CreateExerciseRequest request){
        // Builds an Exercise entity from incoming request
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setMuscleGroup(request.getMuscleGroup());
        exercise.setEquipment(request.getEquipment());
        exercise.setDescription(request.getDescription());
        Exercise savedExercise = exerciseRepository.save(exercise);
        return new ExerciseDTO(savedExercise);
    }

    // Retrieves a single exercise by ID, throws RuntimeException if no exercise is found with the given ID
    public ExerciseDTO getExerciseById(Long id){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            return new ExerciseDTO(exercise.get());
        } else {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
    }

    // Deletes a single exercise by ID, throws RuntimeException if not found
    public void deleteExercise(Long id) {
        exerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercise not found by id: " + id));
        exerciseRepository.deleteById(id);
    }

    // Updates all fields of an existing exercise by ID except exerciseId, throws RuntimeException if not found
    public ExerciseDTO updateExercise(Long id, UpdateExerciseRequest request) {
        Exercise existingExercise = exerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercise not found by id: " + id));
        existingExercise.setName(request.getName());
        existingExercise.setMuscleGroup(request.getMuscleGroup());
        existingExercise.setEquipment(request.getEquipment());
        existingExercise.setDescription(request.getDescription());
        Exercise savedExercise = exerciseRepository.save(existingExercise);
        return new ExerciseDTO(savedExercise);
    }
}
