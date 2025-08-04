package com.neoxenus.webnovelreader.bookmark.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import com.neoxenus.webnovelreader.bookmarkcollection.mapper.BookmarkCollectionMapper;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkMapperImpl implements BookmarkMapper {

    private final BookMapper bookMapper;
    private final ChapterMapper chapterMapper;
    private final BookmarkCollectionMapper collectionMapper;

    private final BookService bookService;

    @Override
    public BookmarkDto toDto(Bookmark bookmark) {
        BookRatingDto rating = bookService.getRatingForBook(bookmark.getBook().getId());
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .book(bookMapper.toDto(bookmark.getBook(), rating))
                .chapter(chapterMapper.toDto(bookmark.getChapter()))
                .type(bookmark.getType())
                .collections(collectionMapper.toDto(bookmark.getCollections()))
                .note(bookmark.getNote())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }

    @Override
    public List<BookmarkDto> toDto(List<Bookmark> bookmark) {
        return bookmark.stream().map(this::toDto).toList();
    }

    @Override
    public Bookmark toBookmark(BookmarkCreateRequest request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setType(request.type());
        bookmark.setNote(request.note());
        return bookmark;
    }

    @Override
    public Bookmark toBookmark(Bookmark toUpdate, BookmarkUpdateRequest request) {
        if(toUpdate == null || request == null)
            return null;
        toUpdate.setNote(request.note());
        return toUpdate;
    }
}
