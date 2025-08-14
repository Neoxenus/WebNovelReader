package com.neoxenus.webnovelreader.book.service;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    BookDto saveBook(BookCreateRequest book);

    BookDto getBookDtoById(Long id);
    Book getBookById(Long id);

    Page<BookDto> getBooks(Pageable pageable);


    BookDto updateBook(Long id, BookUpdateRequest book);
    BookRatingDto getRatingForBook(Long id);

    BookRatingDto rateBook(Long id, BookRatingRequest request);
    void deleteBook(Long id);
}
