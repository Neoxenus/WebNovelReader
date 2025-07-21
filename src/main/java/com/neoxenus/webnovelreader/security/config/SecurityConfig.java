package com.neoxenus.webnovelreader.security.config;

import com.neoxenus.webnovelreader.security.filters.JwtAuthenticationFilter;
import com.neoxenus.webnovelreader.security.filters.JwtAuthorizationFilter;
import com.neoxenus.webnovelreader.security.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    private final JwtService jwtService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager, jwtService);
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {


        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(POST, "api/users").permitAll()
                        .requestMatchers("/api/login", "/api/token/refresh").permitAll()
                        .requestMatchers(GET, "api/users", "/api/users/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(PATCH,  "/api/comments/**").authenticated()
                        .requestMatchers(DELETE,  "/api/comments/**").authenticated()
                        .requestMatchers(POST,"/api/*/*/comments").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilter(jwtAuthenticationFilter(authManager))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }






}

