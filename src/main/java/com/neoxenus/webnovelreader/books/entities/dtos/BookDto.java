package com.neoxenus.webnovelreader.books.entities.dtos;

import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterSummary;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookDto {

    private final Long id;

    private final String title;

    private final String yearOfPublishing;

    private final String languageOfOriginal;

    private final LocalDateTime updatedAt;

    private final List<ChapterSummary> chapterList;
}
