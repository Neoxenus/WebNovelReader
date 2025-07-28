package com.neoxenus.webnovelreader;

import com.neoxenus.webnovelreader.user.service.UserService;
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
        return args -> userService.initAdmin();
    }
}
