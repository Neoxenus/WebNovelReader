package com.neoxenus.webnovelreader.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public final class BookRatingDtoResponse {
    private Long ratingCount;
    private Double storyDevelopment;
    private Double writingQuality;
    private Double worldBackground;
    private Double characterDesign;
    private Double average;

    public BookRatingDtoResponse() {
        ratingCount = 0L;
        storyDevelopment = 0d;
        worldBackground = 0d;
        characterDesign = 0d;
        average = 0d;
    }
}
