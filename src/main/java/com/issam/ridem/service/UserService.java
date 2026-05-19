package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.UserRepository;
import java.util.List;
import com.issam.ridem.entity.User;
import java.util.Optional;

// Service layer for user-related business logic, acts as the bridge between the controller and the repository
@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    // Retrieves all users from the database and returns them as a list
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    // Saves a new user to the database and returns the persisted user with auto-generated ID
    public User createUser(User user){
        return userRepository.save(user);
    }
    // Retrieves a single user by ID, throws RuntimeException if no user is found with the given ID 
    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        } 
    }
}
