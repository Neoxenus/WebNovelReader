package com.neoxenus.webnovelreader.user.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotEmpty(message = "Username mustn't be empty")
    @Size(min = 4, max = 50, message = "Size of username must be between 4 and 50 characters")
    private String username;

    @NotEmpty(message = "Email mustn't be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Password mustn't be empty")
    @Size(min = 8, max = 128, message = "Size of password must be between 4 and 128 characters")
    private String password;
}
