package com.neoxenus.webnovelreader.books.mapper.impl;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.books.entities.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.BookDto;
import com.neoxenus.webnovelreader.books.entities.BookUpdateRequest;
import com.neoxenus.webnovelreader.books.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book book) {
        if(book == null)
            return null;
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing())
                .languageOfOriginal(book.getLanguageOfOriginal())
                .updatedAt(book.getUpdatedAt())
                .chapterList(book.getChapterList())
                .build();
    }

    @Override
    public Optional<BookDto> toDto(Optional<Book> book) {
        if(book.isEmpty())
            return Optional.empty();
        return Optional.of(toDto(book.get()));
    }

    @Override
    public List<BookDto> toDto(List<Book> bookList) {
        if(bookList == null)
            return null;
        return bookList.stream().map(this::toDto).toList();
    }

    @Override
    public Book toBook(BookCreateRequest bookCreateRequest) {
        Book book = new Book();
        book.setTitle(bookCreateRequest.getTitle());
        book.setYearOfPublishing(bookCreateRequest.getYearOfPublishing());
        book.setLanguageOfOriginal(bookCreateRequest.getLanguageOfOriginal());
        book.setChapterList(new ArrayList<>());
        return book;
    }

    @Override
    public Book toBook(Book toUpdate, BookUpdateRequest bookUpdateRequest) {
        if(toUpdate == null || bookUpdateRequest == null)
            return null;
        toUpdate.setTitle(bookUpdateRequest.getTitle());
        toUpdate.setYearOfPublishing(bookUpdateRequest.getYearOfPublishing());
        toUpdate.setLanguageOfOriginal(bookUpdateRequest.getLanguageOfOriginal());
        return toUpdate;
    }


}
