package com.neoxenus.webnovelreader.books.services;

import com.neoxenus.webnovelreader.books.entities.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.BookDto;
import com.neoxenus.webnovelreader.books.entities.BookUpdateRequest;
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
