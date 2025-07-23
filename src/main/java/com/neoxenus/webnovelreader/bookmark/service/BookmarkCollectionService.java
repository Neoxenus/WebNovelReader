package com.neoxenus.webnovelreader.bookmark.service;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.CollectionReorderRequest;

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
