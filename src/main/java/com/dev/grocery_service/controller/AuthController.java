package com.dev.grocery_service.controller;

import com.dev.grocery_service.dto.AuthResponseDto;
import com.dev.grocery_service.dto.LoginDto;
import com.dev.grocery_service.dto.SignUpDto;
import com.dev.grocery_service.dto.UserDto;
import com.dev.grocery_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "1. Please Authenticate...!"
        , description = "Please copy the token generated after Sign in or Sign Up and click on lock symbol on swagger document and provide that token to access App") // Prefix with a number to control order
public class AuthController {

    @Autowired
    private AuthService authService;

    // Build Login REST API
    @PostMapping("/sign-up")
    @Operation(summary = "1. sign up", description = "sign up, use responded token to unlock other endpoints") // Prefix with a number to control order
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody SignUpDto signUpRequest){

        //01 - Receive the token from AuthService
        String token = authService.signup(signUpRequest);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
                authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    // Build Login REST API
    @PostMapping("/sign-in")
    @Operation(summary = "2. sign In", description = "sign In, use responded token to unlock other endpoints") // Prefix with a number to control order
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
