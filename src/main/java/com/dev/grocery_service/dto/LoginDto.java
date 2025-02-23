package com.dev.grocery_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull(message = "Username is required.")
    private String username;

    @NotNull(message = "Password is required.")
    private String password;

}
