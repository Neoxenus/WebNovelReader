package com.neoxenus.webnovelreader.book.dto.response;

public record BookIdBookRatingDtoResponse(
        Long bookId,
        Long ratingsCount,
        Double avgStoryDevelopment,
        Double avgWritingQuality,
        Double avgWorldBackground,
        Double avgCharacterDesign
) {
}
