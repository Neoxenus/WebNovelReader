package com.neoxenus.webnovelreader.user.dto.request;

import lombok.Builder;

@Builder
public record UserUpdateDtoRequest(
//        String email,
//        String password,
        String mimeType,
        byte[] avatar

) {

}
