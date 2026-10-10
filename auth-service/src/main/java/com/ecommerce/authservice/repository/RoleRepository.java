package com.ecommerce.authservice.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.authservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{

    Optional<Role> findByName(String name);

    boolean exexistsByName(String name);


}
