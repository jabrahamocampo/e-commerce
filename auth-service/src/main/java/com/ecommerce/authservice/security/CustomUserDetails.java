package com.ecommerce.authservice.security;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ecommerce.authservice.entity.Role;
import com.ecommerce.authservice.entity.User;


public class CustomUserDetails implements UserDetails{

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
         List<GrantedAuthority> authorities = new ArrayList<>();

         for (Role role : user.getRoles()) {
            authorities.add( new SimpleGrantedAuthority(role.getName()));
         }

         return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNonLocked();
    }


}
