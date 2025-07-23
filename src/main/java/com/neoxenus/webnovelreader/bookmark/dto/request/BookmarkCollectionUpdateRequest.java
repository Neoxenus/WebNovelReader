package com.neoxenus.webnovelreader.bookmark.dto.request;

public record BookmarkCollectionUpdateRequest(
        Boolean isPublic,

        String name,

        String description

) {
}
