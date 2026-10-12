package com.ecommerce.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.authservice.dto.request.LoginRequest;
import com.ecommerce.authservice.dto.request.RegisterRequest;
import com.ecommerce.authservice.dto.response.ApiResponse;
import com.ecommerce.authservice.dto.response.LoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.dto.response.UserSecurityResponse;
import com.ecommerce.authservice.service.AuthService;
import com.ecommerce.authservice.util.ApiResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register (
            @Valid @RequestBody RegisterRequest request ) {

        UserResponse response = authService.register(request);

        return ApiResponseBuilder.created( "User Registered Successfully", response);         
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
        @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ApiResponseBuilder.success("Login successful", response);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<ApiResponse<UserSecurityResponse>> getUser (
        @PathVariable String username ) {

        UserSecurityResponse response = authService.getUserSecurityInformation(username);
            
        return ApiResponseBuilder.success("User security information retrieved successfully", response);
    }

}
