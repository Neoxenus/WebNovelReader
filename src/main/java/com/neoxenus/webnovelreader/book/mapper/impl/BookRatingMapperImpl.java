package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.BookIdBookRatingDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.projection.BookRatingAverages;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.entity.BookRating;
import com.neoxenus.webnovelreader.book.mapper.BookRatingMapper;
import org.springframework.stereotype.Component;

@Component
public class BookRatingMapperImpl implements BookRatingMapper {
//    @Override
//    public BookRatingDto toDto(BookRating rating) {
//        return BookRatingDto.builder()
//                .ratingCount(0)
//                .characterDesign(rating.getCharacterDesign().doubleValue())
//                .worldBackground(rating.getWorldBackground().doubleValue())
//                .storyDevelopment(rating.getStoryDevelopment().doubleValue())
//                .writingQuality(rating.getWritingQuality().doubleValue())
//                .average((
//                        rating.getCharacterDesign()
//                        + rating.getWorldBackground()
//                        + rating.getStoryDevelopment()
//                        + rating.getWritingQuality() )/4d)
//                .build();
//    }

    @Override
    public BookRatingDto toDto(BookRatingAverages rating) {
        if(rating == null) {
            return new BookRatingDto();
        }

        long count = rating.getRatingCount() != null ? rating.getRatingCount() : 0L;

        double characterDesign = rating.getAvgCharacterDesign() != null ? rating.getAvgCharacterDesign() : 0d;
        double worldBackground = rating.getAvgWorldBackground() != null ? rating.getAvgWorldBackground() : 0d;
        double storyDevelopment = rating.getAvgStoryDevelopment() != null ? rating.getAvgStoryDevelopment() : 0d;
        double writingQuality = rating.getAvgWritingQuality() != null ? rating.getAvgWritingQuality() : 0d;

        double average = (characterDesign + worldBackground + storyDevelopment + writingQuality) / 4d;

        return BookRatingDto.builder()
                .ratingCount(count)
                .characterDesign(characterDesign)
                .worldBackground(worldBackground)
                .storyDevelopment(storyDevelopment)
                .writingQuality(writingQuality)
                .average(average)
                .build();
    }

    @Override
    public BookRatingDto toDto(BookIdBookRatingDto rating) {
        return BookRatingDto.builder()
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
    public BookRating toRating(BookRatingRequest request) {

        BookRating rating = new BookRating();
        rating.setCharacterDesign(request.characterDesign());
        rating.setWorldBackground(request.worldBackground());
        rating.setStoryDevelopment(request.storyDevelopment());
        rating.setWritingQuality(request.writingQuality());
        return rating;
    }

    @Override
    public BookRating toRating(BookRating rating, BookRatingRequest request) {
        rating.setCharacterDesign(request.characterDesign());
        rating.setWorldBackground(request.worldBackground());
        rating.setStoryDevelopment(request.storyDevelopment());
        rating.setWritingQuality(request.writingQuality());
        return rating;
    }

}
