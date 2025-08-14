package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
import com.neoxenus.webnovelreader.tag.repo.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final ChapterMapper chapterMapper;

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

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
                .uniqueViews(book.getUniqueViews())
                .rating(
                        BookRatingDto.builder()
                                .ratingCount(book.getRatingCount())
                                .storyDevelopment(book.getAvgStoryDevelopment())
                                .writingQuality(book.getAvgWritingQuality())
                                .worldBackground(book.getAvgWorldBackground())
                                .characterDesign(book.getAvgCharacterDesign())
                                .average(book.getAvgRating())
                                .build()
                )
                .tagList(tagMapper.toSummaryDto(book.getTags()))
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
                .uniqueViews(book.getUniqueViews())
                .rating(
                        BookRatingDto.builder()
                                .ratingCount(book.getRatingCount())
                                .storyDevelopment(book.getAvgStoryDevelopment())
                                .writingQuality(book.getAvgWritingQuality())
                                .worldBackground(book.getAvgWorldBackground())
                                .characterDesign(book.getAvgCharacterDesign())
                                .average(book.getAvgRating())
                                .build()
                )
                .tagList(tagMapper.toSummaryDto(book.getTags()))
                .build();
    }

    @Override
    public Page<BookDto> toDto(Page<Book> bookList) {
        if (bookList == null)
            return null;
        return bookList.map(this::toDtoWithoutChapters);
    }
    @Override
    public Book toBook(BookCreateRequest request) {
        List<Tag> tags = tagRepository.findAllById(request.tagIds());
        Book book = new Book();
        book.setTitle(request.title());
        book.setYearOfPublishing(request.yearOfPublishing());
        book.setLanguageOfOriginal(request.languageOfOriginal());
        book.setChapterList(new ArrayList<>());
        book.setTags(tags);
        return book;
    }

    @Override
    public Book toBook(Book toUpdate, BookUpdateRequest request) {
        if(toUpdate == null || request == null)
            return null;
        List<Tag> tags = tagRepository.findAllById(request.tagIds());
        toUpdate.setTitle(request.title());
        toUpdate.setYearOfPublishing(request.yearOfPublishing());
        toUpdate.setLanguageOfOriginal(request.languageOfOriginal());
        toUpdate.setTags(tags);
        return toUpdate;
    }


}
