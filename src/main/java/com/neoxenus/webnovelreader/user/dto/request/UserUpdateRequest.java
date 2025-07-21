package com.neoxenus.webnovelreader.user.dto.request;

import com.neoxenus.webnovelreader.user.enums.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateRequest {


    private String username;


    private String email;


    private String password;


    private Set<UserRole> roles;

}
