package com.neoxenus.webnovelreader.chapter.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChapterDtoResponse(
        Long id,
        String title,
        Integer chapterNumber,
        String content,
        Integer views,
        LocalDateTime datePublished,
        Long bookId) {

}
