package com.neoxenus.webnovelreader.bookmarkcollection.mapper;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;

import java.util.List;

public interface BookmarkCollectionMapper {
    BookmarkCollectionDto toDto(BookmarkCollection collection);
    List<BookmarkCollectionDto> toDto(List<BookmarkCollection> collections);

    BookmarkCollection toCollection(BookmarkCollectionCreateRequest request);

    BookmarkCollection toCollection(BookmarkCollection toUpdate, BookmarkCollectionUpdateRequest request);
}
