package com.neoxenus.webnovelreader.book.dto.request;

public record BookUpdateRequest(
        String title,
        String yearOfPublishing,
        String languageOfOriginal) {

}
