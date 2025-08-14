package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.BookIdBookRatingDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.projection.BookRatingAverages;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.entity.BookRating;

public interface BookRatingMapper {
    BookRatingDto toDto(BookRatingAverages rating);
    BookRatingDto toDto(BookIdBookRatingDto rating);

    BookRating toRating(BookRatingRequest request);
    BookRating toRating(BookRating rating, BookRatingRequest request);
}
