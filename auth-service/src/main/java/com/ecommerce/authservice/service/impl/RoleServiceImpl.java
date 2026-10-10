package com.ecommerce.authservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.authservice.entity.Role;
import com.ecommerce.authservice.repository.RoleRepository;
import com.ecommerce.authservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional (readOnly = true)
public class RoleServiceImpl implements  RoleService{

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

}
