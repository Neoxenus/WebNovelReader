package com.neoxenus.webnovelreader.book.controller;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.service.BookService;
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
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
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
