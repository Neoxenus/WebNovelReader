package com.neoxenus.webnovelreader.bookmark.dto.request;

import java.util.List;

public record BookmarkUpdateRequest(
        List<Long> collectionsId,
        String note
) {
}
