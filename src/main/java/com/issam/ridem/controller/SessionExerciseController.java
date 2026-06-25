package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.issam.ridem.service.SessionExerciseService;
import com.issam.ridem.dto.sessionexercise.*;

// Handles all HTTP requests related to session-exercise assignments, maps to /session-exercises base URL
@RestController
@RequestMapping("/session-exercises")
public class SessionExerciseController {

    private final SessionExerciseService sessionExerciseService;
    public SessionExerciseController(SessionExerciseService sessionExerciseService) {
        this.sessionExerciseService = sessionExerciseService;
    }

    // Handles GET /session-exercises, retrieves and returns all session-exercise assignments as JSON
    @GetMapping
    public List<SessionExerciseDTO> getAllSessionExercises() {
        return sessionExerciseService.getAllSessionExercises();
    }

    // Handles POST /session-exercises, creates a new session-exercise assignment, returns 400 if validation fails
    @PostMapping
    public SessionExerciseDTO createSessionExercise(@Valid @RequestBody CreateSessionExerciseRequest request) {
        return sessionExerciseService.createSessionExercise(request);
    }

    // Handles GET /session-exercises/{id}, returns a single session-exercise assignment by ID, 404 if not found
    @GetMapping("/{id}")
    public SessionExerciseDTO getSessionExerciseById(@PathVariable Long id) {
        return sessionExerciseService.getSessionExerciseById(id);
    }

    // Handles DELETE /session-exercises/{id}, returns 204 on success, 404 if not found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSessionExercise(@PathVariable Long id) {
        sessionExerciseService.deleteSessionExercise(id);
    }

    // Handles PUT /session-exercises/{id}, returns updated session-exercise assignment on success, 404 if not found
    @PutMapping("/{id}")
    public SessionExerciseDTO updateSessionExercise(@PathVariable Long id, @Valid @RequestBody UpdateSessionExerciseRequest request) {
        return sessionExerciseService.updateSessionExercise(id, request);
    }
}