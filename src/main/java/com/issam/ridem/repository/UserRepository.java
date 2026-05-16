package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.User;

//Creates an interface repository that inherits JpaRepository and its methods
public interface UserRepository extends JpaRepository<User, Long> {

}