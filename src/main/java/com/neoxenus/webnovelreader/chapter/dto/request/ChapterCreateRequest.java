package com.neoxenus.webnovelreader.chapter.dto.request;


public record ChapterCreateRequest(
        String title,
        Integer chapterNumber,
        String content) {
}
