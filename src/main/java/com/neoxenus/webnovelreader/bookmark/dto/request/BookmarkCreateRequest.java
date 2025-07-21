package com.neoxenus.webnovelreader.bookmark.dto.request;

public record BookmarkCreateRequest(
        Long bookId,
        Long chapterId,
        Long collectionId,
        String note) {

}
