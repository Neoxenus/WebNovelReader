package com.neoxenus.webnovelreader.bookmark.mapper;

import com.neoxenus.webnovelreader.bookmark.dto.response.BookmarkDtoResponse;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateDtoRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateDtoRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;

import java.util.List;

public interface BookmarkMapper {
    BookmarkDtoResponse toDto(Bookmark bookmark);
    List<BookmarkDtoResponse> toDto(List<Bookmark> bookmark);

    Bookmark toBookmark(BookmarkCreateDtoRequest bookCreateRequest);

    Bookmark toBookmark(Bookmark toUpdate, BookmarkUpdateDtoRequest bookUpdateRequest);
}
