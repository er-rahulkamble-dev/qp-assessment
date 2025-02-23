package com.dev.grocery_service.mapper;

import com.dev.grocery_service.dto.SignUpDto;
import com.dev.grocery_service.dto.UserDto;
import com.dev.grocery_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")// Use Spring's dependency injection
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "userId", ignore = true) // Ignore userId during sign-up
    @Mapping(target = "orders", ignore = true) // Ignore orders during sign-up
    @Mapping(target ="roles", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword") // Map password field specifically
    public abstract User signUpDtoToUser(SignUpDto signUpDto);

    public abstract UserDto toUserDTO(User user);

    public abstract SignUpDto userToSignUpDto(User user);

    // Custom method to encode the password
    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}