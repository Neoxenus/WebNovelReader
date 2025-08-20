package com.neoxenus.webnovelreader.user.controller;

import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.ImageDto;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
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
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Validated @RequestBody UserCreateRequest user)
                                            throws UsernameExistsException {
        UserDto userDto = userService.saveUser(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/" + userDto.id()).toUriString());
        return ResponseEntity.created(uri).body(userDto);
    }


    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getUserAvatar(@PathVariable Long id){
        ImageDto image = userService.getAvatar(id);
        if (image == null) return ResponseEntity.notFound().build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", image.getMimeType());
        headers.setContentLength(image.getBytes().length);
        headers.setContentDisposition(
                ContentDisposition.builder("inline").filename("avatar").build()
        );
        return new ResponseEntity<>(image.getBytes(), headers, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
