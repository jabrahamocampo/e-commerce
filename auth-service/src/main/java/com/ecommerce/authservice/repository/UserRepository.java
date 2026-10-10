package com.ecommerce.authservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByUsername(String name);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
