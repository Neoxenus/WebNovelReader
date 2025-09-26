package com.neoxenus.webnovelreader.bookmarkcollection.dto.request;

public record BookmarkCollectionUpdateDtoRequest(
        Boolean isPublic,

        String name,

        String description

) {
}
