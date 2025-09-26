package com.neoxenus.webnovelreader.bookmark.dto.request;

import com.neoxenus.webnovelreader.bookmark.entity.enums.UpdateType;

public record BookmarkUpdateDtoRequest(
        UpdateType type,
        Long collectionId,
        String note
) {
}
