package com.issam.ridem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.issam.ridem.entity.Set;

// Creates an interface repository for sets that inherits JpaRepository and its methods
public interface SetRepository extends JpaRepository<Set, Long> {
    
}
