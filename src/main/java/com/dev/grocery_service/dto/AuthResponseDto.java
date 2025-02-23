package com.dev.grocery_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class AuthResponseDto {

    @NotNull(message = "Please provide token")
    private String accessToken;

}
