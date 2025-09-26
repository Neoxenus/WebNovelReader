package com.neoxenus.webnovelreader.bookmarkcollection.dto.request;

public record BookmarkCollectionCreateDtoRequest(
        Boolean isPublic,

        String name,

        String description

){
}
