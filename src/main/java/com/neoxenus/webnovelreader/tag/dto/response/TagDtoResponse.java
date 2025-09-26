package com.neoxenus.webnovelreader.tag.dto.response;

import com.neoxenus.webnovelreader.tag.enums.TagType;
import lombok.Builder;

@Builder
public record TagDtoResponse(
        Long id,
        String name,
        String description,
        TagType tagType
) {
}
