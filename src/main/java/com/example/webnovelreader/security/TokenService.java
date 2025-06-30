package com.example.webnovelreader.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webnovelreader.user.entities.User;
import com.example.webnovelreader.user.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userRepository.findByUsername(username);

                String accessToken =
                        jwtService.generateAccessToken(user.getUsername(), List.of(user.getRole().name()), request);
                Map<String, String> tokens = Map.of(
                        "access_token", accessToken,
                        "refresh_token", refreshToken
                );
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e){
                log.error("Error refreshing token in: {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
