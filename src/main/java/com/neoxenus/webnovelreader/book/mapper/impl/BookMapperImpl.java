package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
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
                .totalViews(book.getTotalViews())
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
                .totalViews(book.getTotalViews())
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
        book.setTitle(bookCreateRequest.title());
        book.setYearOfPublishing(bookCreateRequest.yearOfPublishing());
        book.setLanguageOfOriginal(bookCreateRequest.languageOfOriginal());
        book.setChapterList(new ArrayList<>());
        return book;
    }

    @Override
    public Book toBook(Book toUpdate, BookUpdateRequest request) {
        if(toUpdate == null || request == null)
            return null;
        toUpdate.setTitle(request.title());
        toUpdate.setYearOfPublishing(request.yearOfPublishing());
        toUpdate.setLanguageOfOriginal(request.languageOfOriginal());
        return toUpdate;
    }


}
