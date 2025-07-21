package com.neoxenus.webnovelreader.chapter.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ChapterSummary(
        Long id,
        String title,
        Integer chapterNumber,
        Integer views,
        LocalDateTime datePublished) {
}
