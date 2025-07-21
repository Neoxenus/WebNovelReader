package com.neoxenus.webnovelreader.book.service;

import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookDto saveBook(BookCreateRequest book);

    BookDto getBook(Long id);

    List<BookDto> getAllBooks();


    BookDto updateBook(Long id, BookUpdateRequest book);


    void deleteBook(Long id);
}
