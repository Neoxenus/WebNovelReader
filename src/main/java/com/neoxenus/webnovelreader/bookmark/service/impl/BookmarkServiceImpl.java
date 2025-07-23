package com.neoxenus.webnovelreader.bookmark.service.impl;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.repo.BookmarkRepository;
import com.neoxenus.webnovelreader.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public BookmarkDto createBookmark(BookmarkCreateRequest request) {
        return null;
    }

    @Override
    public BookmarkDto updateBookmark(Long id, BookmarkUpdateRequest request) {
        return null;
    }

    @Override
    public BookmarkDto getBookmark(Long id) {
        return null;
    }

    @Override
    public void moveBookmarks(List<Long> bookmarkIds, Long sourceCollection, Long targetCollection) {

    }

    @Override
    public void addBookmarks(List<Long> bookmarkIds, Long targetCollection) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(List<Long> ids) {

    }
}
