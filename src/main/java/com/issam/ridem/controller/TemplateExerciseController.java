package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.issam.ridem.entity.TemplateExercise;
import com.issam.ridem.service.TemplateExerciseService;

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
    public List<TemplateExercise> getAllTemplateExercises() {
        return templateExerciseService.getAllTemplateExercises();
    }

    // Handles POST /template-exercises, creates a new exercise-template assignment, returns 400 if validation fails
    @PostMapping
    public TemplateExercise createTemplateExercise(@Valid @RequestBody TemplateExercise templateExercise) {
        return templateExerciseService.createTemplateExercise(templateExercise);
    }

    // Handles GET /template-exercises/{id}, returns a single exercise-template assignment by ID, 404 if not found
    @GetMapping("/{id}")
    public TemplateExercise getTemplateExerciseById(@PathVariable Long id) {
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
    public TemplateExercise updateTemplateExercise(@PathVariable Long id, @Valid @RequestBody TemplateExercise templateExercise) {
        return templateExerciseService.updateTemplateExercise(id, templateExercise);
    }
}
