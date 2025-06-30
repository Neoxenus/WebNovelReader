package com.example.webnovelreader.user.controller;

import com.example.webnovelreader.user.entities.User;
import com.example.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getUsers(){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        return ResponseEntity.created(uri).body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }


}
