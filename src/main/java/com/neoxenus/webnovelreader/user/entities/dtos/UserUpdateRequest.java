package com.neoxenus.webnovelreader.user.entities.dtos;

import com.neoxenus.webnovelreader.user.entities.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateRequest {


    private String username;


    private String email;


    private String password;


    private Set<UserRole> roles;

}
