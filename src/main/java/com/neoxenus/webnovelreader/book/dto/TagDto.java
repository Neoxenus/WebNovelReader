package com.neoxenus.webnovelreader.book.dto;

import com.neoxenus.webnovelreader.book.enums.TagType;
import lombok.Builder;

@Builder
public record TagDto(
        Long id,
        String name,
        String description,
        TagType tagType
) {
}
