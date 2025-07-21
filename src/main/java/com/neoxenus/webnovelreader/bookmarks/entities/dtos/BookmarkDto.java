package com.neoxenus.webnovelreader.bookmarks.entities.dtos;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;

/**
 * @param chapter Optional
 */
public record BookmarkDto(Long id, Book book, Chapter chapter, Long collectionId, String note) {

}
