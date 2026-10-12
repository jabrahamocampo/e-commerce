package com.ecommerce.authservice.dto.response;

import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String username;

    private String email;

    private Set<String> roles;

}
