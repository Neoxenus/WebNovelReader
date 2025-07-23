package com.neoxenus.webnovelreader.bookmarkcollection.dto.request;

public record BookmarkCollectionCreateRequest (
        Boolean isPublic,

        String name,

        String description,

        Integer position

){
}
