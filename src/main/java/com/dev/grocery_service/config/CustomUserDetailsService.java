package com.dev.grocery_service.config;

import com.dev.grocery_service.dto.UserDto;
import com.dev.grocery_service.entity.User;
import com.dev.grocery_service.mapper.UserMapper;
import com.dev.grocery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username) .orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }


    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }


    public List<UserDto> getUsersWithRolesAndOrders(){
        return userRepository.findAllUsersWithRolesAndOrders().stream().map(userMapper::toUserDTO).toList();
    }
}
