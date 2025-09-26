package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateDtoRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import com.neoxenus.webnovelreader.chapter.repo.ChapterRepository;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
import com.neoxenus.webnovelreader.tag.repo.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;


    private final TagRepository tagRepository;

    private final TagMapper tagMapper;
//todo: try static implementation
    private BookDtoResponse toDtoHelper(Book book, boolean withChapters) {
        if(book == null)
            return null;

        List<ChapterSummaryDtoResponse> chapterPage = null;
        if(withChapters) {
            Pageable pageable = PageRequest.of(0, 25, Sort.by("chapterNumber").descending());
            chapterPage = chapterMapper.toSummary(
                                    chapterRepository
                                            .findAllByBookId(book.getId(), pageable))
                            .getContent();
        }

        BookDtoResponse.BookDtoResponseBuilder builder = BookDtoResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublishing(book.getYearOfPublishing());


        if(withChapters) {
            builder.chapterList(chapterPage);
        }


        builder
                .updatedAt(book.getUpdatedAt())
                .totalViews(book.getTotalViews())
                .uniqueViews(book.getUniqueViews())
                .rating(
                        BookRatingDtoResponse.builder()
                                .ratingCount(book.getRatingCount())
                                .storyDevelopment(book.getAvgStoryDevelopment())
                                .writingQuality(book.getAvgWritingQuality())
                                .worldBackground(book.getAvgWorldBackground())
                                .characterDesign(book.getAvgCharacterDesign())
                                .average(book.getAvgRating())
                                .build()
                )
                .languageOfOriginal(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.LANGUAGE)).get(0))
                .genreList(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.GENRE)))
                .eventList(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.EVENT)))
                .authorList(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.AUTHOR)))
                .translatorList(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.TRANSLATOR)))
                .publisherList(tagMapper.toSummaryDto(tagFilter(book.getTags(), TagType.PUBLISHER)));

                //.tagList(tagMapper.toSummaryDto(book.getTags()))
        return builder.build();
    }

    private List<Tag> tagFilter(List<Tag> tags, TagType type) {
        return tags.stream().filter(e -> e.getTagType().equals(type)).toList();
    }
    @Override
    public BookDtoResponse toDto(Book book) {
        return toDtoHelper(book, true);
    }

    private BookDtoResponse toDtoWithoutChapters(Book book) {
        return toDtoHelper(book, false);
    }

    @Override
    public Page<BookDtoResponse> toDto(Page<Book> bookList) {
        if (bookList == null)
            return null;
        return bookList.map(this::toDtoWithoutChapters);
    }
    @Override
    public Book toBook(BookCreateDtoRequest request) {
        if(request == null)
            return null;
        List<Tag> tags = aggregateTags(request.languageOfOriginal(), request.authorIds(), request.translatorIds(), request.genreIds(), request.eventIds(), request.publisherIds());

        //todo: validate tags and tagTypes


        Book book = new Book();
        book.setTitle(request.title());
        book.setYearOfPublishing(request.yearOfPublishing());
        book.setChapterList(new ArrayList<>());
        book.setTags(tags);
        return book;
    }

    @SafeVarargs
    private List<Tag> aggregateTags(Long languageId, List<Long>... ids) {
        /*
            repository methods findById and add findAllById throw exceptions "Ids must not be null"
            it handles properly via exception handler
         */

        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.findById(languageId).orElseThrow(
                () -> new NoSuchEntityException("Book must have language field")));
        for (List<Long> idList : ids) {
            tags.addAll(tagRepository.findAllById(idList));
        }
        return tags;
    }

    @Override
    public Book toBook(Book toUpdate, BookUpdateDtoRequest request) {
        if(toUpdate == null || request == null)
            return null;
        List<Tag> tags = aggregateTags(request.languageOfOriginal(), request.authorIds(), request.translatorIds(), request.genreIds(), request.eventIds(), request.publisherIds());

        //todo: validate tags and tagTypes

        toUpdate.setTitle(request.title());
        toUpdate.setYearOfPublishing(request.yearOfPublishing());
        toUpdate.setTags(tags);
        return toUpdate;
    }


}
