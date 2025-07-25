package com.neoxenus.webnovelreader.bookmarkcollection.controller;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    List<BookmarkDto> getCollectionContent(@PathVariable Long id) {
        return service.loadCollectionContent(id);
    }

    @PatchMapping("/{id}")
    BookmarkCollectionDto updateCollection(@PathVariable Long id, @RequestBody BookmarkCollectionUpdateRequest request) {
        return service.updateCollection(id, request);
    }

    @DeleteMapping("/{id}/bookmarks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void emptyCollection(@PathVariable Long id) {
        service.emptyCollection(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCollection(@PathVariable Long id) {
        service.deleteCollection(id);
    }
}
