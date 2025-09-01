package com.neoxenus.webnovelreader.book.dto;

import com.neoxenus.webnovelreader.chapter.dto.ChapterSummary;
import com.neoxenus.webnovelreader.tag.dto.TagSummaryDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BookDto(
        Long id,
        String title,
        String yearOfPublishing,
        LocalDateTime updatedAt,
        Long totalViews,
        Long uniqueViews,
        BookRatingDto rating,
        List<ChapterSummary> chapterList,

        TagSummaryDto languageOfOriginal,

        List<TagSummaryDto> genreList,
        List<TagSummaryDto> eventList,
        List<TagSummaryDto> authorList,
        List<TagSummaryDto> translatorList,
        List<TagSummaryDto> publisherList

) {

}
