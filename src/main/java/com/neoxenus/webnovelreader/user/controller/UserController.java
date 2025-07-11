package com.neoxenus.webnovelreader.user.controller;

import com.neoxenus.webnovelreader.exceptions.NoSuchUserException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Validated @RequestBody UserCreateRequest user)
                                            throws UsernameExistsException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        Optional<UserDto> userOptional = userService.getUser(id);
        return userOptional
                .map(ResponseEntity.ok()::body)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserUpdateRequest userUpdateRequest)
                                              throws NoSuchUserException, UsernameExistsException {
        UserDto user = userService.updateUser(id, userUpdateRequest);

        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
