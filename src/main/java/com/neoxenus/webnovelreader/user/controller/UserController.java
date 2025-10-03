package com.neoxenus.webnovelreader.user.controller;

import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.response.ImageDtoResponse;
import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateDtoRequest;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping
    public List<UserDtoResponse> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> saveUser(@Validated @RequestBody UserCreateDtoRequest user)
                                            throws UsernameExistsException {
        UserDtoResponse userDtoResponse = userService.saveUser(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/" + userDtoResponse.id()).toUriString());
        return ResponseEntity.created(uri).body(userDtoResponse);
    }


    @GetMapping("/{id}")
    public UserDtoResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getUserAvatar(@PathVariable Long id){
        ImageDtoResponse image = userService.getAvatar(id);
        if (image.bytes() == null) return ResponseEntity.notFound().build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", image.mimeType());
        headers.setContentLength(image.bytes().length);
        headers.setContentDisposition(
                ContentDisposition.builder("inline").filename("avatar").build()
        );
        return new ResponseEntity<>(image.bytes(), headers, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
