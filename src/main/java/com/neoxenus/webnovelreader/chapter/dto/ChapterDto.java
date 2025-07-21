package com.neoxenus.webnovelreader.chapter.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChapterDto (
        Long id,
        String title,
        Integer chapterNumber,
        String content,
        Integer views,
        LocalDateTime datePublished,
        Long bookId) {

}
