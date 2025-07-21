package com.neoxenus.webnovelreader.chapter.dto.request;

import lombok.Data;

@Data
public class ChapterCreateRequest {
    private String title;

    private Integer chapterNumber;

    private String content;

}
