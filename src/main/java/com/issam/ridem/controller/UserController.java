package com.issam.ridem.controller;

import java.util.List;

import com.issam.ridem.dto.user.CreateUserRequest;
import com.issam.ridem.dto.user.UpdateUserRequest;
import com.issam.ridem.dto.user.UserDTO;
import com.issam.ridem.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Handles all HTTP requests related to users, maps to the /users base URL
@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Handles GET /users, retrieves and returns all users from the database as JSON
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    // Handles POST /users, creates a new user, returns 400 if validation fails
    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    // Handles GET /users/{id}, returns a single user by ID, or 404 if not found
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Handles DELETE /users/{id}, returns 204 on success, 404 if user does not exist    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    
    // Handles PUT /users/{id}, returns updated user on success, 404 if user does not exist
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }
}