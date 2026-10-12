package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.request.LoginRequest;
import com.ecommerce.authservice.dto.request.RegisterRequest;
import com.ecommerce.authservice.dto.response.LoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.dto.response.UserSecurityResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserSecurityResponse getUserSecurityInformation(String username);

}
