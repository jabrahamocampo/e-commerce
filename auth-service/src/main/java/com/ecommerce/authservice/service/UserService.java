package com.ecommerce.authservice.service;

import java.util.Optional;
import java.util.UUID;

import com.ecommerce.authservice.entity.User;

public interface UserService {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String name);

    boolean existsByEmail(String email);

}

//PASO 5.12 — Implementar RoleServiceImpl
