package com.neoxenus.webnovelreader.bookmarkcollection.service;

import com.neoxenus.webnovelreader.bookmark.dto.response.BookmarkDtoResponse;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.response.BookmarkCollectionDtoResponse;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateDtoRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateDtoRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.CollectionReorderDtoRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.user.entity.User;

import java.util.List;

public interface BookmarkCollectionService {
    BookmarkCollectionDtoResponse createCollection(BookmarkCollectionCreateDtoRequest request);
    BookmarkCollection getCollectionById(Long id);
    List<BookmarkCollection> getCollectionById(List<Long> ids);

    List<BookmarkCollectionDtoResponse> getCollections();
    List<BookmarkDtoResponse> loadCollectionContent(Long id);

    BookmarkCollectionDtoResponse updateCollection(Long id, BookmarkCollectionUpdateDtoRequest request);
    List<BookmarkCollectionDtoResponse> reorder(List<CollectionReorderDtoRequest> request);

    BookmarkCollection updateCount(Long id, int delta);

    BookmarkCollection verifyUserAccessToBookmarkCollection(Long collectionId);
    void emptyCollection(Long id);

    void deleteCollection(Long id);

    void initDefaultCollectionsForUser(User user);
    //void deleteBookmarks(List<Long> ids);
}
