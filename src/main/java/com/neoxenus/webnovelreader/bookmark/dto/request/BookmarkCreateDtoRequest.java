package com.neoxenus.webnovelreader.bookmark.dto.request;

import com.neoxenus.webnovelreader.bookmark.entity.enums.BookmarkType;

public record BookmarkCreateDtoRequest(
        Long bookId,
        Long chapterId,//nullable
        Long collectionId,
        BookmarkType type,
        String note
) {

}
