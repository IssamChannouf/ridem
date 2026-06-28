package com.issam.ridem.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

// DTO for incoming registration requests
@Data
public class RegisterRequest {

    @NotBlank
    @Size(min=2, max=50)
    @Pattern(regexp="^[A-Z].*")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8, max=60)
    private String password;

    @Min(14)
    @Max(80)
    private Integer age;
}