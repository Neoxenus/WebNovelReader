package com.neoxenus.webnovelreader.bookmark.dto.request;

import com.neoxenus.webnovelreader.bookmark.enums.BookmarkType;

public record BookmarkCreateRequest(
        Long bookId,
        Long chapterId,//nullable
        Long collectionId,
        BookmarkType type,
        String note
) {

}
