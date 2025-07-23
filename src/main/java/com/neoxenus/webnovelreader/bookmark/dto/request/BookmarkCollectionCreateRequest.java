package com.neoxenus.webnovelreader.bookmark.dto.request;

public record BookmarkCollectionCreateRequest (
        Boolean isPublic,

        String name,

        String description,

        Integer position

){
}
