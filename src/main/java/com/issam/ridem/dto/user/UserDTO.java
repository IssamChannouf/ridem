package com.issam.ridem.dto.user;

import com.issam.ridem.entity.User;
import lombok.Data;

// DTO for User entity, exposes only safe fields in API responses, excludes password
@Data
public class UserDTO {

    private Long userId;

    private String name;

    private String email;

    private Integer age;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
    }
}


