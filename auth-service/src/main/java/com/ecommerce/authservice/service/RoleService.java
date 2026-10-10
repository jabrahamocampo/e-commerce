package com.ecommerce.authservice.service;

import java.util.Optional;

import com.ecommerce.authservice.entity.Role;

public interface RoleService {

    Optional<Role> findByName(String name);

}
