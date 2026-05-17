package com.issam.ridem.service;

import org.springframework.stereotype.Service;
import com.issam.ridem.repository.UserRepository;
import java.util.List;
import com.issam.ridem.entity.User;

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
    
}
