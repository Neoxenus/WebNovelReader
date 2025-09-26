package com.neoxenus.webnovelreader.chapter.dto.request;


public record ChapterCreateDtoRequest(
        String title,
        Integer chapterNumber,
        String content) {
}
