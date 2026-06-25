package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.issam.ridem.service.WorkoutTemplateService;
import jakarta.validation.Valid;
import com.issam.ridem.dto.workouttemplate.*;


// Handles all HTTP requests related to workout templates, maps to /workout-templates base URL
@RestController
@RequestMapping("/workout-templates")
public class WorkoutTemplateController {
    
    private final WorkoutTemplateService workoutTemplateService;
    public WorkoutTemplateController(WorkoutTemplateService workoutTemplateService) {
        this.workoutTemplateService = workoutTemplateService;
    }

    // Handles GET /workout-templates, retrieves and returns all workout templates as JSON
    @GetMapping
    public List<WorkoutTemplateDTO> getAllWorkoutTemplates() {
        return workoutTemplateService.getAllWorkoutTemplates();
    }
    
    // Handles POST /workout-templates, creates a new template, returns 400 if validation fails    
    @PostMapping
    public WorkoutTemplateDTO createWorkoutTemplate(@Valid @RequestBody CreateWorkoutTemplateRequest request) {
        return workoutTemplateService.createWorkoutTemplate(request);
    }
    
    // Handles GET /workout-templates/{id}, returns a single template by ID, 404 if not found
    @GetMapping("/{id}")
    public WorkoutTemplateDTO getWorkoutTemplateById(@PathVariable Long id) {
        return workoutTemplateService.getWorkoutTemplateById(id);
    }
    
    // Handles DELETE /workout-templates/{id}, returns 204 on success, 404 if not found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutTemplate(@PathVariable Long id){
        workoutTemplateService.deleteWorkoutTemplate(id);
    }

    // Handles PUT /workout-templates/{id}, returns updated template on success, 404 if not found
    @PutMapping("/{id}")
    public WorkoutTemplateDTO updateWorkoutTemplate(@PathVariable Long id, @Valid @RequestBody UpdateWorkoutTemplateRequest request) {
        return workoutTemplateService.updateWorkoutTemplate(id, request);
    }
}
