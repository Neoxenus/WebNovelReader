package com.neoxenus.webnovelreader.bookmark.controller;

import com.neoxenus.webnovelreader.bookmark.dto.response.BookmarkDtoResponse;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateDtoRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateDtoRequest;
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
    public ResponseEntity<BookmarkDtoResponse> createBookmark(@RequestBody BookmarkCreateDtoRequest request) {
        BookmarkDtoResponse bookmarkDtoResponse = bookmarkService.createBookmark(request);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/bookmarks/" + bookmarkDtoResponse.id()).toUriString());
        return ResponseEntity.created(uri).body(bookmarkDtoResponse);
    }

    @GetMapping("/{id}")
    public BookmarkDtoResponse getBookmark(@PathVariable Long id) {
        return bookmarkService.getBookmark(id);
    }

    @PatchMapping("/{id}")
    public BookmarkDtoResponse updateBookmark(@PathVariable Long id, @RequestBody BookmarkUpdateDtoRequest request) {
        return bookmarkService.updateBookmark(id, request);
    }
}
