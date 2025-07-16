package com.neoxenus.webnovelreader.chapters.etitities.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChapterDto {
    private Long id;

    private String title;

    private Integer chapterNumber;

    private String content;

    private LocalDateTime datePublished;

    private Long bookId;
}
