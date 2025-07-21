package com.neoxenus.webnovelreader.chapter.dto.request;

public record ChapterUpdateRequest(
        String title,
        Integer chapterNumber,
        String content) {
}
