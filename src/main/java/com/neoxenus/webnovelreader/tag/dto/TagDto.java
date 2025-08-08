package com.neoxenus.webnovelreader.tag.dto;

import com.neoxenus.webnovelreader.tag.enums.TagType;
import lombok.Builder;

@Builder
public record TagDto(
        Long id,
        String name,
        String description,
        TagType tagType
) {
}
