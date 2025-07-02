package com.neoxenus.webnovelreader.user.entities.dtos;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String email;
    private String password;
}
