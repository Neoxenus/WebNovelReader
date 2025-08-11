package com.neoxenus.webnovelreader.tag.dto;

import com.neoxenus.webnovelreader.tag.enums.TagType;
import lombok.Builder;

@Builder
public record TagSummaryDto(
        Long id,
        String name,
        TagType tagType

) {
}
