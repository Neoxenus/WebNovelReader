package com.neoxenus.webnovelreader.chapter.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ChapterSummaryDtoResponse(
        Long id,
        String title,
        Integer chapterNumber,
        Integer views,
        LocalDateTime datePublished) {
}
