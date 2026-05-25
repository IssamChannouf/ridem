package com.issam.ridem.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.issam.ridem.repository.WorkoutSessionRepository;
import com.issam.ridem.entity.WorkoutSession;

// Service layer for workout sessions, acts as a bridge between the controller and the repository
@Service
public class WorkoutSessionService {
    
    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository){
        this.workoutSessionRepository = workoutSessionRepository;
    }

    // Retrieves all workout session from the database and returns them as a list
    public List<WorkoutSession> getAllWorkoutSessions(){
        return workoutSessionRepository.findAll();
    }

    // Saves a new workout session and returns it with auto-generated ID
    public WorkoutSession createWorkoutSession(WorkoutSession workoutSession){
        if (workoutSession.getDate() == null) {
            workoutSession.setDate(LocalDate.now());
        }
        return workoutSessionRepository.save(workoutSession);
    }

    // Retrieves a single workout session by ID, throws RuntimeException if not found
    public WorkoutSession getWorkoutSessionById(Long id){
        Optional<WorkoutSession> workoutSession = workoutSessionRepository.findById(id);
        if (workoutSession.isPresent()) {
            return workoutSession.get();
        } else {
            throw new RuntimeException("Workout Session not found with id: " + id);
        }
    }

    // Deletes a single workout session by ID, throws RuntimeException if not found
    public void deleteWorkoutSession(Long id) {
        getWorkoutSessionById(id);
        workoutSessionRepository.deleteById(id);
    }

    // Updates date and/or notes of an existing workout session by ID, only provided fields are changed
    public WorkoutSession updateWorkoutSession(Long id, WorkoutSession updatedWorkoutSession) {
        WorkoutSession existingWorkoutSession = getWorkoutSessionById(id);
        if (updatedWorkoutSession.getDate() != null) {
            existingWorkoutSession.setDate(updatedWorkoutSession.getDate());
        }
        if (updatedWorkoutSession.getNotes() != null) {
            existingWorkoutSession.setNotes(updatedWorkoutSession.getNotes());
        }
        return workoutSessionRepository.save(existingWorkoutSession);
    }
    
}
