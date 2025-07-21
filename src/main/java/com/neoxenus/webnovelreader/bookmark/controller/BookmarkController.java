package com.neoxenus.webnovelreader.bookmark.controller;

import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
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
    public ResponseEntity<?> createBookmark(@RequestBody BookmarkCreateRequest request) {

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/bookmarks").toUriString());
        return ResponseEntity.created(uri).body(bookmarkService.createBookmark(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookmark(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookmarkService.getBookmark(id));
    }

}
