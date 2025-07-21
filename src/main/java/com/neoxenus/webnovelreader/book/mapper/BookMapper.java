package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookMapper {
    BookDto toDto(Book book);

    List<BookDto> toDto(List<Book> bookList);

    Book toBook(BookCreateRequest bookCreateRequest);

    Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest);
}
