package com.neoxenus.webnovelreader.bookmark.controller;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<BookmarkDto> createBookmark(@RequestBody BookmarkCreateRequest request) {
        BookmarkDto bookmarkDto = bookmarkService.createBookmark(request);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/bookmarks/" + bookmarkDto.id()).toUriString());
        return ResponseEntity.created(uri).body(bookmarkDto);
    }

    @GetMapping("/{id}")
    public BookmarkDto getBookmark(@PathVariable Long id) {
        return bookmarkService.getBookmark(id);
    }

    @PatchMapping("/{id}")
    public BookmarkDto updateBookmark(@PathVariable Long id, @RequestBody BookmarkUpdateRequest request) {
        return bookmarkService.updateBookmark(id, request);
    }
}
