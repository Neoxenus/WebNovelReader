package com.neoxenus.webnovelreader.book.dto.request;

import java.util.List;

public record BookCreateRequest(
        String title,
        String yearOfPublishing,
        String languageOfOriginal,
        List<Long> tagIds
) {
}
