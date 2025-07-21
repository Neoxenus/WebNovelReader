package com.neoxenus.webnovelreader.book.dto.request;

public record BookCreateRequest(
        String title,
        String yearOfPublishing,
        String languageOfOriginal) {

}
