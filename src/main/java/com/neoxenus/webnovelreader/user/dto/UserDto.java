package com.neoxenus.webnovelreader.user.dto;

import com.neoxenus.webnovelreader.user.enums.UserRole;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String username,
        String email,
        Set<UserRole> roles,
        LocalDateTime createdAt) {
}
