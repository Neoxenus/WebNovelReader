package com.neoxenus.webnovelreader.bookmarkcollection.dto.request;

public record BookmarkCollectionUpdateRequest(
        Boolean isPublic,

        String name,

        String description

) {
}
