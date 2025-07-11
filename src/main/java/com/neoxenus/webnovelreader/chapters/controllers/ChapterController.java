package com.neoxenus.webnovelreader.chapters.controllers;

import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapters.services.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books/{bookId}/chapters")
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;


    @PostMapping
    public ResponseEntity<?> addChapter (@PathVariable Long bookId,
                                         @RequestBody ChapterCreateRequest chapterCreateRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/books/" + bookId + "/chapters").toUriString());
        return ResponseEntity.created(uri).body(chapterService.addChapter(bookId, chapterCreateRequest));
    }

    @GetMapping
    public ResponseEntity<?> getBookChapters(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(chapterService.getBookChapters(bookId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getChapter(@PathVariable Long bookId,
                                        @PathVariable Long id) {
        return ResponseEntity.ok().body(chapterService.getChapter(bookId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChapter(@PathVariable Long bookId,
                                           @PathVariable Long id,
                                           @RequestBody ChapterUpdateRequest chapterUpdateRequest) {
        return ResponseEntity.ok().body(chapterService.updateChapter(bookId, id, chapterUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable Long bookId,
                              @PathVariable Long id) {
        chapterService.deleteChapter(bookId, id);
    }
}
