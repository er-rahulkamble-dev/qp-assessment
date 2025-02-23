package com.dev.grocery_service.service;


import com.dev.grocery_service.dto.LoginDto;
import com.dev.grocery_service.dto.SignUpDto;
import com.dev.grocery_service.dto.UserDto;

public interface AuthService {

    String signup(SignUpDto request);

    String login(LoginDto loginDto);

}