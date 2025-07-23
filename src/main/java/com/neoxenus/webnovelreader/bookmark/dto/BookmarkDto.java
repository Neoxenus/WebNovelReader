package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;

/**
 * @param chapter Optional
 */
public record BookmarkDto(
        Long id,
        BookDto book,
        ChapterDto chapter,
        Long collectionId,
        Integer position,
        String note) {

}
