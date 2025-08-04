package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookIdBookRatingDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;

import java.util.List;

public interface BookMapper {
    //BookDto toDto(Book book);
    BookDto toDto(Book book, BookRatingDto rating);

    List<BookDto> toDto(List<Book> bookList, List<BookIdBookRatingDto> ratings);

    Book toBook(BookCreateRequest bookCreateRequest);

    Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest);
}
