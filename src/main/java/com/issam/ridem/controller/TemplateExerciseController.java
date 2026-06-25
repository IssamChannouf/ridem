package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.issam.ridem.service.TemplateExerciseService;
import com.issam.ridem.dto.templateexercise.*;

// Handles all HTTP requests related to exercise-template assignments, maps to /template-exercises base URL
@RestController
@RequestMapping("/template-exercises")
public class TemplateExerciseController {

    private final TemplateExerciseService templateExerciseService;
    public TemplateExerciseController(TemplateExerciseService templateExerciseService) {
        this.templateExerciseService = templateExerciseService;
    }

    // Handles GET /template-exercises, retrieves and returns all exercise-template assignments as JSON
    @GetMapping
    public List<TemplateExerciseDTO> getAllTemplateExercises() {
        return templateExerciseService.getAllTemplateExercises();
    }

    // Handles POST /template-exercises, creates a new exercise-template assignment, returns 400 if validation fails
    @PostMapping
    public TemplateExerciseDTO createTemplateExercise(@Valid @RequestBody CreateTemplateExerciseRequest request) {
        return templateExerciseService.createTemplateExercise(request);
    }

    // Handles GET /template-exercises/{id}, returns a single exercise-template assignment by ID, 404 if not found
    @GetMapping("/{id}")
    public TemplateExerciseDTO getTemplateExerciseById(@PathVariable Long id) {
        return templateExerciseService.getTemplateExerciseById(id);
    }

    // Handles DELETE /template-exercises/{id}, returns 204 on success, 404 if not found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplateExercise(@PathVariable Long id) {
        templateExerciseService.deleteTemplateExercise(id);
    }

    // Handles PUT /template-exercises/{id}, returns updated exercise-template assignment on success, 404 if not found
    @PutMapping("/{id}")
    public TemplateExerciseDTO updateTemplateExercise(@PathVariable Long id, @Valid @RequestBody UpdateTemplateExerciseRequest request) {
        return templateExerciseService.updateTemplateExercise(id, request);
    }
}