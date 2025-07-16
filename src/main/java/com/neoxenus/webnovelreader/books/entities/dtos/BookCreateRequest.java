package com.neoxenus.webnovelreader.books.entities.dtos;

import lombok.Data;

@Data
public class BookCreateRequest {

    private String title;

    private String yearOfPublishing;

    private String languageOfOriginal;

}
