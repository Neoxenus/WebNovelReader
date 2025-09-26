package com.neoxenus.webnovelreader.tag.dto.response;

import com.neoxenus.webnovelreader.tag.enums.TagType;
import lombok.Builder;

@Builder
public record TagSummaryDtoResponse(
        Long id,
        String name,
        TagType tagType

) {
}
