package com.neoxenus.webnovelreader.openapi.controller;

import com.neoxenus.webnovelreader.security.dto.request.LoginDtoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthControllerDoc {

    @Operation(
            summary = "Login user",
            description = "Authenticates user and returns a JWT token. This endpoint is handled by Spring Security Filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully authenticated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public void login(@RequestBody LoginDtoRequest request) {
        throw new IllegalStateException("This method should not be called. It is implemented by Spring Security filters.");
    }
}