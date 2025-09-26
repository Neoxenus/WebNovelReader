package com.neoxenus.webnovelreader.bookmarkcollection.mapper;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.response.BookmarkCollectionDtoResponse;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateDtoRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateDtoRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;

import java.util.List;

public interface BookmarkCollectionMapper {
    BookmarkCollectionDtoResponse toDto(BookmarkCollection collection);
    List<BookmarkCollectionDtoResponse> toDto(List<BookmarkCollection> collections);

    BookmarkCollection toCollection(BookmarkCollectionCreateDtoRequest request);

    BookmarkCollection toCollection(BookmarkCollection toUpdate, BookmarkCollectionUpdateDtoRequest request);
}
