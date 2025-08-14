package com.neoxenus.webnovelreader.book.controller;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.service.BookService;
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
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    //todo: fetch books by tags
    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookCreateRequest book){
        BookDto bookDto = bookService.saveBook(book);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/books/" + bookDto.id()).toUriString());
        return ResponseEntity.created(uri).body(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id){
        return bookService.getBookDtoById(id);
    }
    @GetMapping
    public Page<BookDto> getAllBooks(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
            ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookService.getBooks(pageable);
    }

    @PatchMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookUpdateRequest bookUpdateRequest) {
        return bookService.updateBook(id, bookUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/{id}/rate")
    public BookRatingDto rateBook(@PathVariable Long id, @RequestBody BookRatingRequest request){
        return bookService.rateBook(id, request);
    }
}
