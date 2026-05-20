package com.issam.ridem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    // Handles GET /exercises — retrieves and returns all exercises from the database as JSON
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    // Handles POST /exercises — accepts JSON exercise data, @Valid triggers validation against Exercises constraints before saving, returns 400 if invalid or the created exercise with auto-generated ID if successful
    @PostMapping
    public Exercise createExercise(@Valid @RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    // Handles GET /exercises/{id} - returns a single exercise by ID, or 404 if not found
    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    // Handles DELETE /exercises/{id} - returns 204 on success, 404 if exercise does not exist
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    // Handles PUT /exercises/{id} - returns updated exercise on success, 404 if exercise does not exist
    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }
}
