package com.neoxenus.webnovelreader.book.dto;

import com.neoxenus.webnovelreader.chapter.dto.ChapterSummary;
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

        Integer storyDevelopment,
        Integer writingQuality,
        Integer worldBackground,
        Integer characterDesign,

        Integer averageRating,
        Integer ratingCount,

        List<ChapterSummary> chapterList
) {
}
