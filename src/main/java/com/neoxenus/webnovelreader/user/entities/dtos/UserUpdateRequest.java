package com.neoxenus.webnovelreader.user.entities.dtos;

import com.neoxenus.webnovelreader.user.entities.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserUpdateRequest {


    private String username;


    private String email;


    private String password;


    private Set<UserRole> roles;

}
