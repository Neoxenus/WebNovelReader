package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import org.springframework.data.domain.Page;

public interface BookMapper {
    //BookDto toDto(Book book);
    BookDto toDto(Book book);

    Page<BookDto> toDto(Page<Book> bookList);

    Book toBook(BookCreateRequest bookCreateRequest);

    Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest);
}
