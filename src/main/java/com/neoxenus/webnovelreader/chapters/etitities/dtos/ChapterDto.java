package com.neoxenus.webnovelreader.chapters.etitities.dtos;

import com.neoxenus.webnovelreader.books.entities.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChapterDto {

    private String title;

    private Integer chapterNumber;

    private String content;

    private LocalDateTime datePublished;

    //@JsonBackReference
    private Book book;
}
