package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.SetRepository;
import com.issam.ridem.repository.SessionExerciseRepository;
import com.issam.ridem.entity.Set;
import com.issam.ridem.entity.SessionExercise;
import com.issam.ridem.dto.set.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for sets, acts as a bridge between the controller and the repository
@Service
public class SetService {
    
    private final SetRepository setRepository;
    private final SessionExerciseRepository sessionExerciseRepository;

    public SetService(SetRepository setRepository, SessionExerciseRepository sessionExerciseRepository){
        this.setRepository = setRepository;
        this.sessionExerciseRepository = sessionExerciseRepository;
    }

    // Retrieves all sets from the database and returns them as a list
    public List<SetDTO> getAllSets(){
        return setRepository.findAll()
            .stream()
            .map(set -> new SetDTO(set))
            .collect(Collectors.toList());
    }

    // Saves a new set and returns it with auto-generated ID
    public SetDTO createSet(CreateSetRequest request){
        SessionExercise sessionExercise = sessionExerciseRepository.findById(request.getSessionExerciseId())
            .orElseThrow(() -> new RuntimeException("Session Exercise not found with id: " + request.getSessionExerciseId()));
        Set set = new Set();
        set.setSessionExercise(sessionExercise);
        set.setReps(request.getReps());
        set.setWeight(request.getWeight());
        Set saved = setRepository.save(set);
        return new SetDTO(saved);
    }

    // Retrieves a single set by ID, throws RuntimeException if not found
    public SetDTO getSetById(Long id){
        Optional<Set> set = setRepository.findById(id);
        if (set.isPresent()) {
            return new SetDTO(set.get());
        } else {
            throw new RuntimeException("Set not found with id: " + id);
        }
    }

    // Deletes a single set by ID, throws RuntimeException if not found
    public void deleteSet(Long id){
        setRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Set not found with id: " + id));
        setRepository.deleteById(id);
    }

    // Updates performed reps and weight of an existing set by ID
    public SetDTO updateSet(Long id, UpdateSetRequest request){
        Set existingSet = setRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Set not found with id: " + id));
        existingSet.setReps(request.getReps());
        existingSet.setWeight(request.getWeight());
        Set saved = setRepository.save(existingSet);
        return new SetDTO(saved);
    }
}