package com.neoxenus.webnovelreader.books.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookUpdateRequest {
    private Long id;

    private String name;

    private String yearOfPublishing;

    private String languageOfOriginal;

    private LocalDateTime updatedAt;

}
