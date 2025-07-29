package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.bookmark.enums.BookmarkType;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param chapter Optional
 */
@Builder
public record BookmarkDto(
        Long id,
        BookDto book,
        ChapterDto chapter,
        BookmarkType type,
        List<BookmarkCollectionDto> collections,
        String note,
        LocalDateTime createdAt
) {

}
