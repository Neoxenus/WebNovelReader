package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.response.BookIdBookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.projection.BookRatingAverages;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingDtoRequest;
import com.neoxenus.webnovelreader.book.entity.BookRating;

public interface BookRatingMapper {
    BookRatingDtoResponse toDto(BookRatingAverages rating);
    BookRatingDtoResponse toDto(BookIdBookRatingDtoResponse rating);

    BookRating toRating(BookRatingDtoRequest request);
    BookRating toRating(BookRating rating, BookRatingDtoRequest request);
}
