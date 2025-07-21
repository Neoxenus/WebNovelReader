package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;

/**
 * @param chapter Optional
 */
public record BookmarkDto(Long id, Book book, Chapter chapter, Long collectionId, String note) {

}
