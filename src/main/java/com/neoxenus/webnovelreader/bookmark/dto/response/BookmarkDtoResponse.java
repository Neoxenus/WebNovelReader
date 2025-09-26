package com.neoxenus.webnovelreader.bookmark.dto.response;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.bookmark.entity.enums.BookmarkType;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.response.BookmarkCollectionDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterDtoResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param chapter Optional
 */
@Builder
public record BookmarkDtoResponse(
        Long id,
        BookDtoResponse book,
        ChapterDtoResponse chapter,
        BookmarkType type,
        List<BookmarkCollectionDtoResponse> collections,
        String note,
        LocalDateTime createdAt
) {

}
