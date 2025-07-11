package com.neoxenus.webnovelreader.chapters.etitities.dtos;

import lombok.Data;

@Data
public class ChapterUpdateRequest {
    private String title;

    private Integer chapterNumber;

    private String content;

}
