package com.dev.grocery_service.dto;

import com.dev.grocery_service.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

    @NotNull(message = "Name is required.")
    private String name;
    @NotNull(message = "Username is required.")
    private String username;
    @NotNull(message = "Email is required.")
    private String email;
    @NotNull(message = "Password is required.")
    private String password;
    @NotNull(message = "Roles are required.")
    private Set<Role> roles;
}
