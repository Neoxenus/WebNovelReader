package com.neoxenus.webnovelreader.bookmarkcollection.dto;

import lombok.Builder;

@Builder
public record BookmarkCollectionDto(
        Long id,
        Boolean isDefault,
        Boolean isPublic,
        Long userId,
        String name,
        String description,
        Integer position,
        Long count
) {

}
