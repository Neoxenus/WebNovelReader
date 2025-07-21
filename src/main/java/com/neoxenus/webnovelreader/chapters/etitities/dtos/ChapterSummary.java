package com.neoxenus.webnovelreader.chapters.etitities.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChapterSummary {
    private Long id;

    private String title;

    private Integer chapterNumber;

    private Integer views;

    private LocalDateTime datePublished;
}
