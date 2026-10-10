package com.ecommerce.authservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "roles")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Role {

    @Id 
    private UUID id;

    @Column (nullable = false, unique = true)
    private String name;

    private String description;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {

        if (id == null) {
            id = UUID.randomUUID();
        }

        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        createdAt = LocalDateTime.now();
    }

}
