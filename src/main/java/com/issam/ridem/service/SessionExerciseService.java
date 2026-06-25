package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.SessionExerciseRepository;
import com.issam.ridem.repository.WorkoutSessionRepository;
import com.issam.ridem.repository.ExerciseRepository;
import com.issam.ridem.entity.SessionExercise;
import com.issam.ridem.entity.WorkoutSession;
import com.issam.ridem.entity.Exercise;
import com.issam.ridem.dto.sessionexercise.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for session-exercise assignments, acts as a bridge between the controller and the repository
@Service
public class SessionExerciseService {
    
    private final SessionExerciseRepository sessionExerciseRepository;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseRepository exerciseRepository;

    public SessionExerciseService(SessionExerciseRepository sessionExerciseRepository, WorkoutSessionRepository workoutSessionRepository, ExerciseRepository exerciseRepository){
        this.sessionExerciseRepository = sessionExerciseRepository;
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    // Retrieves all session-exercise assignments from the database and returns them as a list
    public List<SessionExerciseDTO> getAllSessionExercises(){
        return sessionExerciseRepository.findAll()
            .stream()
            .map(sessionExercise -> new SessionExerciseDTO(sessionExercise))
            .collect(Collectors.toList());
    }

    // Saves a new session-exercise assignment and returns it with auto-generated ID
    public SessionExerciseDTO createSessionExercise(CreateSessionExerciseRequest request){
        WorkoutSession workoutSession = workoutSessionRepository.findById(request.getSessionId())
            .orElseThrow(() -> new RuntimeException("Workout Session not found with id: " + request.getSessionId()));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + request.getExerciseId()));
        SessionExercise sessionExercise = new SessionExercise();
        sessionExercise.setWorkoutSession(workoutSession);
        sessionExercise.setExercise(exercise);
        sessionExercise.setSets(request.getSets());
        sessionExercise.setReps(request.getReps());
        sessionExercise.setWeight(request.getWeight());
        sessionExercise.setRest(request.getRest());
        SessionExercise saved = sessionExerciseRepository.save(sessionExercise);
        return new SessionExerciseDTO(saved);
    }

    // Retrieves a single session-exercise assignment by ID, throws RuntimeException if not found
    public SessionExerciseDTO getSessionExerciseById(Long id){
        Optional<SessionExercise> sessionExercise = sessionExerciseRepository.findById(id);
        if (sessionExercise.isPresent()) {
            return new SessionExerciseDTO(sessionExercise.get());
        } else {
            throw new RuntimeException("Session Exercise not found with id: " + id);
        }
    }

    // Deletes a single session-exercise assignment by ID, throws RuntimeException if not found
    public void deleteSessionExercise(Long id) {
        sessionExerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Session Exercise not found with id: " + id));
        sessionExerciseRepository.deleteById(id);
    }

    // Updates performed sets, reps, weight, rest and exercise of an existing assignment by ID
    public SessionExerciseDTO updateSessionExercise(Long id, UpdateSessionExerciseRequest request){
        SessionExercise existingSessionExercise = sessionExerciseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Session Exercise not found with id: " + id));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + request.getExerciseId()));
        existingSessionExercise.setExercise(exercise);
        existingSessionExercise.setSets(request.getSets());
        existingSessionExercise.setReps(request.getReps());
        existingSessionExercise.setWeight(request.getWeight());
        existingSessionExercise.setRest(request.getRest());
        SessionExercise saved = sessionExerciseRepository.save(existingSessionExercise);
        return new SessionExerciseDTO(saved);
    }
}