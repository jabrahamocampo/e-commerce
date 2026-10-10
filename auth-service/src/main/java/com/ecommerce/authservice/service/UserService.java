package com.ecommerce.authservice.service;

import java.util.Optional;
import java.util.UUID;

import com.ecommerce.authservice.entity.User;

public interface UserService {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> finByEmail(String email);

    boolean existisByUsername(String username);

    boolean existsByEmail(String email);

}
