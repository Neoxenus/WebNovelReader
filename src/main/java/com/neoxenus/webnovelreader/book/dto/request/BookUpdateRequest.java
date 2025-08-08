package com.neoxenus.webnovelreader.book.dto.request;

import java.util.List;

public record BookUpdateRequest(
        String title,
        String yearOfPublishing,
        String languageOfOriginal,
        List<Long> tagIds
) {
}
