package com.neoxenus.webnovelreader.bookmarkcollection.dto.response;

import lombok.Builder;

@Builder
public record BookmarkCollectionDtoResponse(
        Long id,
        Boolean isDefault,
        Boolean isPublic,
        Long userId,
        String name,
        String description,
        Integer position,
        Integer count
) {

}
