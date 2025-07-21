package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;

/**
 * @param chapter Optional
 */
public record BookmarkDto(
        Long id,
        Book book,
        ChapterDto chapter,
        Long collectionId,
        String note) {

}
