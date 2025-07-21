package com.neoxenus.webnovelreader.book.dto.request;

import lombok.Data;

@Data
public class BookUpdateRequest {

    private String title;

    private String yearOfPublishing;

    private String languageOfOriginal;

}
