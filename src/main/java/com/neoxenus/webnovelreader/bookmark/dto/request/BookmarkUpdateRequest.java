package com.neoxenus.webnovelreader.bookmark.dto.request;

import com.neoxenus.webnovelreader.bookmark.enums.UpdateType;

public record BookmarkUpdateRequest(
        UpdateType type,
        Long collectionId,
        String note
) {
}
