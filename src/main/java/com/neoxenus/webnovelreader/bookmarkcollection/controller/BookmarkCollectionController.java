package com.neoxenus.webnovelreader.bookmarkcollection.controller;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookmark-collections")
@RequiredArgsConstructor
public class BookmarkCollectionController {

    private final BookmarkCollectionService service;



    @PostMapping
    ResponseEntity<BookmarkCollectionDto> createCollection(@RequestBody BookmarkCollectionCreateRequest request) {
        BookmarkCollectionDto bookmarkCollectionDto = service.createCollection(request);
        URI uri = URI
                .create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/bookmark-collections/" + bookmarkCollectionDto.id())
                .toUriString());
        return ResponseEntity.created(uri).body(bookmarkCollectionDto);
    }

    @GetMapping
    List<BookmarkCollectionDto> getCollections() {
        return service.getCollections();
    }
}
