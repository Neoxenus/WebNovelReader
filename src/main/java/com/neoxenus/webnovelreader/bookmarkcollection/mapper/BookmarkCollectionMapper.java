package com.neoxenus.webnovelreader.bookmarkcollection.mapper;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.projection.BookmarkCountProjection;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;

import java.util.List;

public interface BookmarkCollectionMapper {
    BookmarkCollectionDto toDto(BookmarkCollection collection, Long count);
    List<BookmarkCollectionDto> toDto(List<BookmarkCollection> collections, List<BookmarkCountProjection> countProjections);

    BookmarkCollection toBookmark(BookmarkCollectionCreateRequest request);

    BookmarkCollection toBookmark(BookmarkCollection toUpdate, BookmarkCollectionUpdateRequest request);
}
