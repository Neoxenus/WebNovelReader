package com.neoxenus.webnovelreader.user.controller;

import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.service.UserSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserSettingsController {
    private final UserSettingsService service;

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestParam("avatar") MultipartFile avatar)
            throws IOException {
        UserUpdateRequest request = UserUpdateRequest.builder()
                .mimeType(avatar.getContentType())
                .avatar(avatar.getBytes())
                .build();
        return service.updateUser(id, request);
    }

}
