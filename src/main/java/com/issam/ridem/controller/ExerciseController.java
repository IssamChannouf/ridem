package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.issam.ridem.entity.Exercise;
import com.issam.ridem.service.ExerciseService;
import jakarta.validation.Valid;



// Handles all HTTP requests related to exercises, maps to the /exercises base URL
@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    
    private final ExerciseService exerciseService;
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // Handles GET /exercises, retrieves and returns all exercises from the database as JSON
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    // Handles POST /exercises, creates a new exercise, returns 400 if validation fails
    @PostMapping
    public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    // Handles GET /exercises/{id}, returns a single exercise by ID, or 404 if not found
    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    // Handles DELETE /exercises/{id}, returns 204 on success, 404 if exercise does not exist
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    // Handles PUT /exercises/{id}, returns updated exercise on success, 404 if exercise does not exist
    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }
}
