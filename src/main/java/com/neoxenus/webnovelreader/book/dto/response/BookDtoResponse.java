package com.neoxenus.webnovelreader.book.dto.response;

import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.response.TagSummaryDtoResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BookDtoResponse(
        Long id,
        String title,
        String yearOfPublishing,
        LocalDateTime updatedAt,
        Long totalViews,
        Long uniqueViews,
        BookRatingDtoResponse rating,
        List<ChapterSummaryDtoResponse> chapterList,

        TagSummaryDtoResponse languageOfOriginal,

        List<TagSummaryDtoResponse> genreList,
        List<TagSummaryDtoResponse> eventList,
        List<TagSummaryDtoResponse> authorList,
        List<TagSummaryDtoResponse> translatorList,
        List<TagSummaryDtoResponse> publisherList

) {

}
