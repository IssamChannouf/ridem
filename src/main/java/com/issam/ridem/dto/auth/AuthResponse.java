package com.issam.ridem.dto.auth;

import lombok.Data;

// DTO for authentication responses, contains the JWT token
@Data
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}