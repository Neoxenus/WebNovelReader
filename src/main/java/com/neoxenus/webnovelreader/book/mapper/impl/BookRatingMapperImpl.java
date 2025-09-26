package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.response.BookIdBookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.projection.BookRatingAverages;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingDtoRequest;
import com.neoxenus.webnovelreader.book.entity.BookRating;
import com.neoxenus.webnovelreader.book.mapper.BookRatingMapper;
import org.springframework.stereotype.Component;

@Component
public class BookRatingMapperImpl implements BookRatingMapper {

    @Override
    public BookRatingDtoResponse toDto(BookRatingAverages rating) {
        if(rating == null) {
            return new BookRatingDtoResponse();
        }

        long count = rating.getRatingCount() != null ? rating.getRatingCount() : 0L;

        double characterDesign = rating.getAvgCharacterDesign() != null ? rating.getAvgCharacterDesign() : 0d;
        double worldBackground = rating.getAvgWorldBackground() != null ? rating.getAvgWorldBackground() : 0d;
        double storyDevelopment = rating.getAvgStoryDevelopment() != null ? rating.getAvgStoryDevelopment() : 0d;
        double writingQuality = rating.getAvgWritingQuality() != null ? rating.getAvgWritingQuality() : 0d;

        double average = (characterDesign + worldBackground + storyDevelopment + writingQuality) / 4d;

        return BookRatingDtoResponse.builder()
                .ratingCount(count)
                .characterDesign(characterDesign)
                .worldBackground(worldBackground)
                .storyDevelopment(storyDevelopment)
                .writingQuality(writingQuality)
                .average(average)
                .build();
    }

    @Override
    public BookRatingDtoResponse toDto(BookIdBookRatingDtoResponse rating) {
        return BookRatingDtoResponse.builder()
                .ratingCount(rating.ratingsCount())
                .characterDesign(rating.avgCharacterDesign())
                .worldBackground(rating.avgWorldBackground())
                .storyDevelopment(rating.avgStoryDevelopment())
                .writingQuality(rating.avgWritingQuality())
                .average((
                                 rating.avgCharacterDesign()
                                 + rating.avgWorldBackground()
                                 + rating.avgStoryDevelopment()
                                 + rating.avgWritingQuality() )/4d)
                .build();
    }

    @Override
    public BookRating toRating(BookRatingDtoRequest request) {

        BookRating rating = new BookRating();
        rating.setCharacterDesign(request.characterDesign());
        rating.setWorldBackground(request.worldBackground());
        rating.setStoryDevelopment(request.storyDevelopment());
        rating.setWritingQuality(request.writingQuality());
        return rating;
    }

    @Override
    public BookRating toRating(BookRating rating, BookRatingDtoRequest request) {
        if(rating == null) {
            return toRating(request);
        }
        rating.setCharacterDesign(request.characterDesign());
        rating.setWorldBackground(request.worldBackground());
        rating.setStoryDevelopment(request.storyDevelopment());
        rating.setWritingQuality(request.writingQuality());
        return rating;
    }

}
