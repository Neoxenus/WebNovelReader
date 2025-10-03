package com.neoxenus.webnovelreader.chapter.controller;

import com.neoxenus.webnovelreader.chapter.dto.response.ChapterDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateDtoRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateDtoRequest;
import com.neoxenus.webnovelreader.chapter.service.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bookId}/chapters")
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;


    @PostMapping
    public ResponseEntity<ChapterDtoResponse> addChapter (@PathVariable Long bookId,
                                                          @RequestBody ChapterCreateDtoRequest chapterCreateDtoRequest) {
        ChapterDtoResponse chapterDtoResponse = chapterService.addChapter(bookId, chapterCreateDtoRequest);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/books/" + bookId + "/chapters/" + chapterDtoResponse.id()).toUriString());
        return ResponseEntity.created(uri).body(chapterDtoResponse);
    }

    @GetMapping
    public Page<ChapterSummaryDtoResponse> getBookChapters(
        @PathVariable Long bookId,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "25") int size,
        @RequestParam(required = false, defaultValue = "id") String sortBy,
        @RequestParam(required = false, defaultValue = "asc") String sortDir
            ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return chapterService.getBookChapters(bookId, pageable);
    }
    @GetMapping("/{id}")
    public ChapterDtoResponse getChapter(@PathVariable Long bookId,
                                         @PathVariable Long id) {
        return chapterService.getChapterDtoForView(bookId, id);
    }

    @PutMapping("/{id}")
    public ChapterDtoResponse updateChapter(@PathVariable Long bookId,
                                            @PathVariable Long id,
                                            @RequestBody ChapterUpdateDtoRequest chapterUpdateDtoRequest) {
        return chapterService.updateChapter(bookId, id, chapterUpdateDtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChapter(@PathVariable Long bookId,
                              @PathVariable Long id) {
        chapterService.deleteChapter(bookId, id);
    }
}
