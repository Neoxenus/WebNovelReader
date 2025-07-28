package com.neoxenus.webnovelreader.bookmark.service;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;

public interface BookmarkService {

    BookmarkDto createBookmark(BookmarkCreateRequest request);

    BookmarkDto updateBookmark(Long id, BookmarkUpdateRequest request);

    BookmarkDto getBookmark(Long id);

//    void moveBookmarks(List<Long> bookmarkIds, Long sourceCollection, Long targetCollection);
//    void addBookmarks(List<Long> bookmarkIds, Long targetCollection);

//    void delete(Long id);
//    void delete(List<Long> ids);

}
