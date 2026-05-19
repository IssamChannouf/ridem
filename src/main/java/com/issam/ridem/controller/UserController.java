package com.issam.ridem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.issam.ridem.entity.User;
import com.issam.ridem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Handles all HTTP requests related to users, maps to the /users base URL
@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    // Handles GET /users — retrieves and returns all users from the database as JSON
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // Handles POST /users — accepts JSON user data and persists it to the database, returns the created user with auto-generated ID
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
}
}

