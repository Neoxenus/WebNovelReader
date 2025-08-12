package com.neoxenus.webnovelreader.chapter.controller;

import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapter.service.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books/{bookId}/chapters")
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;


    @PostMapping
    public ResponseEntity<ChapterDto> addChapter (@PathVariable Long bookId,
                                         @RequestBody ChapterCreateRequest chapterCreateRequest) {
        ChapterDto chapterDto = chapterService.addChapter(bookId, chapterCreateRequest);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/books/" + bookId + "/chapters/" + chapterDto.id()).toUriString());
        return ResponseEntity.created(uri).body(chapterDto);
    }

    @GetMapping
    public List<ChapterDto> getBookChapters(@PathVariable Long bookId) {
        return chapterService.getBookChapters(bookId);
    }
    @GetMapping("/{id}")
    public ChapterDto getChapter(@PathVariable Long bookId,
                                        @PathVariable Long id) {
        return chapterService.getChapterDtoForView(bookId, id);
    }

    @PutMapping("/{id}")
    public ChapterDto updateChapter(@PathVariable Long bookId,
                                           @PathVariable Long id,
                                           @RequestBody ChapterUpdateRequest chapterUpdateRequest) {
        return chapterService.updateChapter(bookId, id, chapterUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChapter(@PathVariable Long bookId,
                              @PathVariable Long id) {
        chapterService.deleteChapter(bookId, id);
    }
}
