package com.neoxenus.webnovelreader.book.dto;

public record BookIdBookRatingDto(
        Long bookId,
        Long ratingsCount,
        Double avgStoryDevelopment,
        Double avgWritingQuality,
        Double avgWorldBackground,
        Double avgCharacterDesign
) {
}
