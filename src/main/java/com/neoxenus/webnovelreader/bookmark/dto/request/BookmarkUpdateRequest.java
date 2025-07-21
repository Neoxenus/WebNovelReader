package com.neoxenus.webnovelreader.bookmark.dto.request;

import java.util.List;

public record BookmarkUpdateRequest(
        Long id,
        List<Long> collectionsId,
        String note
) {
}
