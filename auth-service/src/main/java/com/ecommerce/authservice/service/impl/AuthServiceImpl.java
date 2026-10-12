package com.ecommerce.authservice.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.authservice.dto.request.LoginRequest;
import com.ecommerce.authservice.dto.request.RegisterRequest;
import com.ecommerce.authservice.dto.response.LoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.dto.response.UserSecurityResponse;
import com.ecommerce.authservice.entity.Role;
import com.ecommerce.authservice.entity.User;
import com.ecommerce.authservice.exception.ResourceAlreadyExistsException;
import com.ecommerce.authservice.exception.ResourceNotFoundException;
import com.ecommerce.authservice.mapper.UserMapper;
import com.ecommerce.authservice.repository.UserRepository;
import com.ecommerce.authservice.security.CustomUserDetailsService;
import com.ecommerce.authservice.service.AuthService;
import com.ecommerce.authservice.service.JwtService;
import com.ecommerce.authservice.service.RoleService;
import com.ecommerce.authservice.service.UserService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;


    @Override
    public UserResponse register(RegisterRequest request) {
        
       validateRegisterRequest(request);

       User user = createUser(request);

       assignDefaultRole(user);

       encodePassword(user);

       User savedUser = saveUser(user);

       return userMapper.toResponse(savedUser);
    }

    private void validateRegisterRequest(RegisterRequest request) {
       
        if (userService.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }

        if (userService.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException( "Email already exists");
        }

    }

    private User createUser(RegisterRequest request) {
        return userMapper.toEntity(request);
    }

    private void assignDefaultRole( User user ) {
        Role role = roleService.findByName("ROLE_CUSTOMER")
                    .orElseThrow(() -> new ResourceNotFoundException(" Default role not found "));
        
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
    }

    private void encodePassword(User user) {
        user.setPassword(
            passwordEncoder.encode(
                user.getPassword()
            )
        );
    }

    private User saveUser(User user) {
        return userService.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate( 
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails =  userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token);
    }

    @Override
public UserSecurityResponse getUserSecurityInformation(String username) {

    User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException(
                            "User not found: " + username
                    ));

    Set<String> roles = new HashSet<>();

    for (Role role : user.getRoles()) {
        roles.add(role.getName());
    }

    return new UserSecurityResponse(
            user.getUsername(),
            roles
    );
}

}
