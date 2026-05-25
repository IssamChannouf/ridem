package com.issam.ridem.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.issam.ridem.entity.WorkoutSession;
import com.issam.ridem.service.WorkoutSessionService;

// Handles all HTTP requests related to workout sessions, maps to /workout-sessions base URL
@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;
    public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
    }

    // Handles GET /workout-sessions, retrieves and returns all workout sessions as JSON
    @GetMapping
    public List<WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionService.getAllWorkoutSessions();
    }

    // Handles POST /workout-sessions, creates a new workout session, returns 400 if validation fails
    @PostMapping
    public WorkoutSession createWorkoutSession(@Valid @RequestBody WorkoutSession workoutSession) {
        return workoutSessionService.createWorkoutSession(workoutSession);
    }

    // Handles GET /workout-sessions/{id}, returns a single workout session by ID, 404 if not found
    @GetMapping("/{id}")
    public WorkoutSession getWorkoutSessionById(@PathVariable Long id) {
        return workoutSessionService.getWorkoutSessionById(id);
    }

    // Handles DELETE /workout-sessions/{id}, returns 204 on success, 404 if not found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutSession(@PathVariable Long id) {
        workoutSessionService.deleteWorkoutSession(id);
    }

    // Handles PUT /workout-sessions/{id}, returns updated workout session on success, 404 if not found
    @PutMapping("/{id}")
    public WorkoutSession updateWorkoutSession(@PathVariable Long id, @Valid @RequestBody WorkoutSession workoutSession) {
        return workoutSessionService.updateWorkoutSession(id, workoutSession);
    }
}
