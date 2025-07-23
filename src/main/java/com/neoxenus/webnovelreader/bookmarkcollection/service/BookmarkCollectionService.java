package com.neoxenus.webnovelreader.bookmarkcollection.service;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.CollectionReorderRequest;

import java.util.List;

public interface BookmarkCollectionService {
    BookmarkCollectionDto createCollection(BookmarkCollectionCreateRequest request);

    List<BookmarkCollectionDto> getCollections();
    List<BookmarkDto> loadCollectionContent(Long id);

    BookmarkCollectionDto updateCollection(Long id, BookmarkCollectionUpdateRequest request);
    List<BookmarkCollectionDto> reorder(List<CollectionReorderRequest> request);
    void emptyCollection(Long id);

    void deleteCollection(Long id);
}
