package com.ecommerce.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must contain between 3 and 50 characters")
    private String username;

    @Email(message = "Email format is invalid")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 8, max = 50, message = "Password must contain between 8 and 50 characters")
    @NotBlank(message = "Password is required")
    private String password;

}
