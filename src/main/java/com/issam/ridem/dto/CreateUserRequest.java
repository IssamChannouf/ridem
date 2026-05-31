package com.issam.ridem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming user creation requests, includes password for hashing
@Data
public class CreateUserRequest {

    // Full name of the user, must start with uppercase, between 2 and 50 characters
    @NotBlank
    @Size(min=2, max=50)
    @Pattern(regexp="^[A-Z].*")
    private String name;

    // Unique email as alternative identifier and for account, must be valid email format
    @NotBlank
    @Email
    private String email;

    // Plain text password, hashed in the service before persisting
    @NotBlank
    @Size(min=8, max=60)
    private String password;

    // Age of the user, optional, must be between 14 and 80 if provided
    @Min(14)
    @Max(80)
    private Integer age;
    
}
