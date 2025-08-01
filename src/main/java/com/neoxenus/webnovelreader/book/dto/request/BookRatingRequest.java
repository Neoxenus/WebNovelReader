package com.neoxenus.webnovelreader.book.dto.request;

public record BookRatingRequest(
        Integer storyDevelopment,
        Integer writingQuality,
        Integer worldBackground,
        Integer characterDesign
) {
}
