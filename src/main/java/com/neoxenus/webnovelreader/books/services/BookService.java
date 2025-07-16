package com.neoxenus.webnovelreader.books.services;

import com.neoxenus.webnovelreader.books.entities.dtos.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.dtos.BookDto;
import com.neoxenus.webnovelreader.books.entities.dtos.BookUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    BookDto saveBook(BookCreateRequest book);

    Optional<BookDto> getBook(Long id);

    List<BookDto> getAllBooks();


    BookDto updateBook(Long id, BookUpdateRequest book);


    void deleteBook(Long id);
}
