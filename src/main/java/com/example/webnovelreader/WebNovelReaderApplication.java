package com.example.webnovelreader;

import com.example.webnovelreader.user.entities.User;
import com.example.webnovelreader.user.entities.UserRole;
import com.example.webnovelreader.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                userService.saveUser(new User(null, "admin", "admin", UserRole.ADMIN));
                userService.saveUser(new User(null, "user", "user", UserRole.USER));
            }
        };
    }
}
