package com.neoxenus.webnovelreader.books.mapper;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.books.entities.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.BookDto;
import com.neoxenus.webnovelreader.books.entities.BookUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookMapper {
    BookDto toDto(Book book);
    Optional<BookDto> toDto(Optional<Book> book);
    List<BookDto> toDto(List<Book> bookList);

    Book toBook(BookCreateRequest bookCreateRequest);

    Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest);
}
