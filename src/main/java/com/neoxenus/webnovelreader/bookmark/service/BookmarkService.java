package com.neoxenus.webnovelreader.bookmark.service;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;

public interface BookmarkService {

    BookmarkDto createBookmark(BookmarkCreateRequest request);

    BookmarkDto getBookmark(Long id);
}
