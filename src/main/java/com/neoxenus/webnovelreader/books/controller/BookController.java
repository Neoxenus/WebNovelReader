package com.neoxenus.webnovelreader.books.controller;

import com.neoxenus.webnovelreader.books.entities.BookCreateRequest;
import com.neoxenus.webnovelreader.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody BookCreateRequest book){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/books").toUriString());
        return ResponseEntity.created(uri).body(bookService.saveBook(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        return bookService.getBook(id)
                .map(ResponseEntity.ok()::body)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
}
