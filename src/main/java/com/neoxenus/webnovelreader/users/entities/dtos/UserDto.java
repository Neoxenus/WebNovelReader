package com.neoxenus.webnovelreader.users.entities.dtos;

import com.neoxenus.webnovelreader.users.entities.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private Set<UserRole> roles;

    private LocalDateTime createdAt;
}
