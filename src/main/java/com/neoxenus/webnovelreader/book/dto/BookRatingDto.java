package com.neoxenus.webnovelreader.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public final class BookRatingDto {
    private final Long ratingCount;
    private final Double storyDevelopment;
    private final Double writingQuality;
    private final Double worldBackground;
    private final Double characterDesign;
    private final Double average;


    public BookRatingDto() {
        ratingCount = 0L;
        storyDevelopment = 0d;
        writingQuality = 0d;
        worldBackground = 0d;
        characterDesign = 0d;
        average = 0d;
    }
}
