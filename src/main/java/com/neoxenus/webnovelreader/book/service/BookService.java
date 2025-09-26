package com.neoxenus.webnovelreader.book.service;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateDtoRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    BookDtoResponse saveBook(BookCreateDtoRequest book);

    BookDtoResponse getBookDtoById(Long id);
    Book getBookById(Long id);

    Page<BookDtoResponse> getBooks(Pageable pageable);


    BookDtoResponse updateBook(Long id, BookUpdateDtoRequest book);
    BookRatingDtoResponse getRatingForBook(Long id);

    BookRatingDtoResponse rateBook(Long id, BookRatingDtoRequest request);
    void deleteBook(Long id);
}
