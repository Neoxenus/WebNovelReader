package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateDtoRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import org.springframework.data.domain.Page;

public interface BookMapper {
    //BookDto toDto(Book book);
    BookDtoResponse toDto(Book book);

    Page<BookDtoResponse> toDto(Page<Book> bookList);

    Book toBook(BookCreateDtoRequest bookCreateDtoRequest);

    Book toBook(Book toUpdate, BookUpdateDtoRequest bookUpdateDtoRequest);
}
