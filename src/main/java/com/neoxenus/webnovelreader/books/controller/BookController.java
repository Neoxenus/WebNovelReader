package com.neoxenus.webnovelreader.books.controller;

import com.neoxenus.webnovelreader.books.entities.dtos.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.dtos.BookUpdateRequest;
import com.neoxenus.webnovelreader.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookCreateRequest book){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/books").toUriString());
        return ResponseEntity.created(uri).body(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        return bookService.getBook(id)
                .map(ResponseEntity.ok()::body)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequest bookUpdateRequest) {
        return ResponseEntity.ok().body(bookService.updateBook(id, bookUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
