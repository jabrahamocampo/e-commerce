package com.ecommerce.authservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecommerce.authservice.dto.request.RegisterRequest;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping (target = "id", ignore = true)
    @Mapping (target = "password", ignore = true)
    @Mapping (target = "roles", ignore = true)
    @Mapping (target = "accountNonLocked", ignore = true)
    @Mapping (target = "createdAt", ignore = true)
    @Mapping (target = "updatedAt", ignore = true)
    User toEntity(RegisterRequest request);

    @Mapping(target = "roles",
            expression = "java(user.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet()))")
    UserResponse toResponse(User user);

}
