package com.issam.ridem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.User;

// Creates an interface repository for users that inherits JpaRepository and its methods
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds a user by mail, used for authentication
    Optional<User> findByEmail(String email);
}