package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.SessionExerciseRepository;
import com.issam.ridem.entity.SessionExercise;
import java.util.List;
import java.util.Optional;

// Service layer for session-exercise assignments, acts as a bridge between the controller and the repository
@Service
public class SessionExerciseService {
    
    private final SessionExerciseRepository sessionExerciseRepository;

    public SessionExerciseService(SessionExerciseRepository sessionExerciseRepository){
        this.sessionExerciseRepository = sessionExerciseRepository;
    }

    // Retrieves all session-exercise assignments from the database and returns them as a list
    public List<SessionExercise> getAllSessionExercises(){
        return sessionExerciseRepository.findAll();
    }

    // Saves a new session-exercise assignment and returns it with auto-generated ID
    public SessionExercise createSessionExercise(SessionExercise sessionExercise){
        return sessionExerciseRepository.save(sessionExercise);
    }

    // Retrieves a single session-exercise assignment by ID, throws RuntimeException if not found
    public SessionExercise getSessionExerciseById(Long id){
        Optional<SessionExercise> sessionExercise = sessionExerciseRepository.findById(id);
        if (sessionExercise.isPresent()) {
            return sessionExercise.get();
        } else {
            throw new RuntimeException("Session Exercise not found with id: " + id);
        }
    }

    // Deletes a single session-exercise assignment by ID, throws RuntimeException if not found
    public void deleteSessionExercise(Long id) {
        getSessionExerciseById(id);
        sessionExerciseRepository.deleteById(id);
    }

    // Updates performed sets, reps, weight and rest of an existing session-exercise assignment by ID
    public SessionExercise updateSessionExercise(Long id, SessionExercise updatedSessionExercise) {
        SessionExercise existingSessionExercise = getSessionExerciseById(id);
        existingSessionExercise.setSets(updatedSessionExercise.getSets());
        existingSessionExercise.setReps(updatedSessionExercise.getReps());
        existingSessionExercise.setWeight(updatedSessionExercise.getWeight());
        existingSessionExercise.setRest(updatedSessionExercise.getRest());
        return sessionExerciseRepository.save(existingSessionExercise);
    }
}
