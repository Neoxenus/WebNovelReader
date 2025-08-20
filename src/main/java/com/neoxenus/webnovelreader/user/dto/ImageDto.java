package com.neoxenus.webnovelreader.user.dto;

import lombok.Data;

@Data
public class ImageDto {
    private final String mimeType;
    private final byte[] bytes;
}
