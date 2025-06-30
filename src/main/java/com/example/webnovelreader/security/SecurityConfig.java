    package com.example.webnovelreader.security;

    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Lazy;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    import static org.springframework.http.HttpMethod.GET;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    @Lazy
    public class SecurityConfig  {
        private final BCryptPasswordEncoder bCryptPasswordEncoder;


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authManager, bCryptPasswordEncoder);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

            return http.csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers(GET,             "/api/login")
                            .permitAll())
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers(GET, "/api/user/**")
                            .hasAnyAuthority("ADMIN"))



                    .addFilter(jwtAuthenticationFilter)
                    .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .build();
        }



        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }


    }

