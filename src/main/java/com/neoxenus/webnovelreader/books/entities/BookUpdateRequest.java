package com.neoxenus.webnovelreader.books.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookUpdateRequest {

    private String title;

    private String yearOfPublishing;

    private String languageOfOriginal;

}
