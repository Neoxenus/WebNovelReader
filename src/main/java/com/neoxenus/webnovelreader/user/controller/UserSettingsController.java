package com.neoxenus.webnovelreader.user.controller;

import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateDtoRequest;
import com.neoxenus.webnovelreader.user.service.UserSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserSettingsController {
    private final UserSettingsService service;

    @PatchMapping("/{id}")
    public UserDtoResponse updateUser(@PathVariable Long id,
                                      @RequestParam("avatar") MultipartFile avatar)
            throws IOException {
        UserUpdateDtoRequest request = UserUpdateDtoRequest.builder()
                .mimeType(avatar.getContentType())
                .avatar(avatar.getBytes())
                .build();
        return service.updateUser(id, request);
    }

}
