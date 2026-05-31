package com.issam.ridem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.issam.ridem.repository.UserRepository;

import java.util.List;

import com.issam.ridem.dto.user.CreateUserRequest;
import com.issam.ridem.dto.user.UpdateUserRequest;
import com.issam.ridem.dto.user.UserDTO;
import com.issam.ridem.entity.User;
import java.util.Optional;
import java.util.stream.Collectors;

// Service layer for user-related business logic, acts as the bridge between the controller and the repository
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Retrieves all users from the database and returns them as a list
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
            .stream()
            .map(user -> new UserDTO(user))
            .collect(Collectors.toList());
    }

    // Saves a new user to the database and returns the persisted user with auto-generated ID
    public UserDTO createUser(CreateUserRequest request){
        // Builds a User entity from the incoming request
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        // Hashes the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    // Retrieves a single user by ID, throws RuntimeException if no user is found with the given ID 
    public UserDTO getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserDTO(user.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        } 
    }

    // Deletes a single user by ID, throws RuntimeException if not found
    public void deleteUser(Long id) {
        userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found by id: " + id));
        userRepository.deleteById(id);
    }

    // Updates all fields of an existing user by ID except userId and password, throws RuntimeException if not found
    public UserDTO updateUser(Long id, UpdateUserRequest updatedUser) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found by id: " + id));
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());
        User savedUser = userRepository.save(existingUser);
        return new UserDTO(savedUser);
    }
}