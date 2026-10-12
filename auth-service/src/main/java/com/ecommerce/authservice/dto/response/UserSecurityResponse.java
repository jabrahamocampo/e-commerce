package com.ecommerce.authservice.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSecurityResponse {

    private String username;
    private Set<String> roles;

}
