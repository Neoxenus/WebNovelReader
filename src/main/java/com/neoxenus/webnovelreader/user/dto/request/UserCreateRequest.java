package com.neoxenus.webnovelreader.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record UserCreateRequest(
        @NotEmpty(message = "Username mustn't be empty")
        @Size(min = 4, max = 50, message = "Size of username must be between 4 and 50 characters")
        String username,
        @NotEmpty(message = "Email mustn't be empty")
        @Email(message = "Email must be valid")
        String email,
        @NotEmpty(message = "Password mustn't be empty")
        @Size(min = 8, max = 128, message = "Size of password must be between 4 and 128 characters")
        String password) {

}
