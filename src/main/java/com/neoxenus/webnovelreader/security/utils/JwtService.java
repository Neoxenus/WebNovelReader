package com.neoxenus.webnovelreader.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access-expiration}")
    private Long accessExpirationMillis;

    @Value("${jwt.refresh-expiration}")
    private Long refreshExpirationMillis;
    public String generateAccessToken(String username, Collection<String> roles, HttpServletRequest request){
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpirationMillis))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roles.stream().toList())
                .sign(getAlgorithm());
    }
    public String generateRefreshToken(String username, HttpServletRequest request){
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpirationMillis))
                .withIssuer(request.getRequestURL().toString())
                .sign(getAlgorithm());
    }

    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        return verifier.verify(token);
    }
    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }
}
