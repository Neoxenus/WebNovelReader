package com.neoxenus.webnovelreader.bookmark.service;

import com.neoxenus.webnovelreader.bookmark.dto.response.BookmarkDtoResponse;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateDtoRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateDtoRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;

public interface BookmarkService {

    BookmarkDtoResponse createBookmark(BookmarkCreateDtoRequest request);

    BookmarkDtoResponse updateBookmark(Long id, BookmarkUpdateDtoRequest request);

    BookmarkDtoResponse getBookmark(Long id);

    Bookmark findById(Long id);

    Bookmark verifyUserAccessToBookmark(Long bookmarkId);

//    void moveBookmarks(List<Long> bookmarkIds, Long sourceCollection, Long targetCollection);
//    void addBookmarks(List<Long> bookmarkIds, Long targetCollection);

//    void delete(Long id);
//    void delete(List<Long> ids);

}
