package com.neoxenus.webnovelreader.book.dto.request;

import lombok.Data;

@Data
public class BookCreateRequest {

    private String title;

    private String yearOfPublishing;

    private String languageOfOriginal;

}
