package com.neoxenus.webnovelreader.users.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotEmpty(message = "Username mustn't be empty")
    @Size(min = 4, max = 50, message = "Size of username must be between 4 and 50 characters")
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    @NotEmpty(message = "Email mustn't be empty")
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
