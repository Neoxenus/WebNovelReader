package com.neoxenus.webnovelreader.book.dto.request;

import java.util.List;

public record BookUpdateRequest(
        String title,
        String yearOfPublishing,
        Long languageOfOriginal,

        List<Long> genreIds,
        List<Long> eventIds,
        List<Long> authorIds,
        List<Long> translatorIds,
        List<Long> publisherIds
) {
}
