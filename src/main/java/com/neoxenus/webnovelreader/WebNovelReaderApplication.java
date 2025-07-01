package com.neoxenus.webnovelreader;

import com.neoxenus.webnovelreader.user.entities.User;
import com.neoxenus.webnovelreader.user.entities.UserRole;
import com.neoxenus.webnovelreader.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class WebNovelReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebNovelReaderApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            boolean adminExists = userService.existsByUsername("admin");
            if(!adminExists) {
                userService.saveUser(User.builder()
                        .username("admin")
                        .password("admin")
                        .email("admin@admin")
                        .roles(Set.of(UserRole.ADMIN, UserRole.USER))
                        .build());

                userService.saveUser(User.builder()
                        .username("user")
                        .password("user")
                        .email("user@user")
                        .roles(Set.of(UserRole.USER))
                        .build());
            }
        };
    }
}
