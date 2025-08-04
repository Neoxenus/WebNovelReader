package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookIdBookRatingDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.book.mapper.BookRatingMapper;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final ChapterMapper chapterMapper;

    private final BookRatingMapper ratingMapper;
    @Override
    public BookDto toDto(Book book, BookRatingDto rating) {
        if(book == null)
            return null;
        if(rating == null) {
            rating = new BookRatingDto();
        }
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing())
                .languageOfOriginal(book.getLanguageOfOriginal())
                .chapterList(chapterMapper.toSummary(book.getChapterList()))
                .updatedAt(book.getUpdatedAt())
                .totalViews(book.getTotalViews())
                .rating(rating)
                .build();
    }

    private BookDto toDtoWithoutChapters(Book book, BookRatingDto rating) {
        if(book == null)
            return null;
        if(rating == null) {
            rating = new BookRatingDto();
        }
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing())
                .languageOfOriginal(book.getLanguageOfOriginal())
                .updatedAt(book.getUpdatedAt())
                .totalViews(book.getTotalViews())
                .rating(rating)
                .build();
    }

    @Override
    public List<BookDto> toDto(List<Book> bookList, List<BookIdBookRatingDto> ratings) {
        if(bookList == null)
            return null;
        Map<Long, BookRatingDto> ratingsMap = ratings.stream()
                .collect(Collectors.toMap(BookIdBookRatingDto::bookId, ratingMapper::toDto));
        return bookList.stream().map(book -> toDtoWithoutChapters(book, ratingsMap.get(book.getId()))).toList();
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
