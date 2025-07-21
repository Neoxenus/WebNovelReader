package com.neoxenus.webnovelreader.user.dto.request;

import com.neoxenus.webnovelreader.user.enums.UserRole;

import java.util.Set;


public record UserUpdateRequest(
        String username,
        String email,
        String password, Set<UserRole> roles) {


}
