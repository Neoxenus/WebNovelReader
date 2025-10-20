package com.neoxenus.webnovelreader.book.controller;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateDtoRequest;
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
@RequestMapping("/books")
@Slf4j
public class BookController {

    //todo: fetch books by tags
    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookDtoResponse> createBook(@RequestBody BookCreateDtoRequest book){
        log.info("Got request for saving: {}", book);
        BookDtoResponse bookDtoResponse = bookService.saveBook(book);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/books/" + bookDtoResponse.id()).toUriString());
        return ResponseEntity.created(uri).body(bookDtoResponse);
    }

    @GetMapping("/{id}")
    public BookDtoResponse getBook(@PathVariable Long id){
        return bookService.getBookDtoById(id);
    }
    @GetMapping
    public Page<BookDtoResponse> getAllBooks(
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
    public BookDtoResponse updateBook(@PathVariable Long id, @RequestBody BookUpdateDtoRequest bookUpdateDtoRequest) {
        return bookService.updateBook(id, bookUpdateDtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/{id}/rate")
    public BookRatingDtoResponse rateBook(@PathVariable Long id, @RequestBody BookRatingDtoRequest request){
        return bookService.rateBook(id, request);
    }
}
