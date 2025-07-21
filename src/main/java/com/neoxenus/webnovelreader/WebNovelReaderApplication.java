package com.neoxenus.webnovelreader;

import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.enums.UserRole;
import com.neoxenus.webnovelreader.user.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class WebNovelReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebNovelReaderApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        return args -> {
            boolean adminExists = userRepository.existsByUsername("admin");
            if(!adminExists) {
                userRepository.save(User.builder()
                        .username("admin")
                        .password(bCryptPasswordEncoder.encode("admin"))
                        .email("admin@admin")
                        .roles(Set.of(UserRole.ADMIN, UserRole.USER))
                        .build());

                userRepository.save(User.builder()
                        .username("user")
                        .password(bCryptPasswordEncoder.encode("user"))
                        .email("user@user")
                        .roles(Set.of(UserRole.USER))
                        .build());
            }
        };
    }
}
