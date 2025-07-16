package com.neoxenus.webnovelreader.books.mapper;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.books.entities.dtos.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.dtos.BookDto;
import com.neoxenus.webnovelreader.books.entities.dtos.BookUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookMapper {
    BookDto toDto(Book book);

    List<BookDto> toDto(List<Book> bookList);

    Book toBook(BookCreateRequest bookCreateRequest);

    Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest);
}
