package com.dev.grocery_service.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
public class UserDto {

    private String name;
    private String username;
    private String email;
    private Set<RoleDto> roles;
    private Set<OrderDto> orders;

}
