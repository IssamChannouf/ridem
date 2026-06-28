package com.issam.ridem.controller;

import org.springframework.web.bind.annotation.*;

import com.issam.ridem.service.SetService;

// Handles personal best queries across users and exercises
@RestController
public class PersonalBestController {
    
    private final SetService setService;
    public PersonalBestController(SetService setService) {
        this.setService = setService;
    }

    // Handles GET/users/{userId}/exercises/{exerciseId}/personal-best, returns max weight for an exercise 
    @GetMapping("/users/{userId}/exercises/{exerciseId}/personal-best")
    public Double personalBest(@PathVariable Long exerciseId, @PathVariable Long userId) {
        return setService.findPersonalBest(exerciseId, userId);
    }
}
