package com.dev.grocery_service.service.Impl;

import com.dev.grocery_service.config.JwtTokenProvider;
import com.dev.grocery_service.dto.LoginDto;
import com.dev.grocery_service.dto.SignUpDto;
import com.dev.grocery_service.entity.Role;
import com.dev.grocery_service.entity.User;
import com.dev.grocery_service.mapper.UserMapper;
import com.dev.grocery_service.repository.RoleRepository;
import com.dev.grocery_service.repository.UserRepository;
import com.dev.grocery_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public String signup(SignUpDto request) {

        // Fetch or save roles
        Set<Role> roles = request.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .collect(Collectors.toSet());
        User user1 = userMapper.signUpDtoToUser(request);
        user1.setRoles(roles);
        userRepository.save(user1);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 04 - Return the token to controller
        return jwtTokenProvider.generateToken(authentication);
    }
}
