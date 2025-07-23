package com.neoxenus.webnovelreader.bookmark.mapper;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;

import java.util.List;

public interface BookmarkMapper {
    BookmarkDto toDto(Bookmark bookmark);
    List<BookmarkDto> toDto(List<Bookmark> bookmark);

    Bookmark toBookmark(BookmarkCreateRequest bookCreateRequest);

    Bookmark toBookmark(Bookmark toUpdate, BookmarkUpdateRequest bookUpdateRequest);
}
