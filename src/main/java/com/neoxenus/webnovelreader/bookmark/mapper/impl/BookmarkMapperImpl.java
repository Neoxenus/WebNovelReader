package com.neoxenus.webnovelreader.bookmark.mapper.impl;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookmarkMapperImpl implements BookmarkMapper {
    @Override
    public BookmarkDto toDto(Bookmark bookmark) {
        return null;
    }

    @Override
    public List<BookmarkDto> toDto(List<Bookmark> bookmark) {
        return null;
    }

    @Override
    public Bookmark toBookmark(BookmarkCreateRequest bookCreateRequest) {
        return null;
    }

    @Override
    public Bookmark toBookmark(Bookmark toUpdate, BookmarkUpdateRequest bookUpdateRequest) {
        return null;
    }
}
