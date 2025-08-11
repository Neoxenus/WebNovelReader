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
        String languageOfOriginal,
        LocalDateTime updatedAt,
        Integer totalViews,

        BookRatingDto rating,
        List<ChapterSummary> chapterList,

        List<TagSummaryDto> tagList
) {
}
