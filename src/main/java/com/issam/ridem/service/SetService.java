package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.SetRepository;
import com.issam.ridem.entity.Set;
import java.util.List;
import java.util.Optional;

// Service layer for sets, acts as a bridge between the controller and the repository 
@Service
public class SetService {
    
    private final SetRepository setRepository;

    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    // Retrieves all sets from the database and returns them as a list
    public List<Set> getAllSets() {
        return setRepository.findAll();
    }

    // Saves a new set and returns it with auto-generated ID
    public Set createSet(Set set) {
        return setRepository.save(set);
    }

    // Retrieves a single set by ID, throws RuntimeException if not found
    public Set getSetById(Long id) {
        Optional<Set> set = setRepository.findById(id);
        if (set.isPresent()) {
            return set.get();
        } else {
            throw new RuntimeException("Set not found with id: " + id);
        }
    }

    // Deletes a single set by ID, throws RuntimeException if not found
    public void deleteSet(Long id) {
        getSetById(id);
        setRepository.deleteById(id);
    }

    // Updates performed reps and weight of an existing set by ID
    public Set updateSet(Long id, Set updatedSet) {
        Set existingSet = getSetById(id);
        existingSet.setReps(updatedSet.getReps());
        existingSet.setWeight(updatedSet.getWeight());
        return setRepository.save(existingSet);
    }
}
