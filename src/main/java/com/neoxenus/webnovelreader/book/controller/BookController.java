package com.neoxenus.webnovelreader.book.controller;

import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.service.BookService;
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
        return ResponseEntity.ok().body(bookService.getBook(id));
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
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
