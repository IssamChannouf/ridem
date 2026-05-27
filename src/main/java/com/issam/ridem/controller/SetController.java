package com.issam.ridem.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.issam.ridem.entity.Set;
import com.issam.ridem.service.SetService;

// Handles all HTTP requests related to sets, maps to /sets base URL
@RestController
@RequestMapping("/sets")
public class SetController {
    
    private final SetService setService;
    public SetController(SetService setService) {
        this.setService = setService;
    }

    // Handles GET /sets, retrieves and returns all sets as JSON
    @GetMapping
    public List<Set> getAllSets() {
        return setService.getAllSets();
    }

    // Handles POST /sets, creates a new set, returns 400 if validation fails
    @PostMapping
    public Set createSet(@Valid @RequestBody Set set) {
        return setService.createSet(set);
    }

    // Handles GET /sets/{id}, returns a single set by ID, 404 if not found
    @GetMapping("/{id}")
    public Set getSetById(@PathVariable Long id) {
        return setService.getSetById(id);
    }

    // Handles DELETE /sets/{id}, returns 204 on success, 404 if not found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSet(@PathVariable Long id) {
        setService.deleteSet(id);
    }

    // Handles PUT /sets/{id}, returns updated version of a set on success, 404 if not found
    @PutMapping("/{id}")
    public Set updateSet(@PathVariable Long id, @Valid @RequestBody Set set) {
        return setService.updateSet(id, set);
    }
}
