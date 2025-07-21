package com.neoxenus.webnovelreader.bookmark.service;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;

public interface BookmarkService {

    BookmarkDto createBookmark(BookmarkCreateRequest request);

    Bookmark getBookmark(Long id);
}
