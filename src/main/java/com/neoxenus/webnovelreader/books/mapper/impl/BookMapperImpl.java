package com.neoxenus.webnovelreader.books.mapper.impl;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.books.entities.dtos.BookCreateRequest;
import com.neoxenus.webnovelreader.books.entities.dtos.BookDto;
import com.neoxenus.webnovelreader.books.entities.dtos.BookUpdateRequest;
import com.neoxenus.webnovelreader.books.mapper.BookMapper;
import com.neoxenus.webnovelreader.chapters.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final ChapterMapper chapterMapper;


    @Override
    public BookDto toDto(Book book) {
        if(book == null)
            return null;
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing())
                .languageOfOriginal(book.getLanguageOfOriginal())
                .chapterList(chapterMapper.toSummary(book.getChapterList()))
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    private BookDto toDtoWithoutChapters(Book book) {
        if(book == null)
            return null;
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing())
                .languageOfOriginal(book.getLanguageOfOriginal())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Override
    public List<BookDto> toDto(List<Book> bookList) {
        if(bookList == null)
            return null;
        return bookList.stream().map(this::toDtoWithoutChapters).toList();
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
