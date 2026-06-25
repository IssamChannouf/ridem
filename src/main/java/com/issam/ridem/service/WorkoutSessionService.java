package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.issam.ridem.repository.WorkoutSessionRepository;
import com.issam.ridem.repository.UserRepository;
import com.issam.ridem.repository.WorkoutTemplateRepository;
import com.issam.ridem.entity.WorkoutSession;
import com.issam.ridem.entity.User;
import com.issam.ridem.entity.WorkoutTemplate;
import com.issam.ridem.dto.workoutsession.*;

// Service layer for workout sessions, acts as a bridge between the controller and the repository
@Service
public class WorkoutSessionService {
    
    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserRepository userRepository;
    private final WorkoutTemplateRepository workoutTemplateRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, UserRepository userRepository, WorkoutTemplateRepository workoutTemplateRepository){
        this.workoutSessionRepository = workoutSessionRepository;
        this.userRepository = userRepository;
        this.workoutTemplateRepository = workoutTemplateRepository;
    }

    // Retrieves all workout sessions from the database and returns them as a list
    public List<WorkoutSessionDTO> getAllWorkoutSessions(){
        return workoutSessionRepository.findAll()
            .stream()
            .map(workoutSession -> new WorkoutSessionDTO(workoutSession))
            .collect(Collectors.toList());
    }

    // Saves a new workout session and returns it with auto-generated ID, defaults date to today if not provided
    public WorkoutSessionDTO createWorkoutSession(CreateWorkoutSessionRequest request){
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        WorkoutTemplate workoutTemplate = workoutTemplateRepository.findById(request.getTemplateId())
            .orElseThrow(() -> new RuntimeException("Workout Template not found with id: " + request.getTemplateId()));
        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setUser(user);
        workoutSession.setWorkoutTemplate(workoutTemplate);
        workoutSession.setDate(request.getDate() != null ? request.getDate() : LocalDate.now());
        workoutSession.setNotes(request.getNotes());
        WorkoutSession saved = workoutSessionRepository.save(workoutSession);
        return new WorkoutSessionDTO(saved);
    }

    // Retrieves a single workout session by ID, throws RuntimeException if not found
    public WorkoutSessionDTO getWorkoutSessionById(Long id){
        Optional<WorkoutSession> workoutSession = workoutSessionRepository.findById(id);
        if (workoutSession.isPresent()) {
            return new WorkoutSessionDTO(workoutSession.get());
        } else {
            throw new RuntimeException("Workout Session not found with id: " + id);
        }
    }

    // Deletes a single workout session by ID, throws RuntimeException if not found
    public void deleteWorkoutSession(Long id) {
        workoutSessionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout Session not found with id: " + id));
        workoutSessionRepository.deleteById(id);
    }

    // Updates date and notes of an existing workout session by ID
    public WorkoutSessionDTO updateWorkoutSession(Long id, UpdateWorkoutSessionRequest request){
        WorkoutSession existingWorkoutSession = workoutSessionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout Session not found with id: " + id));
        if (request.getDate() != null) {
            existingWorkoutSession.setDate(request.getDate());
        }
        if (request.getNotes() != null) {
            existingWorkoutSession.setNotes(request.getNotes());
        }
        WorkoutSession saved = workoutSessionRepository.save(existingWorkoutSession);
        return new WorkoutSessionDTO(saved);
    }
}