package com.neoxenus.webnovelreader.chapter.dto.request;

public record ChapterUpdateDtoRequest(
        String title,
        Integer chapterNumber,
        String content) {
}
