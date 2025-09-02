package com.neoxenus.webnovelreader.tag.dto.request;

import com.neoxenus.webnovelreader.tag.enums.TagType;

public record TagRequest(
        String name,
        String description,
        TagType tagType
) {
}
