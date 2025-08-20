package com.neoxenus.webnovelreader.user.dto.request;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
//        String email,
//        String password,
        String mimeType,
        byte[] avatar

) {

}
